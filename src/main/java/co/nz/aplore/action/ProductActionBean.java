package co.nz.aplore.action;

import javax.inject.Inject;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import co.nz.aplore.business.dto.ProductDto;
import co.nz.aplore.business.service.ProductService;

public class ProductActionBean extends BaseActionBean {
	private String id;
	private ProductDto productDto;

	@Inject
	private ProductService productService;

	@Before(on = { "view" })
	public void init() {
		this.productDto = this.productService.getProductByEntityId(this.id);
	}

	@DefaultHandler
	public Resolution view() throws IllegalAccessException {
		return new ForwardResolution(this.getViewPage());
	}

	public ProductDto getProductDto() {
		return this.productDto;
	}

	public void setId(final String id) {
		this.id = id;
	}
}
