package co.nz.aplore.persistence.mongo.entity;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class RestEndPointEntity {
	private String uri;
	private String method;
	private String javaClass;
	private String javaMethodName;
	private String javaDocUrl;
	@Embedded
	private List<QueryParameterEntity> queryParameters;
	@Embedded
	private List<PathParameterEntity> pathParameters;
	@Embedded
	private List<PayloadParameterEntity> payloadParameters;

	public RestEndPointEntity() {
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(final String uri) {
		this.uri = uri;
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

	public List<QueryParameterEntity> getQueryParameters() {
		return this.queryParameters;
	}

	public void setQueryParameters(final List<QueryParameterEntity> queryParameters) {
		this.queryParameters = queryParameters;
	}

	public List<PathParameterEntity> getPathParameters() {
		return this.pathParameters;
	}

	public void setPathParameters(final List<PathParameterEntity> pathParameters) {
		this.pathParameters = pathParameters;
	}

	public List<PayloadParameterEntity> getPayloadParameters() {
		return this.payloadParameters;
	}

	public void setPayloadParameters(final List<PayloadParameterEntity> payloadParameters) {
		this.payloadParameters = payloadParameters;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getUri()).append(getMethod()).append(getJavaClass()).toHashCode();
	}

	@Override
	public boolean equals(final Object object) {
		if (object == null || !(object instanceof RestEndPointEntity)) {
			return false;
		}
		if (object == this) {
			return true;
		}
		final RestEndPointEntity other = (RestEndPointEntity) object;
		return new EqualsBuilder().append(getUri(), other.getUri()).append(getMethod(), other.getMethod())
				.append(getJavaClass(), other.getJavaClass()).isEquals();
	}

}
