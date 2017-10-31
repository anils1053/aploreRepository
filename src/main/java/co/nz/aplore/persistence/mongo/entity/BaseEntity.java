package co.nz.aplore.persistence.mongo.entity;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3159986625500875499L;

	@Id
	@JsonIgnore
	private ObjectId entityId;

	/**
	 * We'll only provide getters for these attributes, setting is done in
	 *
	 * @PrePersist.
	 */
	private Date creationDate;

	private Date lastChange;

	/**
	 * No getters and setters required, the version is handled internally.
	 */
	@Version
	@JsonIgnore
	private long version;

	private boolean enabled = true;

	public BaseEntity() {
		super();
	}

	public ObjectId getEntityId() {
		return this.entityId;
	}

	public void setEntityId(final ObjectId entityId) {
		this.entityId = entityId;
	}

	public String getId() {
		return getEntityId() == null ? null : getEntityId().toString();
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public Date getLastChange() {
		return this.lastChange;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	@PrePersist
	public void prePersist() {
		this.creationDate = (this.creationDate == null) ? new Date() : this.creationDate;
		this.lastChange = (this.lastChange == null) ? this.creationDate : new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.entityId == null) ? 0 : this.entityId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final BaseEntity other = (BaseEntity) obj;
		if (this.entityId == null) {
			if (other.entityId != null) {
				return false;
			}
		} else if (!this.entityId.equals(other.entityId)) {
			return false;
		}

		return true;
	}
}
