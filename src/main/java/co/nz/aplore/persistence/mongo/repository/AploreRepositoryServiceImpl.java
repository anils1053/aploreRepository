package co.nz.aplore.persistence.mongo.repository;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import co.nz.aplore.persistence.mongo.entity.ProductEntity;
import co.nz.aplore.persistence.mongo.entity.ProductSummaryEntity;

public class AploreRepositoryServiceImpl extends AbstractRepository implements AploreRepositoryService {

	@Override
	public ProductEntity createProductEntity(final ProductEntity entity) {
		Validate.notNull(entity);
		return this.persist(entity);
	}

	@Override
	public ProductEntity updateProductEntity(final ProductEntity entity) {
		Validate.notNull(entity);
		getMongoDatastore().merge(entity);
		return entity;
	}

	@Override
	public ProductEntity getProductByEntityId(final String entityId) {
		Validate.notNull(entityId);
		final Query<ProductEntity> query = getMongoDatastore().find(ProductEntity.class);
		query.field("entityId").equal(new ObjectId(entityId));
		return query.get();
	}

	@Override
	public List<ProductEntity> getProducts() {
		final Query<ProductEntity> query = getMongoDatastore().find(ProductEntity.class);
		return query.asList();
	}

	@Override
	public ProductEntity getProductByProductId(final String id) {
		Validate.notNull(id);
		final Query<ProductEntity> query = getMongoDatastore().find(ProductEntity.class);
		query.field("productId").equal(id);
		return query.get();
	}

	@Override
	public List<ProductEntity> getProductsByname(final String name) {
		Validate.notNull(name);
		final Query<ProductEntity> query = getMongoDatastore().find(ProductEntity.class);
		query.field("productDetails.productName").equal(name);
		return query.asList();
	}

	@Override
	public ProductSummaryEntity createProductSummaryEntity(
			final ProductSummaryEntity entity) {
		Validate.notNull(entity);
		return this.persist(entity);
	}

	@Override
	public ProductSummaryEntity updateProductSummaryEntity(
			final ProductSummaryEntity entity) {
		Validate.notNull(entity);
		getMongoDatastore().merge(entity);
		return entity;
	}

	@Override
	public ProductSummaryEntity getProductSummaryEntityByName(final String name) {
		Validate.notNull(name);
		final Query<ProductSummaryEntity> query = getMongoDatastore().find(ProductSummaryEntity.class);
		query.field("name").equal(name);
		return query.get();
	}

	@Override
	public List<ProductSummaryEntity> getProductSummaryEntities() {
		final Query<ProductSummaryEntity> query = getMongoDatastore().find(ProductSummaryEntity.class);
		return query.asList();
	}

	@Override
	public List<ProductSummaryEntity> searchProductSummaryEntityByName(
			final String name) {
		Validate.notNull(name);
		final Query<ProductSummaryEntity> query = getMongoDatastore().find(ProductSummaryEntity.class);
		query.field("name").containsIgnoreCase(name);
		return query.asList();
	}
}
