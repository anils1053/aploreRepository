package co.nz.aplore.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

public abstract class BaseResource {
	@Context
	private SecurityContext securityContext;

	@Context
	private HttpServletRequest request;

	protected SecurityContext getSecurityContext() {
		return this.securityContext;
	}

	protected HttpServletRequest getRequest() {
		return this.request;
	}
}
