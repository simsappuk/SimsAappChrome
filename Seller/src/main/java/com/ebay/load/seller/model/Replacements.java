package com.ebay.load.seller.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="replacements")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Replacements {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String title;
    private Integer recordNumber;
    private String orderId;
    private String buyerName;
    private String buyerStreet1;
    private String buyerStreet2;
    private String buyerCity;
    private String buyerState;
    private String buyerPostalCode;
    private String buyerCountry;
    private Date updatedOn;
    private Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Integer recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getBuyerStreet1() {
        return buyerStreet1;
    }

    public void setBuyerStreet1(String buyerStreet1) {
        this.buyerStreet1 = buyerStreet1;
    }

    public String getBuyerStreet2() {
        return buyerStreet2;
    }

    public void setBuyerStreet2(String buyerStreet2) {
        this.buyerStreet2 = buyerStreet2;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public String getBuyerState() {
        return buyerState;
    }

    public void setBuyerState(String buyerState) {
        this.buyerState = buyerState;
    }

    public String getBuyerPostalCode() {
        return buyerPostalCode;
    }

    public void setBuyerPostalCode(String buyerPostalCode) {
        this.buyerPostalCode = buyerPostalCode;
    }

    public String getBuyerCountry() {
        return buyerCountry;
    }

    public void setBuyerCountry(String buyerCountry) {
        this.buyerCountry = buyerCountry;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
