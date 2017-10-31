package co.nz.aplore.persistence.mongo.config;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import co.nz.aplore.app.config.AppConfig;
import co.nz.aplore.persistence.mongo.entity.BaseEntity;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoDBFactory {

	private static final MongoDBFactory INSTANCE = new MongoDBFactory();

	private Datastore datastore;

	public static MongoDBFactory instance() {
		return INSTANCE;
	}

	public Datastore getDatastore() {
		try {

			if (this.datastore != null) {
				return this.datastore;
			}

			final MongoDBConfig mongoDBConfig = AppConfig.getInstance().getMongoDBConfig();

			final MongoClient mongoClient = new MongoClient(mongoDBConfig.getHostname(), mongoDBConfig.getPort());
			mongoClient.setWriteConcern(WriteConcern.SAFE);

			final DB db = mongoClient.getDB(mongoDBConfig.getDbName());

			if (mongoDBConfig.isRequireAuth()) {
				final boolean success = db.authenticate(mongoDBConfig.getUsername(), mongoDBConfig.getPassword().toCharArray());
				if (!success) {
					throw new RuntimeException("DB access authentication failed.");
				}
			}

			this.datastore = new Morphia().mapPackage(BaseEntity.class.getPackage().getName()).createDatastore(mongoClient,
					mongoDBConfig.getDbName());

			this.datastore.ensureIndexes();
			return this.datastore;

		} catch (final Exception e) {
			throw new RuntimeException("Error initializing MongoDB", e);
		}
	}
}
