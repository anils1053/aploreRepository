package co.nz.aplore.business.dto;


public class SoapEndPointDto {
	private String method;
	private String javaClass;
	private String javaMethodName;
	private String javaDocUrl;

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
