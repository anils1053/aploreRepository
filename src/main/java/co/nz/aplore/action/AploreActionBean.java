package co.nz.aplore.action;

import javax.inject.Inject;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import co.nz.aplore.business.service.ProductService;

public class AploreActionBean extends BaseActionBean {

	public int totalProducts;
	public int totalProductsVersions;
	public int totalProductApis;

	@Inject
	private ProductService productService;

	@DefaultHandler
	public Resolution view() throws IllegalAccessException {
		return new ForwardResolution(this.getViewPage());
	}

	public Resolution logout() {
		getContext().getRequest().getSession().setAttribute(IndexActionBean.LOGGEDIN_SESSION_ID, false);
		return new RedirectResolution(IndexActionBean.class);
	}

	public int getTotalProducts() {
		return this.productService.getTotalProductCount();
	}

	public int getTotalProductsVersions() {
		return this.productService.getTotalProductsVersions();
	}

	public int getTotalProductApis() {
		return this.productService.getTotalProductAPIsCount();
	}

}
