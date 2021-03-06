package co.nz.aplore.persistence.mongo.entity;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class ProductDetailsEntity {
	private String majorVersion;
	private String minorVersion;
	private String servicePackVersion;
	private String milestone;
	private String productName;
	private String javadocBaseUrl;

	public ProductDetailsEntity() {
	}

	public String getMajorVersion() {
		return this.majorVersion;
	}

	public void setMajorVersion(final String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public String getMinorVersion() {
		return this.minorVersion;
	}

	public void setMinorVersion(final String minorVersion) {
		this.minorVersion = minorVersion;
	}

	public String getServicePackVersion() {
		return this.servicePackVersion;
	}

	public void setServicePackVersion(final String servicePackVersion) {
		this.servicePackVersion = servicePackVersion;
	}

	public String getMilestone() {
		return this.milestone;
	}

	public void setMilestone(final String milestone) {
		this.milestone = milestone;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public String getJavadocBaseUrl() {
		return this.javadocBaseUrl;
	}

	public void setJavadocBaseUrl(final String javadocBaseUrl) {
		this.javadocBaseUrl = javadocBaseUrl;
	}
}
