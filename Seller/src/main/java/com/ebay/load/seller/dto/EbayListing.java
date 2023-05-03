package com.ebay.load.seller.dto;


import com.ebay.load.seller.model.VariantItemDetails;
import com.ebay.soap.eBLBaseComponents.VariationsType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EbayListing extends EbayOrders {


    private Boolean autoPay;
    private String buyerProtection;
    private String buyItNowPriceValue;
    private String buyItNowPriceCurrencyID;
    private String country;
    private Calendar startTime;
    private Calendar endTime;
    private String currency;
    private String description;
    private String hitCounter;
    private String itemId;
    private String listingDesignerLayoutID;
    private String listingDesignerThemeID;
    private String listingDuration;
    private String listingType;
    private String location;
    private String timeLeft;
    private String payPalEmailAddress;
    private String primaryCategoryID;
    private String primaryCategoryName;
    private String quantity;
    private String reservePriceValue;
    private String reservePriceCurrencyID;
    private String reviseStatus_ItemRevised;
    private String site;
    private String startPriceValue;
    private String startPriceCurrencyID;
    private String storeFrontCategoryID;
    private String storeFrontCategory2ID;
    private String storeURL;
    private String brand;
    private String mpn;
    private String title;
    private String hitCount;
    private String platform;
    private String genre;
    private Boolean buyerResponsibleforShipping;
    private String sku;
    private Long watchCount;
    private String postalCode;
    private String dispatchTimeMax;
    private Boolean proxyItem;
    private String buyerGuaranteePriceValue;
    private String buyerGuaranteePriceCurrencyID;
    private String conditionID;
    private String conditionDisplayName;
    private String relistParentID;
    private Boolean hideFromSearch;
    private Boolean ebayPlus;
    private Boolean ebayPlusEligible;
    private Boolean isSecureDescription;
    private String imageUrl;
    private String imageError;
    private List paymentMethods;
    private String ean;
    private String returns;
    private VariationsType variationsTypes;
    private List<VariantItemDetails> variantItemDetails;
    private Integer quantityAvailable;
    private String shippingProfileId;
    private String shippingProfileName;
    private String returnProfileName;
    private String returnProfileId;
    private String paymentProfileId;
    private String paymentProfileName;
    private Integer totalQuantitySold;

    public Boolean getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(Boolean autoPay) {
        this.autoPay = autoPay;
    }

    public String getBuyerProtection() {
        return buyerProtection;
    }

    public void setBuyerProtection(String buyerProtection) {
        this.buyerProtection = buyerProtection;
    }

    public String getBuyItNowPriceValue() {
        return buyItNowPriceValue;
    }

    public void setBuyItNowPriceValue(String buyItNowPriceValue) {
        this.buyItNowPriceValue = buyItNowPriceValue;
    }

    public String getBuyItNowPriceCurrencyID() {
        return buyItNowPriceCurrencyID;
    }

    public void setBuyItNowPriceCurrencyID(String buyItNowPriceCurrencyID) {
        this.buyItNowPriceCurrencyID = buyItNowPriceCurrencyID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHitCounter() {
        return hitCounter;
    }

    public void setHitCounter(String hitCounter) {
        this.hitCounter = hitCounter;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getListingDesignerLayoutID() {
        return listingDesignerLayoutID;
    }

    public void setListingDesignerLayoutID(String listingDesignerLayoutID) {
        this.listingDesignerLayoutID = listingDesignerLayoutID;
    }

    public String getListingDesignerThemeID() {
        return listingDesignerThemeID;
    }

    public void setListingDesignerThemeID(String listingDesignerThemeID) {
        this.listingDesignerThemeID = listingDesignerThemeID;
    }

    public String getListingDuration() {
        return listingDuration;
    }

    public void setListingDuration(String listingDuration) {
        this.listingDuration = listingDuration;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPayPalEmailAddress() {
        return payPalEmailAddress;
    }

    public void setPayPalEmailAddress(String payPalEmailAddress) {
        this.payPalEmailAddress = payPalEmailAddress;
    }

    public String getPrimaryCategoryID() {
        return primaryCategoryID;
    }

    public void setPrimaryCategoryID(String primaryCategoryID) {
        this.primaryCategoryID = primaryCategoryID;
    }

    public String getPrimaryCategoryName() {
        return primaryCategoryName;
    }

    public void setPrimaryCategoryName(String primaryCategoryName) {
        this.primaryCategoryName = primaryCategoryName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getReservePriceValue() {
        return reservePriceValue;
    }

    public void setReservePriceValue(String reservePriceValue) {
        this.reservePriceValue = reservePriceValue;
    }

    public String getReservePriceCurrencyID() {
        return reservePriceCurrencyID;
    }

    public void setReservePriceCurrencyID(String reservePriceCurrencyID) {
        this.reservePriceCurrencyID = reservePriceCurrencyID;
    }

    public String getReviseStatus_ItemRevised() {
        return reviseStatus_ItemRevised;
    }

    public void setReviseStatus_ItemRevised(String reviseStatus_ItemRevised) {
        this.reviseStatus_ItemRevised = reviseStatus_ItemRevised;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStartPriceValue() {
        return startPriceValue;
    }

    public void setStartPriceValue(String startPriceValue) {
        this.startPriceValue = startPriceValue;
    }

    public String getStartPriceCurrencyID() {
        return startPriceCurrencyID;
    }

    public void setStartPriceCurrencyID(String startPriceCurrencyID) {
        this.startPriceCurrencyID = startPriceCurrencyID;
    }

    public String getStoreFrontCategoryID() {
        return storeFrontCategoryID;
    }

    public void setStoreFrontCategoryID(String storeFrontCategoryID) {
        this.storeFrontCategoryID = storeFrontCategoryID;
    }

    public String getStoreFrontCategory2ID() {
        return storeFrontCategory2ID;
    }

    public void setStoreFrontCategory2ID(String storeFrontCategory2ID) {
        this.storeFrontCategory2ID = storeFrontCategory2ID;
    }

    public String getStoreURL() {
        return storeURL;
    }

    public void setStoreURL(String storeURL) {
        this.storeURL = storeURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHitCount() {
        return hitCount;
    }

    public void setHitCount(String hitCount) {
        this.hitCount = hitCount;
    }

    public Boolean getBuyerResponsibleforShipping() {
        return buyerResponsibleforShipping;
    }

    public void setBuyerResponsibleforShipping(Boolean buyerResponsibleforShipping) {
        this.buyerResponsibleforShipping = buyerResponsibleforShipping;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDispatchTimeMax() {
        return dispatchTimeMax;
    }

    public void setDispatchTimeMax(String dispatchTimeMax) {
        this.dispatchTimeMax = dispatchTimeMax;
    }

    public Boolean getProxyItem() {
        return proxyItem;
    }

    public void setProxyItem(Boolean proxyItem) {
        this.proxyItem = proxyItem;
    }

    public String getBuyerGuaranteePriceValue() {
        return buyerGuaranteePriceValue;
    }

    public void setBuyerGuaranteePriceValue(String buyerGuaranteePriceValue) {
        this.buyerGuaranteePriceValue = buyerGuaranteePriceValue;
    }

    public String getBuyerGuaranteePriceCurrencyID() {
        return buyerGuaranteePriceCurrencyID;
    }

    public void setBuyerGuaranteePriceCurrencyID(String buyerGuaranteePriceCurrencyID) {
        this.buyerGuaranteePriceCurrencyID = buyerGuaranteePriceCurrencyID;
    }

    public String getConditionID() {
        return conditionID;
    }

    public void setConditionID(String conditionID) {
        this.conditionID = conditionID;
    }

    public String getConditionDisplayName() {
        return conditionDisplayName;
    }

    public void setConditionDisplayName(String conditionDisplayName) {
        this.conditionDisplayName = conditionDisplayName;
    }

    public String getRelistParentID() {
        return relistParentID;
    }

    public void setRelistParentID(String relistParentID) {
        this.relistParentID = relistParentID;
    }

    public Boolean getHideFromSearch() {
        return hideFromSearch;
    }

    public void setHideFromSearch(Boolean hideFromSearch) {
        this.hideFromSearch = hideFromSearch;
    }

    public Boolean getEbayPlus() {
        return ebayPlus;
    }

    public void setEbayPlus(Boolean ebayPlus) {
        this.ebayPlus = ebayPlus;
    }

    public Boolean getEbayPlusEligible() {
        return ebayPlusEligible;
    }

    public void setEbayPlusEligible(Boolean ebayPlusEligible) {
        this.ebayPlusEligible = ebayPlusEligible;
    }


    public Boolean getSecureDescription() {
        return isSecureDescription;
    }

    public void setSecureDescription(Boolean secureDescription) {
        isSecureDescription = secureDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }


    public List getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }

    public Long getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Long watchCount) {
        this.watchCount = watchCount;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getShippingProfileId() {
        return shippingProfileId;
    }

    public void setShippingProfileId(String shippingProfileId) {
        this.shippingProfileId = shippingProfileId;
    }

    public String getShippingProfileName() {
        return shippingProfileName;
    }

    public void setShippingProfileName(String shippingProfileName) {
        this.shippingProfileName = shippingProfileName;
    }

    public String getReturnProfileName() {
        return returnProfileName;
    }

    public void setReturnProfileName(String returnProfileName) {
        this.returnProfileName = returnProfileName;
    }

    public String getReturnProfileId() {
        return returnProfileId;
    }

    public void setReturnProfileId(String returnProfileId) {
        this.returnProfileId = returnProfileId;
    }

    public String getPaymentProfileId() {
        return paymentProfileId;
    }

    public void setPaymentProfileId(String paymentProfileId) {
        this.paymentProfileId = paymentProfileId;
    }

    public String getPaymentProfileName() {
        return paymentProfileName;
    }

    public void setPaymentProfileName(String paymentProfileName) {
        this.paymentProfileName = paymentProfileName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<VariantItemDetails> getVariantItemDetails() {
        return variantItemDetails;
    }

    public void setVariantItemDetails(List<VariantItemDetails> variantItemDetails) {
        this.variantItemDetails = variantItemDetails;
    }

    public VariationsType getVariationsTypes() {
        return variationsTypes;
    }

    public void setVariationsTypes(VariationsType variationsTypes) {
        this.variationsTypes = variationsTypes;
    }

    public Integer getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(Integer totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }
}

















