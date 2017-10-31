package co.nz.aplore.action;

import javax.inject.Inject;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import co.nz.aplore.business.service.LoginService;

public class IndexActionBean extends BaseActionBean {

	private String username;
	private String password;
	private String message = "";

	public static String LOGGEDIN_SESSION_ID = "aploreloginSession";

	@Inject
	private LoginService loginService;

	@DefaultHandler
	public Resolution view() throws IllegalAccessException {
		final Object authenticationAttr = getContext().getRequest().getSession().getAttribute(IndexActionBean.LOGGEDIN_SESSION_ID);
		final boolean isValid = authenticationAttr != null ? (boolean) authenticationAttr : false;
		if (isValid) {
			return new RedirectResolution(AploreActionBean.class);
		} else {
			return new ForwardResolution(this.getViewPage());
		}
	}

	public Resolution login() throws IllegalAccessException {
		final boolean isValidUser = this.loginService.isValidUser(this.username, this.password);
		if (isValidUser) {
			this.message = "";
			getContext().getRequest().getSession().setAttribute(LOGGEDIN_SESSION_ID, true);
			return new RedirectResolution(AploreActionBean.class);
		} else {
			this.message = "Username/Password is invalid.";
			return new ForwardResolution(this.getViewPage());
		}
	}

	public String getMessage() {
		return this.message;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}