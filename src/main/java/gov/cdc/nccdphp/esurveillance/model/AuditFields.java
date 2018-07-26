package gov.cdc.nccdphp.esurveillance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
/**
 * Audit fields abstract class inherited by other model classes. 
 * @CreatedDate, @LastModifiedDate are populated by Spring Data
 * @CretedBy, @LastModifiedBy are populated by AuditAwareImpl class using Spring Data 
 * @JsonIgnore ignores the fields being populated by @RequestBody by Jackson and Swagger docs
 * 
 * @author hxo5
 *
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditFields {
	@CreatedBy
	@Column (name="created_by")
	@JsonProperty("_created_by")
	private String createdBy;
	
	@CreatedDate
	@Column (name="created_datetime")
	@JsonProperty("_created_datetime")
	private Date createdTime;
	
	@LastModifiedBy
	@Column (name="updated_by")
	@JsonProperty("_updated_by")
	private String updatedBy;
	
	@LastModifiedDate
	@Column (name="updated_datetime")
	@JsonProperty("_updated_datetime")
	private Date updatedTime;
	
    @Version
    @Column(name="version", columnDefinition = "integer DEFAULT 0", nullable = false)
	@JsonProperty("_record_version")
    private long version = 0L;

    @Column(name="active", nullable = false)
    @JsonProperty("_active")
	private boolean active = true;
}
