package co.nz.aplore.business.dto;

import java.util.List;

public class RestEndPointDto {
	private String uri;
	private String method;
	private String javaClass;
	private String javaMethodName;
	private String javaDocUrl;
	private List<QueryParameterDto> queryParameters;
	private List<PathParameterDto> pathParameters;
	private List<PayloadParameterDto> payloadParameters;

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

	public List<QueryParameterDto> getQueryParameters() {
		return this.queryParameters;
	}

	public void setQueryParameters(final List<QueryParameterDto> queryParameters) {
		this.queryParameters = queryParameters;
	}

	public List<PathParameterDto> getPathParameters() {
		return this.pathParameters;
	}

	public void setPathParameters(final List<PathParameterDto> pathParameters) {
		this.pathParameters = pathParameters;
	}

	public List<PayloadParameterDto> getPayloadParameters() {
		return this.payloadParameters;
	}

	public void setPayloadParameters(final List<PayloadParameterDto> payloadParameters) {
		this.payloadParameters = payloadParameters;
	}

}
