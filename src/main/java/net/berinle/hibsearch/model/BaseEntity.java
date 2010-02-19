package net.berinle.hibsearch.model;
import javax.persistence.Column;
import javax.persistence.Version;


/**
 * This is the super class for all entities that are 
 * persisted to the datastore
 * 
 * It keeps common assessors common to all entities
 * 
 * @author berinle
 *
 */
public class BaseEntity {

	//the primary key
	private Long id;
	private Integer version; //used for optimistic locking
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name="version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
