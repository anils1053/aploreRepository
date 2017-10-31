package co.nz.aplore.business.module;

import co.nz.aplore.app.config.AppConfig;
import co.nz.aplore.business.service.LDAPLoginServiceImpl;
import co.nz.aplore.business.service.LoginService;
import co.nz.aplore.business.service.ProductService;
import co.nz.aplore.business.service.ProductServiceImpl;
import co.nz.aplore.business.service.ProductSummaryService;
import co.nz.aplore.business.service.ProductSummaryServiceImpl;
import co.nz.aplore.business.service.StaticLoginServiceImpl;

import com.google.inject.AbstractModule;

public class BusinessModule extends AbstractModule {

	private static final String LOGIN_METHOD_LDAP = "ldap";
	private static final String LOGIN_METHOD_STATIC = "static";

	@Override
	protected void configure() {
		// Service
		bind(LoginService.class).to(BusinessModule.getLoginServiceImpl());
		bind(ProductService.class).to(ProductServiceImpl.class);
		bind(ProductSummaryService.class).to(ProductSummaryServiceImpl.class);
	}

	private static Class<? extends LoginService> getLoginServiceImpl() {
		final String loginMethod = AppConfig.getInstance().getLoginMethod();
		if (loginMethod.equals(LOGIN_METHOD_STATIC)) {
			return StaticLoginServiceImpl.class;
		} else if (loginMethod.equals(LOGIN_METHOD_LDAP)) {
			return LDAPLoginServiceImpl.class;
		} else {
			throw new IllegalArgumentException("Login Method not found");
		}
	}

}