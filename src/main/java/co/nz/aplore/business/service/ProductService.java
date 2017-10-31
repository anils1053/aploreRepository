package co.nz.aplore.business.service;

import java.util.List;

import co.nz.aplore.business.dto.ProductDetailsDto;
import co.nz.aplore.business.dto.ProductDto;
import co.nz.aplore.business.dto.ProductSummaryDto;

public interface ProductService {

	/**
	 * If it is new v
	 *
	 * @param dto
	 * @return DB entity Id
	 */
	String updateOrCreateProduct(ProductDto dto);

	/**
	 *
	 * @param id it is the mongo db id
	 * @return
	 */
	ProductDto getProductByEntityId(String id);

	List<ProductDetailsDto> getProductByName(String name);

	/**
	 *
	 * @param id it is the product id eg: referral4.0.0.m1
	 * @return
	 */
	ProductDto getProductByProductId(String id);

	/**
	 *
	 * @return product summarys
	 */
	List<ProductSummaryDto> getProductSummary();

	/**
	 *
	 * @return only returns summary of the Product without any API info
	 */
	List<ProductDetailsDto> getProductsDetails();

	/**
	 *
	 * @return the total count of product that are currently available in Aplore
	 */
	int getTotalProductCount();

	/**
	 *
	 * @return the total count of product versions that are currently available in Aplore
	 */
	int getTotalProductsVersions();

	/**
	 *
	 * @return the total count of product Apis that are currently available in Aplore
	 */
	int getTotalProductAPIsCount();

	List<ProductDto> getProducts();

}
