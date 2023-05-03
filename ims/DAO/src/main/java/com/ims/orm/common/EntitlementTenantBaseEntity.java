/* COPYRIGHT (C) 2017 DCHQ. All Rights Reserved. */
package com.ims.orm.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.solr.core.mapping.Indexed;

import com.dchq.orm.entity.security.EntitlementType;
import com.dchq.orm.entity.security.Tenant;

/**
 * @author Faizan Mohammad
 * @since  08/12/2017
 *
 * Use this class when entitlement is required at Tenant level.
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class EntitlementTenantBaseEntity extends EntitlementBaseEntity {
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Tenant> entitledTenants;

    @Indexed("entitled_tenants_ss")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> entitledTenantsPks = new HashSet<>();
    
    @PrePersist
    @PreUpdate
	public void mergeEntitleId() {
		if (this.getEntitlementType() == null) {
			return;
		}
		if (this.entitledTenantsPks != null) {
			this.entitledTenantsPks.clear();
		}
		if (this.getEntitlementType() != EntitlementType.TENANTS && this.getEntitlementType() != EntitlementType.TENANTS_CUSTOM) {
			 if (CollectionUtils.isNotEmpty(this.entitledTenants)) {
	                this.entitledTenants.clear();
	            }
		}
		if (CollectionUtils.isNotEmpty(this.entitledTenants)) {
			for (Tenant t : this.entitledTenants) {
				this.entitledTenantsPks.add(t.getId());
			}
		}
		super.mergeEntitleId();
	}

	public Set<Tenant> getEntitledTenants() {
		return entitledTenants;
	}

	public void setEntitledTenants(Set<Tenant> entitledTenants) {
		this.entitledTenants = entitledTenants;
		
	}

	public Set<String> getEntitledTenantsPks() {
		return entitledTenantsPks;
	}

	public void setEntitledTenantsPks(Set<String> entitledTenantsPks) {
		this.entitledTenantsPks = entitledTenantsPks;
	}

}
