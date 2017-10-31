package co.nz.aplore.persistence.mongo.entity;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class PayloadParameterEntity {
	private String parameterType;
	private String javaType;

	public PayloadParameterEntity() {
	}

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
