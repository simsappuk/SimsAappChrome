package com.ebay.load.seller.ListingClasses;

public class ShippingDetails {
    private Boolean applyShippingDiscount;
    private String salesTaxPercent;
    private Boolean shippingIncludedInTax;
    private String shippingType;
    private Boolean thirdPartyCheckOut;
    private String shippingDiscountProfileId;
    private String internationalShippingDiscountProfileId;
    private Boolean sellerExcludeShipToLocationsPreference;



    public Boolean getApplyShippingDiscount() {
        return applyShippingDiscount;
    }

    public void setApplyShippingDiscount(Boolean applyShippingDiscount) {
        this.applyShippingDiscount = applyShippingDiscount;
    }

    public String getSalesTaxPercent() {
        return salesTaxPercent;
    }

    public void setSalesTaxPercent(String salesTaxPercent) {
        this.salesTaxPercent = salesTaxPercent;
    }

    public Boolean getShippingIncludedInTax() {
        return shippingIncludedInTax;
    }

    public void setShippingIncludedInTax(Boolean shippingIncludedInTax) {
        this.shippingIncludedInTax = shippingIncludedInTax;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Boolean getThirdPartyCheckOut() {
        return thirdPartyCheckOut;
    }

    public void setThirdPartyCheckOut(Boolean thirdPartyCheckOut) {
        this.thirdPartyCheckOut = thirdPartyCheckOut;
    }

    public String getShippingDiscountProfileId() {
        return shippingDiscountProfileId;
    }

    public void setShippingDiscountProfileId(String shippingDiscountProfileId) {
        this.shippingDiscountProfileId = shippingDiscountProfileId;
    }

    public String getInternationalShippingDiscountProfileId() {
        return internationalShippingDiscountProfileId;
    }

    public void setInternationalShippingDiscountProfileId(String internationalShippingDiscountProfileId) {
        this.internationalShippingDiscountProfileId = internationalShippingDiscountProfileId;
    }

    public Boolean getSellerExcludeShipToLocationsPreference() {
        return sellerExcludeShipToLocationsPreference;
    }

    public void setSellerExcludeShipToLocationsPreference(Boolean sellerExcludeShipToLocationsPreference) {
        this.sellerExcludeShipToLocationsPreference = sellerExcludeShipToLocationsPreference;
    }



}
