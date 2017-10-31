/**
 *
 */
package co.nz.aplore.resource.application;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import co.nz.aplore.web.guice.module.WebModule;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.inject.Guice;

/**
 * @author yizhangy
 *
 */

public class AploreApplication extends ResourceConfig {

	private static final String REST_RESOURCES_PACKAGES_SCAN = "co.nz.aplore.resource";

	@Inject
	public AploreApplication(final ServiceLocator serviceLocator) {
		packages(REST_RESOURCES_PACKAGES_SCAN);

		register(RolesAllowedDynamicFeature.class);
		register(MultiPartFeature.class);
		register(JacksonJaxbJsonProvider.class);

		// Guice integration
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		final GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(Guice.createInjector(new WebModule()));
	}

}
