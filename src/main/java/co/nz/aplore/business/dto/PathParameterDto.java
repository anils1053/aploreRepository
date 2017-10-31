package co.nz.aplore.business.dto;

public class PathParameterDto {
	private String parameterType;
	private String javaType;
	private String name;

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

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
