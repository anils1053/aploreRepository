package co.nz.aplore.persistence.mongo.entity;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class SoapEndPointEntity {
	private String method;
	private String javaClass;
	private String javaMethodName;
	private String javaDocUrl;

	public SoapEndPointEntity() {
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(final String method) {
		this.method = method;
	}

	public String getJavaClass() {
		return this.javaClass;
	}

	public void setJavaClass(final String javaClass) {
		this.javaClass = javaClass;
	}

	public String getJavaMethodName() {
		return this.javaMethodName;
	}

	public void setJavaMethodName(final String javaMethodName) {
		this.javaMethodName = javaMethodName;
	}

	public String getJavaDocUrl() {
		return this.javaDocUrl;
	}

	public void setJavaDocUrl(final String javaDocUrl) {
		this.javaDocUrl = javaDocUrl;
	}
}
