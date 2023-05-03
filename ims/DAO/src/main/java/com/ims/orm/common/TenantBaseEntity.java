
package com.ims.orm.common;

import com.ims.orm.entity.audit.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * @author Intesar Mohammed
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class TenantBaseEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    private Tenant tenant;

 //   @Indexed("tenantId_s")
    private String tenantPk;

    @PrePersist
    @PreUpdate
    public void mergeTenentId() {
        if (tenant != null && StringUtils.isEmpty(this.tenantPk)) {
            this.setTenantPk(tenant.getId());
        }
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getTenantPk() {
        return tenantPk;
    }

    public void setTenantPk(String tenantPk) {
        this.tenantPk = tenantPk;
    }

}
