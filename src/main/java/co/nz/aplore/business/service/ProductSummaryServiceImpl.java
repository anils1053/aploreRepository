package co.nz.aplore.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.nz.aplore.business.converter.ProductConverter;
import co.nz.aplore.business.dto.ProductSummaryDto;
import co.nz.aplore.persistence.mongo.entity.ProductSummaryEntity;
import co.nz.aplore.persistence.mongo.repository.AploreRepositoryService;

public class ProductSummaryServiceImpl implements ProductSummaryService {

	@Inject
	private AploreRepositoryService aploreRespositoryService;

	@Override
	public ProductSummaryDto createProductSummary(final ProductSummaryDto dto) {
		final ProductSummaryEntity entity = new ProductSummaryEntity();
		entity.setName(dto.getName());
		entity.setSummary(dto.getSummary());
		final ProductSummaryEntity savedEntity = this.aploreRespositoryService.createProductSummaryEntity(entity);
		return ProductConverter.convertToProductSummaryDto(savedEntity);
	}

	@Override
	public ProductSummaryDto updateProductSummary(final ProductSummaryDto dto) {
		final ProductSummaryEntity savedEntity = this.aploreRespositoryService.getProductSummaryEntityByName(dto.getName());
		if (savedEntity != null) {
			final ProductSummaryEntity updatedEntity = this.aploreRespositoryService.updateProductSummaryEntity(savedEntity);
			return ProductConverter.convertToProductSummaryDto(updatedEntity);
		}
		return null;
	}

	@Override
	public ProductSummaryDto getProductSummaryByName(final String name) {
		final ProductSummaryEntity savedEntity = this.aploreRespositoryService.getProductSummaryEntityByName(name);
		return ProductConverter.convertToProductSummaryDto(savedEntity);
	}

	@Override
	public List<ProductSummaryDto> getProductsSummary() {
		final List<ProductSummaryEntity> entities = this.aploreRespositoryService.getProductSummaryEntities();
		final List<ProductSummaryDto> dtos = new ArrayList<>();
		for (final ProductSummaryEntity entity : entities) {
			final ProductSummaryDto dto = ProductConverter.convertToProductSummaryDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<ProductSummaryDto> searchProductSummaryByName(final String name) {
		final List<ProductSummaryEntity> entities = this.aploreRespositoryService.searchProductSummaryEntityByName(name);
		final List<ProductSummaryDto> dtos = new ArrayList<>();
		for (final ProductSummaryEntity entity : entities) {
			final ProductSummaryDto dto = ProductConverter.convertToProductSummaryDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}

}
