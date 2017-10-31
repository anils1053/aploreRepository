package co.nz.aplore.persistence.mongo.config;

import org.mongodb.morphia.Datastore;

public interface MongoDBConnectionService {
	Datastore getDatastore();
}
