package co.nz.aplore.persistence.mongo.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity(value = "aplore_product", noClassnameStored = false)
@Indexes({ @Index(value = "productId", unique = true) })
public class ProductEntity extends BaseEntity {
	private static final long serialVersionUID = -8747629113693907046L;

	private String productId;

	@Embedded
	private ProductDetailsEntity productDetails;

	@Embedded
	private List<RestEndPointEntity> restEndPoints;

	@Embedded
	private List<SoapEndPointEntity> soapEndPoints;
	
	@Embedded
	private List<ClinicalEventEndPointEntity> clinicalEventEndPoints;

	public ProductEntity() {
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	public ProductDetailsEntity getProductDetails() {
		return this.productDetails;
	}

	public void setProductDetails(final ProductDetailsEntity productDetails) {
		this.productDetails = productDetails;
	}

	public List<RestEndPointEntity> getRestEndPoints() {
		return this.restEndPoints;
	}

	public void setRestEndPoints(final List<RestEndPointEntity> restEndPoints) {
		this.restEndPoints = restEndPoints;
	}

	public List<SoapEndPointEntity> getSoapEndPoints() {
		return this.soapEndPoints;
	}

	public void setSoapEndPoints(final List<SoapEndPointEntity> soapEndPoints) {
		this.soapEndPoints = soapEndPoints;
	}

	public List<ClinicalEventEndPointEntity> getClinicalEventEndPoints() {
		return clinicalEventEndPoints;
	}

	public void setClinicalEventEndPoints(
			List<ClinicalEventEndPointEntity> clinicalEventEndPoints) {
		this.clinicalEventEndPoints = clinicalEventEndPoints;
	}
	
}
