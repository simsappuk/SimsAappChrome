
package com.ims.orm.entity.audit;

import com.ims.orm.common.Users;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Intesar Mohammed
 */
@MappedSuperclass
public class BaseEntity extends BasicBaseEntity {

    private String uuid = null;
   // @Indexed("inactive_b")
    private Boolean inactive;
  //  @Indexed("deleted_b")
    private Boolean deleted;
    private Integer lockVersion;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    private Users owner;

  //  @Indexed("ownerid_s")
    private String ownerPk;

    @PrePersist
    @PreUpdate
    public void preMerge() {
        if (this.owner != null && StringUtils.isEmpty(this.ownerPk)) {
            this.ownerPk = this.owner.getId();
        }
        if (StringUtils.isEmpty(this.uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }

        if (this.inactive == null) {
            this.inactive = false;
        }

        if (this.deleted == null) {
            this.deleted = false;
        }

        if (this.lockVersion == null) {
            this.lockVersion = 1;
        }

     /*   if (this.getCreatedDate() == null) {
            setCreatedDate(new DateTime());
        }
*/
        //if (this.getLastModifiedDate() == null) {
        //setLastModifiedDate(new DateTime());
        //}
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    /**
     * @return the owner
     */
    public Users getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Users owner) {
        this.owner = owner;
    }

    /**
     * @return the ownerPk
     */
    public String getOwnerPk() {
        return ownerPk;
    }

    /**
     * @param ownerPk
     */
    public void setOwnerPk(String ownerPk) {
        this.ownerPk = ownerPk;
    }

}
