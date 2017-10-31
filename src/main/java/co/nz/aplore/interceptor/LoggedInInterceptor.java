package co.nz.aplore.interceptor;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import co.nz.aplore.action.IndexActionBean;

@Intercepts(LifecycleStage.HandlerResolution)
public class LoggedInInterceptor implements Interceptor {

	@Override
	public Resolution intercept(ExecutionContext context) throws Exception {
		Resolution re = context.proceed();
		if (context.getActionBean().getClass().getSimpleName().equals("IndexActionBean")) {
			return re;
		}
		
		final Object authenticationAttr = context.getActionBeanContext().getRequest().getSession().getAttribute(IndexActionBean.LOGGEDIN_SESSION_ID);
		final boolean isValid = authenticationAttr != null ? (boolean) authenticationAttr : false;
		
		if (!isValid) {
			return new RedirectResolution(IndexActionBean.class);
		}
		
		return re;
	}

}
