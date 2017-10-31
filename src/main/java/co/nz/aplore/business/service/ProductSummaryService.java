package co.nz.aplore.business.service;

import java.util.List;

import co.nz.aplore.business.dto.ProductSummaryDto;

public interface ProductSummaryService {

	ProductSummaryDto createProductSummary(ProductSummaryDto dto);

	ProductSummaryDto updateProductSummary(ProductSummaryDto dto);

	ProductSummaryDto getProductSummaryByName(String name);

	List<ProductSummaryDto> getProductsSummary();

	List<ProductSummaryDto> searchProductSummaryByName(String name);

}
