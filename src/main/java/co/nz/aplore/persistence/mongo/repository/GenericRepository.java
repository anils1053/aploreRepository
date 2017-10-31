package co.nz.aplore.persistence.mongo.repository;

import org.bson.types.ObjectId;

import co.nz.aplore.persistence.mongo.entity.BaseEntity;

public interface GenericRepository {
	<E extends BaseEntity> E persist(E entity);

	<E extends BaseEntity> E update(E entity);

	<E extends BaseEntity> long count(Class<E> clazz);

	<E extends BaseEntity> E get(Class<E> clazz, ObjectId id);
}
