package com.ebay.load.seller.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compete")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Compete {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String link;
    private String sellerPrice;
    private Integer quantity;
    private String ebayItemId;
    @ElementCollection(fetch=FetchType.LAZY)
    private List<String> competeItemId;
    private String ebayPrice;
    private String accountId;
    private String ownerId;
    private String subtractValue;
    private String checkRevise;
    private Date lastEffectiveDate;
    private String listingType;
    private String firstVariationName;
    private String firstVariationValue;
    private String variantSku;
    private String secondVariationName;
    private String secondVariationValue;
    private boolean pause;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(String sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getEbayPrice() {
        return ebayPrice;
    }

    public void setEbayPrice(String ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

    public String getSubtractValue() {
        return subtractValue;
    }

    public void setSubtractValue(String subtractValue) {
        this.subtractValue = subtractValue;
    }

    public String getCheckRevise() {
        return checkRevise;
    }

    public void setCheckRevise(String checkRevise) {
        this.checkRevise = checkRevise;
    }

    public Date getLastEffectiveDate() {
        return lastEffectiveDate;
    }

    public void setLastEffectiveDate(Date lastEffectiveDate) {
        this.lastEffectiveDate = lastEffectiveDate;
    }

    public String getEbayItemId() {
        return ebayItemId;
    }

    public void setEbayItemId(String ebayItemId) {
        this.ebayItemId = ebayItemId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getVariantSku() {
        return variantSku;
    }

    public void setVariantSku(String variantSku) {
        this.variantSku = variantSku;
    }

    public List<String> getCompeteItemId() {
        return competeItemId;
    }

    public void setCompeteItemId(List<String> competeItemId) {
        this.competeItemId = competeItemId;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
