package co.nz.aplore.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import co.nz.aplore.business.converter.ProductConverter;
import co.nz.aplore.business.dto.ClinicalEventEndPointDto;
import co.nz.aplore.business.dto.ProductDetailsDto;
import co.nz.aplore.business.dto.ProductDto;
import co.nz.aplore.business.dto.ProductSummaryDto;
import co.nz.aplore.business.dto.RestEndPointDto;
import co.nz.aplore.business.dto.SoapEndPointDto;
import co.nz.aplore.business.service.ProductService;
import co.nz.aplore.business.service.ProductSummaryService;

import com.google.gson.Gson;

@Path("/")
public class AploreResource extends BaseResource {

	private static final String PARAM_NAME = "name";
	private static final String PARAM_ID = "id";

	@Inject
	private ProductService productService;

	@Inject
	private ProductSummaryService productSummaryService;

	@POST
	@Path("/publish")
	@Produces({ MediaType.APPLICATION_JSON })
	public String uploadApi(final String apiJson) {
		final Gson gson = new Gson();
		final ProductDto productDto = gson.fromJson(apiJson, ProductDto.class);
		return this.productService.updateOrCreateProduct(productDto);
	}

	@GET
	@Path("/list/product")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductSummaryDto> getProductsSummary() {
		return this.productSummaryService.getProductsSummary();
	}

	@GET
	@Path("/search/product")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductSummaryDto> searchProduct(@QueryParam(PARAM_NAME) final String name) {
		final List<ProductDto> products = this.productService.getProducts();

		final List<ProductSummaryDto> productSummaryDtos = new ArrayList<>();
		for (final ProductDto dto : products) {
			final ProductDto filteredDto = filterProductWithSearchCriterion(dto, name, true);
			if (filteredDto != null) {
				final ProductSummaryDto summaryDto = new ProductSummaryDto();
				summaryDto.setName(filteredDto.getProductDetails().getProductName());
				if (!productSummaryDtos.contains(summaryDto)) {
					productSummaryDtos.add(summaryDto);
				}
			}
		}
		return productSummaryDtos;
	}

	@GET
	@Path("/search/{id}/product")
	@Produces({ MediaType.APPLICATION_JSON })
	public ProductDto searchProduct(@PathParam(PARAM_ID) final String id, @QueryParam(PARAM_NAME) final String name) {
		final ProductDto product = this.productService.getProductByEntityId(id);
		return filterProductWithSearchCriterion(product, name, false);
	}

	private ProductDto filterProductWithSearchCriterion(final ProductDto dto, final String searchWords, final boolean quickSearch) {
		boolean isMatch = false;

		final ProductDetailsDto detailsDto = dto.getProductDetails();
		if (StringUtils.containsIgnoreCase(detailsDto.getProductName(), searchWords)) {
			isMatch = true;
			if (quickSearch) {
				return dto;
			}
		}

		final List<RestEndPointDto> restEndPoints = dto.getRestEndPoints();
		final List<RestEndPointDto> newEndPoints = new ArrayList<RestEndPointDto>();
		if (restEndPoints != null) {
			for (final RestEndPointDto rest : restEndPoints) {
				if (StringUtils.containsIgnoreCase(rest.getJavaClass(), searchWords) ||
						StringUtils.containsIgnoreCase(rest.getMethod(), searchWords) ||
						StringUtils.containsIgnoreCase(rest.getUri(), searchWords)) {
					isMatch = true;
					if (quickSearch) {
						return dto;
					} else {
						newEndPoints.add(rest);
					}
				}
			}
		}

		final List<SoapEndPointDto> soapEndPoints = dto.getSoapEndPoints();
		final List<SoapEndPointDto> newSoapEndPoints = dto.getSoapEndPoints();
		if (soapEndPoints != null) {
			for (final SoapEndPointDto soapDto : soapEndPoints) {
				if (StringUtils.containsIgnoreCase(soapDto.getJavaClass(), searchWords) ||
						StringUtils.containsIgnoreCase(soapDto.getMethod(), searchWords) ||
						StringUtils.containsIgnoreCase(soapDto.getJavaMethodName(), searchWords)) {
					isMatch = true;
					if (quickSearch) {
						return dto;
					} else {
						newSoapEndPoints.add(soapDto);
					}
				}
			}
		}

		final List<ClinicalEventEndPointDto> clinicalEventEndPoints = dto.getClinicalEventEndPoints();
		final List<ClinicalEventEndPointDto> newClinicalEventEndPoints = dto.getClinicalEventEndPoints();
		if (clinicalEventEndPoints != null) {
			for (final ClinicalEventEndPointDto clinicalDto : clinicalEventEndPoints) {
				if (StringUtils.containsIgnoreCase(clinicalDto.getJavaClass(), searchWords) ||
						StringUtils.containsIgnoreCase(clinicalDto.getPackageName(), searchWords)) {
					isMatch = true;
					if (quickSearch) {
						return dto;
					} else {
						newClinicalEventEndPoints.add(clinicalDto);
					}
				}
			}
		}

		if (isMatch) {
			return ProductConverter.copyProudctDto(dto.getProductId(), dto.getProductDetails(), newEndPoints, newSoapEndPoints,
					newClinicalEventEndPoints);
		} else {
			return null;
		}
	}
}
