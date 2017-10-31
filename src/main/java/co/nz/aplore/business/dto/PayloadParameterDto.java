package co.nz.aplore.business.dto;

public class PayloadParameterDto {
	private String parameterType;
	private String javaType;

	public String getParameterType() {
		return this.parameterType;
	}

	public void setParameterType(final String parameterType) {
		this.parameterType = parameterType;
	}

	public String getJavaType() {
		return this.javaType;
	}

	public void setJavaType(final String javaType) {
		this.javaType = javaType;
	}
}
