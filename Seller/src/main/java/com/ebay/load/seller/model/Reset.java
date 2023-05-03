package com.ebay.load.seller.model;


import com.ebay.soap.eBLBaseComponents.VariationsType;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reset")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reset {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String ebayItemId;
    private Integer quantity;
    private Integer existedQuantity;
    private Integer totalQuantitySold;
    private Integer soldFromReset;
    private String ebayPrice;
    private String afterPrice;
    private String accountId;
    private Boolean updated;
    private String ownerId;
    private Date lastEffectiveDate;
    private String listingType;
    private VariationsType variationsTypes;
    private String firstVariationName;
    private String firstVariationValue;
    private String variantSku;
    private String secondVariationName;
    private String secondVariationValue;
    private String title;
    private Boolean listingHistory;
    private Boolean moveToAwaitingDispatch;
    private String bidPercentage;
    private String adId;
    private String campaignId;
    private Integer nextResetQuantity;
    private String  nextResetPrice;
    private Boolean dualMode;
    private Boolean nextResetUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEbayItemId() {
        return ebayItemId;
    }

    public void setEbayItemId(String ebayItemId) {
        this.ebayItemId = ebayItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getExistedQuantity() {
        return existedQuantity;
    }

    public void setExistedQuantity(Integer existedQuantity) {
        this.existedQuantity = existedQuantity;
    }

    public String getEbayPrice() {
        return ebayPrice;
    }

    public void setEbayPrice(String ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getLastEffectiveDate() {
        return lastEffectiveDate;
    }

    public void setLastEffectiveDate(Date lastEffectiveDate) {
        this.lastEffectiveDate = lastEffectiveDate;
    }

    public String getAfterPrice() {
        return afterPrice;
    }

    public void setAfterPrice(String afterPrice) {
        this.afterPrice = afterPrice;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public String getFirstVariationName() {
        return firstVariationName;
    }

    public void setFirstVariationName(String firstVariationName) {
        this.firstVariationName = firstVariationName;
    }

    public String getFirstVariationValue() {
        return firstVariationValue;
    }

    public void setFirstVariationValue(String firstVariationValue) {
        this.firstVariationValue = firstVariationValue;
    }

    public String getVariantSku() {
        return variantSku;
    }

    public void setVariantSku(String variantSku) {
        this.variantSku = variantSku;
    }

    public String getSecondVariationName() {
        return secondVariationName;
    }

    public void setSecondVariationName(String secondVariationName) {
        this.secondVariationName = secondVariationName;
    }

    public String getSecondVariationValue() {
        return secondVariationValue;
    }

    public void setSecondVariationValue(String secondVariationValue) {
        this.secondVariationValue = secondVariationValue;
    }

    public VariationsType getVariationsTypes() {
        return variationsTypes;
    }

    public void setVariationsTypes(VariationsType variationsTypes) {
        this.variationsTypes = variationsTypes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(Integer totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }

    public Integer getSoldFromReset() {
        return soldFromReset;
    }

    public void setSoldFromReset(Integer soldFromReset) {
        this.soldFromReset = soldFromReset;
    }

    public Boolean getListingHistory() {
        return listingHistory;
    }

    public void setListingHistory(Boolean listingHistory) {
        this.listingHistory = listingHistory;
    }

    public Boolean getMoveToAwaitingDispatch() {
        return moveToAwaitingDispatch;
    }

    public void setMoveToAwaitingDispatch(Boolean moveToAwaitingDispatch) {
        this.moveToAwaitingDispatch = moveToAwaitingDispatch;
    }

    public String getBidPercentage() {
        return bidPercentage;
    }

    public void setBidPercentage(String bidPercentage) {
        this.bidPercentage = bidPercentage;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getNextResetQuantity() {
        return nextResetQuantity;
    }

    public void setNextResetQuantity(Integer nextResetQuantity) {
        this.nextResetQuantity = nextResetQuantity;
    }

    public String getNextResetPrice() {
        return nextResetPrice;
    }

    public void setNextResetPrice(String nextResetPrice) {
        this.nextResetPrice = nextResetPrice;
    }

    public Boolean getDualMode() {
        return dualMode;
    }

    public void setDualMode(Boolean dualMode) {
        this.dualMode = dualMode;
    }

    public Boolean getNextResetUpdated() {
        return nextResetUpdated;
    }

    public void setNextResetUpdated(Boolean nextResetUpdated) {
        this.nextResetUpdated = nextResetUpdated;
    }
}
