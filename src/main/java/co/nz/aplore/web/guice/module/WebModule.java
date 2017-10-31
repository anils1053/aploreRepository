package co.nz.aplore.web.guice.module;

import co.nz.aplore.business.module.BusinessModule;
import co.nz.aplore.persistence.mongo.module.PersistenceModule;

import com.google.inject.servlet.ServletModule;

public class WebModule extends ServletModule {
	@Override
	protected void configureServlets() {
		install(new PersistenceModule());
		install(new BusinessModule());
	}
}
