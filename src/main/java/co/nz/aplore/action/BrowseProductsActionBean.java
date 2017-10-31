package co.nz.aplore.action;

import java.util.List;

import javax.inject.Inject;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import co.nz.aplore.business.dto.ProductSummaryDto;
import co.nz.aplore.business.service.ProductService;

public class BrowseProductsActionBean extends BaseActionBean {
	public List<ProductSummaryDto> productSummaryDtos;

	@Inject
	private ProductService productService;

	@Before(on = { "view" })
	public void init() {
		this.productSummaryDtos = this.productService.getProductSummary();
	}

	@DefaultHandler
	public Resolution view() throws IllegalAccessException {
		return new ForwardResolution(this.getViewPage());
	}

	public List<ProductSummaryDto> getProductSummaryDtos() {
		return this.productSummaryDtos;
	}

	public void setProductSummaryDtos(final List<ProductSummaryDto> productSummaryDtos) {
		this.productSummaryDtos = productSummaryDtos;
	}
}
