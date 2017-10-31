/**
 *
 */
package co.nz.aplore.persistence.mongo.module;

import co.nz.aplore.persistence.mongo.repository.AploreRepositoryService;
import co.nz.aplore.persistence.mongo.repository.AploreRepositoryServiceImpl;

import com.google.inject.AbstractModule;

/**
 * @author yizhangy
 *
 */
public class RepositoryModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AploreRepositoryService.class).to(AploreRepositoryServiceImpl.class);
	}
}
