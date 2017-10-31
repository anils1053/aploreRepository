package co.nz.aplore.business.dto;

public class ClinicalEventEndPointDto {
	
	private String javaClass;

	private String packageName;

	private String javadocLink;

	public String getJavaClass() {
		return this.javaClass;
	}

	public void setJavaClass(final String javaClass) {
		this.javaClass = javaClass;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(final String packageName) {
		this.packageName = packageName;
	}

	public String getJavadocLink() {
		return this.javadocLink;
	}

	public void setJavadocLink(final String javadocLink) {
		this.javadocLink = javadocLink;
	}

}
