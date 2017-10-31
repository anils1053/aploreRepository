package co.nz.aplore.persistence.mongo.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity(value = "aplore_productSummary", noClassnameStored = false)
@Indexes({ @Index(value = "name", unique = true) })
public class ProductSummaryEntity extends BaseEntity {
	private static final long serialVersionUID = 7543037532635623989L;
	private String name;
	private String summary;

	public ProductSummaryEntity() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}
}
