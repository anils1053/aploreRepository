package co.nz.aplore.action;

import java.util.List;

import javax.inject.Inject;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import co.nz.aplore.business.dto.ProductDetailsDto;
import co.nz.aplore.business.service.ProductService;

public class ProductDetailsActionBean extends BaseActionBean {
	public String name;
	public List<ProductDetailsDto> productDetailsDto;

	@Inject
	private ProductService productService;

	@Before(on = { "view" })
	public void init() {
		this.productDetailsDto = this.productService.getProductByName(this.name);
	}

	@DefaultHandler
	public Resolution view() throws IllegalAccessException {
		return new ForwardResolution(this.getViewPage());
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<ProductDetailsDto> getProductDetailsDto() {
		return this.productDetailsDto;
	}
}
