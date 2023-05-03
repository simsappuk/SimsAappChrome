package com.ims.orm.common;


import com.ims.orm.model.Users;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Intesar Mohammed
 * @author Mohammed Shoukath Ali
 */
//@Cacheable(value = "userCache"  )
@MappedSuperclass
@SuppressWarnings("serial")
public class EntitlementBaseEntity extends TenantBaseEntity {

 //   @Indexed("entitlement_type_s")
    @Enumerated(EnumType.STRING)
    private EntitlementType entitlementType;

    //@Indexed("entitlement_t")
    //private String entitlement;

    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,  region="entitlement_region")
  //  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  //  private Set<Organization> entitledOrgs;

    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,  region="entitlement_region")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<UserGroup> entitledUserGroups;

    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,  region="entitlement_region")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Users> entitledUsers;

   // @Indexed("entitled_users_ss")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> entitledUsersPks = new HashSet<>();

   // @Indexed("entitled_groups_ss")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> entitledGroupsPks = new HashSet<>();

    @PrePersist
    @PreUpdate
    public void mergeEntitleId() {
        if (this.entitlementType == null) {
            return;
        }
        //this.entitlement = this.entitlementType.name();
        if (this.entitledGroupsPks != null) {
            this.entitledGroupsPks.clear();
        }
        if (this.entitledUsersPks != null) {
            this.entitledUsersPks.clear();
        }
        if (this.entitlementType == EntitlementType.PUBLIC || this.entitlementType == EntitlementType.OWNER) {
            if (CollectionUtils.isNotEmpty(this.entitledUserGroups)) {
                this.entitledUserGroups.clear();
            }
            if (CollectionUtils.isNotEmpty(this.entitledUsers)) {
                this.entitledUsers.clear();
            }
        }
        if (CollectionUtils.isNotEmpty(this.entitledUserGroups)) {
            for (UserGroup ug : this.entitledUserGroups) {
                this.entitledGroupsPks.add(ug.getId());
            }
        }
        if (CollectionUtils.isNotEmpty(this.entitledUsers)) {
            for (Users u : this.entitledUsers) {
                this.entitledUsersPks.add(u.getId());
            }
        }
    }

    public EntitlementType getEntitlementType() {
        return entitlementType;
    }

    public void setEntitlementType(EntitlementType entitlementType) {
        this.entitlementType = entitlementType;
    }
/*
    public Set<Organization> getEntitledOrgs() {
        return entitledOrgs;
    }

    public void setEntitledOrgs(Set<Organization> entitledOrgs) {
        this.entitledOrgs = entitledOrgs;
    }
*/
    public Set<UserGroup> getEntitledUserGroups() {
        return entitledUserGroups;
    }

    public void setEntitledUserGroups(Set<UserGroup> entitledUserGroups) {
        this.entitledUserGroups = entitledUserGroups;
    }

    public Set<Users> getEntitledUsers() {
        return entitledUsers;
    }

    public void setEntitledUsers(Set<Users> entitledUsers) {
        this.entitledUsers = entitledUsers;
    }

    public Set<String> getEntitledUsersPks() {
        return entitledUsersPks;
    }

    public void setEntitledUsersPks(Set<String> entitledUsersPks) {
        this.entitledUsersPks = entitledUsersPks;
    }

    public Set<String> getEntitledGroupsPks() {
        return entitledGroupsPks;
    }

    public void setEntitledGroupsPks(Set<String> entitledGroupsPks) {
        this.entitledGroupsPks = entitledGroupsPks;
    }

}
