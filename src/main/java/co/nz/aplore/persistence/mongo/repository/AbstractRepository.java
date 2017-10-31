package co.nz.aplore.persistence.mongo.repository;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import co.nz.aplore.persistence.mongo.config.MongoDBConnectionService;
import co.nz.aplore.persistence.mongo.entity.BaseEntity;

public class AbstractRepository implements GenericRepository {

	@Inject
	private MongoDBConnectionService mongoDBConnectionService;

	protected Datastore getMongoDatastore() {
		return this.mongoDBConnectionService.getDatastore();
	}

	protected String getCurrentMongoDBName() {
		return getMongoDatastore().getDB().getName();
	}

	@Override
	public <E extends BaseEntity> E persist(final E entity) {
		Validate.notNull(entity);
		getMongoDatastore().save(entity);
		return entity;
	}

	@Override
	public <E extends BaseEntity> E update(final E entity) {
		Validate.notNull(entity);
		Validate.notNull(entity.getId());

		getMongoDatastore().save(entity);
		return entity;
	}

	@Override
	public <E extends BaseEntity> long count(final Class<E> clazz) {
		if (clazz == null) {
			return 0;
		}
		return getMongoDatastore().find(clazz).countAll();
	}

	@Override
	public <E extends BaseEntity> E get(final Class<E> clazz, final ObjectId id) {
		if ((clazz == null) || (id == null)) {
			return null;
		}
		return getMongoDatastore().find(clazz).field("entityId").equal(id)
				.get();
	}

}
