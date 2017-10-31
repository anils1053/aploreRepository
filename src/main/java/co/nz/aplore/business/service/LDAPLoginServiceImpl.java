package co.nz.aplore.business.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.nz.aplore.app.config.AppConfig;

public class LDAPLoginServiceImpl implements LoginService {

	private static final String LDAP_USER_SEARCH = "(sAMAccountName=%s)";
	private static final String LDAP_DISTINGUISHED_NAME = "distinguishedName";

	private final Logger log = LoggerFactory.getLogger(LDAPLoginServiceImpl.class);

	@Override
	public boolean isValidUser(final String name, final String password) {
		return ldapLogin(name, password);
	}

	private boolean ldapLogin(final String name, final String password) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			return false;
		}

		final Hashtable<String, String> env = getEnvParameters();

		DirContext ctx = null;
		NamingEnumeration<?> results = null;
		try {
			ctx = new InitialDirContext(env);
			final SearchControls controls = configureSerarchControls();

			results = ctx.search(AppConfig.getInstance().getLdapUserBase(), String.format(LDAP_USER_SEARCH, name), controls);

			if (!results.hasMore()) {
				return false; // Only if a user hasn't been found
			}

			final String distinguishedName = getLdapUserName(results);

			env.put(Context.SECURITY_PRINCIPAL, distinguishedName);
			env.put(Context.SECURITY_CREDENTIALS, password);

			new InitialDirContext(env); // Exception will be thrown on Invalid case
			this.log.info("[APLORE] Successfully logged in");
			return true;
		} catch (final Exception e) {
			this.log.error("[APLORE] Invalid login credentials", e);
			return false;
		}
	}

	private String getLdapUserName(final NamingEnumeration<?> results)
			throws NamingException {
		final SearchResult searchResult = (SearchResult) results.next();
		final Attributes attributes = searchResult.getAttributes();
		final Attribute dnAttr = attributes.get(LDAP_DISTINGUISHED_NAME);
		final String distinguishedName = (String) dnAttr.get();
		return distinguishedName;
	}

	private SearchControls configureSerarchControls() {
		final SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Search Entire Subtree
		controls.setCountLimit(1); // Sets the maximum number of entries to be returned as a result of the search
		controls.setTimeLimit(5000); // Sets the time limit of these SearchControls in milliseconds
		return controls;
	}

	private Hashtable<String, String> getEnvParameters() {
		final Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, AppConfig.getInstance().getLdapFactoryInitial());
		env.put(Context.PROVIDER_URL, AppConfig.getInstance().getLdapProviderUrl());
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, AppConfig.getInstance().getLdapSecurityPrincipal());
		env.put(Context.SECURITY_CREDENTIALS, AppConfig.getInstance().getLdapSecurityCredentials());
		return env;
	}

}
