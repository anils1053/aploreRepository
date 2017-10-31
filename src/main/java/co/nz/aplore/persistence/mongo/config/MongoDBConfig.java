package co.nz.aplore.persistence.mongo.config;

import org.apache.commons.lang3.StringUtils;

public class MongoDBConfig {
	private final String hostname;
	private final int port;
	private final String username;
	private final String password;
	private final String dbName;

	public MongoDBConfig(final String hostname, final int port, final String username, final String password, final String dbName) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.dbName = dbName;
	}

	public String getHostname() {
		return this.hostname;
	}

	public int getPort() {
		return this.port;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getDbName() {
		return this.dbName;
	}

	public boolean isRequireAuth() {
		if (StringUtils.isEmpty(this.username) || StringUtils.isEmpty(this.password)) {
			return false;
		}
		return true;
	}

}
