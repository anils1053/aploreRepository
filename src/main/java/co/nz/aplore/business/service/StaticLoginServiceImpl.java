package co.nz.aplore.business.service;

import co.nz.aplore.app.config.AppConfig;

public class StaticLoginServiceImpl implements LoginService {

	@Override
	public boolean isValidUser(final String name, final String password) {
		return name.equals(AppConfig.getInstance().getUsername())
				&& password.equals(AppConfig.getInstance().getPassword());
	}

}
