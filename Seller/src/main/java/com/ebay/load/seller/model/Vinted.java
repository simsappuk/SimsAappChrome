package com.ebay.load.seller.model;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="vinted")
public class Vinted {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String itemId;
    private String url;
    private String title;
    private String ownerId;
    private String createdAt;

    private double originalPriceNumeric;
    private String itemClosingAction;

    public String getitemClosingAction() {
        return itemClosingAction;
    }

    public void setItemClosingAction(String itemClosingAction) {
        this.itemClosingAction = itemClosingAction;
    }



    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    public Double getoriginalPriceNumeric() {return originalPriceNumeric;
    }

    public void setOriginalPriceNumeric(Double originalPriceNumeric) {this.originalPriceNumeric = originalPriceNumeric;
    }



    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    private String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}