package co.nz.aplore.persistence.mongo.module;

import co.nz.aplore.persistence.mongo.config.MongoDBConnectionService;
import co.nz.aplore.persistence.mongo.config.MongoDBConnectionServiceImpl;

import com.google.inject.Scopes;

public class PersistenceModule extends RepositoryModule {

	@Override
	protected void configure() {
		super.configure();
		bind(MongoDBConnectionService.class).to(MongoDBConnectionServiceImpl.class).in(Scopes.SINGLETON);
	}

}
