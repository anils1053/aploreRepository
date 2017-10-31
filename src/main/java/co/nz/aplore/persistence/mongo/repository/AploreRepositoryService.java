package co.nz.aplore.persistence.mongo.repository;

import java.util.List;

import co.nz.aplore.persistence.mongo.entity.ProductEntity;
import co.nz.aplore.persistence.mongo.entity.ProductSummaryEntity;

public interface AploreRepositoryService {

	ProductEntity createProductEntity(ProductEntity entity);

	ProductEntity updateProductEntity(ProductEntity entity);

	/**
	 *
	 * @param id it is the mongo db id
	 * @return
	 */
	ProductEntity getProductByEntityId(String id);

	List<ProductEntity> getProductsByname(String name);

	/**
	 *
	 * @param id it is the product id eg: referral4.0.0.m1
	 * @return
	 */
	ProductEntity getProductByProductId(String id);

	List<ProductEntity> getProducts();

	ProductSummaryEntity createProductSummaryEntity(ProductSummaryEntity entity);

	ProductSummaryEntity updateProductSummaryEntity(ProductSummaryEntity entity);

	ProductSummaryEntity getProductSummaryEntityByName(String name);

	List<ProductSummaryEntity> getProductSummaryEntities();

	List<ProductSummaryEntity> searchProductSummaryEntityByName(String name);
}
