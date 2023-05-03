package com.ebay.load.seller.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="activeListings")
public class ActiveListings {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="id")
    private String id;
    private String itemId;
    private String title;
    private String sku;
    private Integer quantityAvailable;
    private Calendar startTime;
    private String startPriceValue;
    private String conditionDisplayName;
    private String ean;
    private String primaryCategoryName;
    private String returns;
    private String startPriceCurrencyId;
    private String imageUrl;
    private String ownerId;
    private String accountId;
    private Integer roundOffPrice;
    private Integer skuNumber;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public String getStartPriceValue() {
        return startPriceValue;
    }

    public void setStartPriceValue(String startPriceValue) {
        this.startPriceValue = startPriceValue;
    }

    public String getConditionDisplayName() {
        return conditionDisplayName;
    }

    public void setConditionDisplayName(String conditionDisplayName) {
        this.conditionDisplayName = conditionDisplayName;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getPrimaryCategoryName() {
        return primaryCategoryName;
    }

    public void setPrimaryCategoryName(String primaryCategoryName) {
        this.primaryCategoryName = primaryCategoryName;
    }

    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getStartPriceCurrencyId() {
        return startPriceCurrencyId;
    }

    public void setStartPriceCurrencyId(String startPriceCurrencyId) {
        this.startPriceCurrencyId = startPriceCurrencyId;
    }

    public Integer getRoundOffPrice() {
        return roundOffPrice;
    }

    public void setRoundOffPrice(Integer roundOffPrice) {
        this.roundOffPrice = roundOffPrice;
    }

    public Integer getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(Integer skuNumber) {
        this.skuNumber = skuNumber;
    }
}
