package co.nz.aplore.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.nz.aplore.business.converter.ProductConverter;
import co.nz.aplore.business.dto.ProductDetailsDto;
import co.nz.aplore.business.dto.ProductDto;
import co.nz.aplore.business.dto.ProductSummaryDto;
import co.nz.aplore.persistence.mongo.entity.ClinicalEventEndPointEntity;
import co.nz.aplore.persistence.mongo.entity.ProductEntity;
import co.nz.aplore.persistence.mongo.entity.ProductSummaryEntity;
import co.nz.aplore.persistence.mongo.entity.RestEndPointEntity;
import co.nz.aplore.persistence.mongo.entity.SoapEndPointEntity;
import co.nz.aplore.persistence.mongo.repository.AploreRepositoryService;

public class ProductServiceImpl implements ProductService {

	@Inject
	private AploreRepositoryService aploreRespositoryService;

	@Inject
	private ProductSummaryService ProductSummaryService;

	@Override
	public ProductDto getProductByEntityId(final String id) {
		return ProductConverter.convertToProductDto(this.aploreRespositoryService.getProductByEntityId(id));
	}

	@Override
	public ProductDto getProductByProductId(final String id) {
		return ProductConverter.convertToProductDto(this.aploreRespositoryService.getProductByProductId(id));
	}

	@Override
	public List<ProductDetailsDto> getProductsDetails() {
		final List<ProductEntity> entities = this.aploreRespositoryService.getProducts();
		final List<ProductDetailsDto> dtos = new ArrayList<>();
		for (final ProductEntity entity : entities) {
			dtos.add(ProductConverter.convertToProductDetailsDto(entity));
		}
		return dtos;
	}

	@Override
	public List<ProductDto> getProducts() {
		final List<ProductEntity> entities = this.aploreRespositoryService.getProducts();
		final List<ProductDto> dtos = new ArrayList<>();
		for (final ProductEntity entity : entities) {
			dtos.add(ProductConverter.convertToProductDto(entity));
		}
		return dtos;
	}

	@Override
	public String updateOrCreateProduct(final ProductDto dto) {
		final ProductEntity entity = this.aploreRespositoryService.getProductByProductId(dto.getProductId());
		ProductEntity persistedEntity;
		if (entity != null) {
			final ProductEntity updatingEntity = ProductConverter.convertToProductEntity(dto, entity);
			persistedEntity = this.aploreRespositoryService.updateProductEntity(updatingEntity);
		} else {
			final ProductEntity savedEntity = ProductConverter.convertToProductEntity(dto, new ProductEntity());
			persistedEntity = this.aploreRespositoryService.createProductEntity(savedEntity);
		}

		// We need to create product summary as well if it not available.
		final String id = persistedEntity != null ? persistedEntity.getId() : "";
		if (persistedEntity != null) {
			final String productName = persistedEntity.getProductDetails().getProductName();
			final ProductSummaryEntity productSummaryEntity = this.aploreRespositoryService.getProductSummaryEntityByName(productName);
			if (productSummaryEntity == null) {
				final ProductSummaryDto summaryDto = new ProductSummaryDto();
				summaryDto.setName(productName);
				summaryDto.setSummary("");
				this.ProductSummaryService.createProductSummary(summaryDto);
			}
		}
		return id;
	}

	@Override
	public List<ProductDetailsDto> getProductByName(final String name) {
		final List<ProductEntity> entities = this.aploreRespositoryService.getProductsByname(name);
		final List<ProductDetailsDto> dtos = new ArrayList<>();
		for (final ProductEntity entity : entities) {
			dtos.add(ProductConverter.convertToProductDetailsDto(entity));
		}
		return dtos;
	}

	@Override
	public List<ProductSummaryDto> getProductSummary() {
		final List<ProductSummaryEntity> entities = this.aploreRespositoryService.getProductSummaryEntities();
		final List<ProductSummaryDto> dtos = new ArrayList<>();
		for (final ProductSummaryEntity entity : entities) {
			dtos.add(ProductConverter.convertToProductSummaryDto(entity));
		}
		return dtos;
	}

	@Override
	public int getTotalProductCount() {
		return this.aploreRespositoryService.getProductSummaryEntities().size();
	}

	@Override
	public int getTotalProductsVersions() {
		return this.aploreRespositoryService.getProducts().size();
	}

	@Override
	public int getTotalProductAPIsCount() {
		final List<ProductEntity> products = this.aploreRespositoryService.getProducts();
		int apiCounter = 0;
		for (final ProductEntity product : products) {
			final List<RestEndPointEntity> restEndPoints = product.getRestEndPoints();
			if (restEndPoints != null) {
				apiCounter = apiCounter + restEndPoints.size();
			}
			final List<SoapEndPointEntity> soapEndPoints = product.getSoapEndPoints();
			if (soapEndPoints != null) {
				apiCounter = apiCounter + soapEndPoints.size();
			}

			final List<ClinicalEventEndPointEntity> clinicalEventEndPoints = product.getClinicalEventEndPoints();
			if (clinicalEventEndPoints != null) {
				apiCounter = apiCounter + clinicalEventEndPoints.size();
			}
		}
		return apiCounter;
	}

}
