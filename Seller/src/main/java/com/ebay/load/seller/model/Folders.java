package com.ebay.load.seller.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "folders")
public class Folders {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    private String name;
    private String ownerId;
    private String parentId;

    @ElementCollection
    private List<String> entitleOwner;

    private String accountId;
    private String entitleReadOnly;
    private String entitleEdit;
    private Date createdDate;
    private Date modifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getEntitleOwner() {
        return entitleOwner;
    }

    public void setEntitleOwner(List<String> entitleOwner) {
        this.entitleOwner = entitleOwner;
    }

    public String getEntitleReadOnly() {
        return entitleReadOnly;
    }

    public void setEntitleReadOnly(String entitleReadOnly) {
        this.entitleReadOnly = entitleReadOnly;
    }

    public String getEntitleEdit() {
        return entitleEdit;
    }

    public void setEntitleEdit(String entitleEdit) {
        this.entitleEdit = entitleEdit;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
