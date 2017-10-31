package co.nz.aplore.app.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import co.nz.aplore.persistence.mongo.config.MongoDBConfig;

public class AppConfig {

	private static final String CONFIG_FILE = "aplore.properties";

	private static Configuration config;

	private static AppConfig instance = new AppConfig();

	static {
		try {
			config = new PropertiesConfiguration(CONFIG_FILE);
		} catch (final ConfigurationException e) {
			throw new IllegalStateException(e);
		}
	}

	public static AppConfig getInstance() {
		return instance;
	}

	public MongoDBConfig getMongoDBConfig() {
		final String hostname = config.getString("db.mongo.hostname");
		final int port = Integer.parseInt(config.getString("db.mongo.port"));
		final String dbName = config.getString("db.mongo.dbname");
		final String username = config.getString("db.mongo.username");
		final String password = config.getString("db.mongo.password");
		return new MongoDBConfig(hostname, port, username, password, dbName);

	}

	public String getUsername() {
		return config.getString("admin.username");
	}

	public String getPassword() {
		return config.getString("admin.password");
	}

	public String getLoginMethod() {
		return config.getString("admin.login.method");
	}

	/*
	 * LDAP CONFIGURATION
	 */
	public String getLdapFactoryInitial() {
		return config.getString("admin.login.ldap.java.naming.factory.initial");
	}

	public String getLdapProviderUrl() {
		return config.getString("admin.login.ldap.java.naming.provider.url");
	}

	public String getLdapSecurityAuthentication() {
		return config.getString("admin.login.ldap.java.naming.security.authentication");
	}

	public String getLdapSecurityPrincipal() {
		return config.getString("admin.login.ldap.java.naming.security.principal");
	}

	public String getLdapSecurityCredentials() {
		return config.getString("admin.login.ldap.java.naming.security.credentials");
	}

	public String getLdapUserBase() {
		return config.getString("admin.login.ldap.user.base");
	}
	/*
	 * END OF LDAP CONFIGURATION
	 */

}