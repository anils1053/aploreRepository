package co.nz.aplore.business.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ProductDto {
	private String productId;
	private ProductDetailsDto productDetails;
	private List<RestEndPointDto> restEndPoints;
	private List<SoapEndPointDto> soapEndPoints;
	private List<ClinicalEventEndPointDto> clinicalEventEndPoints;

	public ProductDetailsDto getProductDetails() {
		return this.productDetails;
	}

	public void setProductDetails(final ProductDetailsDto productDetails) {
		this.productDetails = productDetails;
	}

	public List<RestEndPointDto> getRestEndPoints() {
		return this.restEndPoints;
	}

	public void setRestEndPoints(final List<RestEndPointDto> restEndPoints) {
		this.restEndPoints = restEndPoints;
	}

	public String getProductId() {
		String id = this.productId;
		if (StringUtils.isBlank(this.productId)) {
			final StringBuilder builder = new StringBuilder();
			builder.append(this.productDetails.getProductName());
			builder.append(this.productDetails.getMajorVersion());
			builder.append(".");
			builder.append(this.productDetails.getMinorVersion());
			builder.append(".");
			builder.append(this.productDetails.getServicePackVersion());
			builder.append(".");
			builder.append(this.productDetails.getMilestone());
			id = builder.toString();
		}
		return id;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	public List<SoapEndPointDto> getSoapEndPoints() {
		return soapEndPoints;
	}

	public void setSoapEndPoints(List<SoapEndPointDto> soapEndPoints) {
		this.soapEndPoints = soapEndPoints;
	}

	public List<ClinicalEventEndPointDto> getClinicalEventEndPoints() {
		return clinicalEventEndPoints;
	}

	public void setClinicalEventEndPoints(
			List<ClinicalEventEndPointDto> clinicalEventEndPoints) {
		this.clinicalEventEndPoints = clinicalEventEndPoints;
	}
	
}
