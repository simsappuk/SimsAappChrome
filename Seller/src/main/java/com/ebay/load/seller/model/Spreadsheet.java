package com.ebay.load.seller.model;

import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="spreadsheet")
public class Spreadsheet extends ResponseEntity<Spreadsheet> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String name;
    private String folderId;
    private String ownerId;

    @ElementCollection
    private List<String> entitleOwner;
    private String entitleReadOnly;
    private String entitleEdit;
    private Date createdDate;
    private String duplicateId;
    private String accountId;
    private Date modifiedDate;
    private String sheetNumber;
    private String channelId;
    private String channelName;

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

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
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

    public String getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(String sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public String getDuplicateId() {
        return duplicateId;
    }

    public void setDuplicateId(String duplicateId) {
        this.duplicateId = duplicateId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
