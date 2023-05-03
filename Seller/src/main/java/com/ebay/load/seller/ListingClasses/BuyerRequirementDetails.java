package com.ebay.load.seller.ListingClasses;

public class BuyerRequirementDetails {
    private Boolean shipToRegistrationCountry;
    private String maximumItemCount;
    private String maximumUnpaidItemStrikesInfoCount;
    private String maximumUnpaidItemStrikesInfoPeriod;

    public Boolean getShipToRegistrationCountry() {
        return shipToRegistrationCountry;
    }

    public void setShipToRegistrationCountry(Boolean shipToRegistrationCountry) {
        this.shipToRegistrationCountry = shipToRegistrationCountry;
    }

    public String getMaximumItemCount() {
        return maximumItemCount;
    }

    public void setMaximumItemCount(String maximumItemCount) {
        this.maximumItemCount = maximumItemCount;
    }

    public String getMaximumUnpaidItemStrikesInfoCount() {
        return maximumUnpaidItemStrikesInfoCount;
    }

    public void setMaximumUnpaidItemStrikesInfoCount(String maximumUnpaidItemStrikesInfoCount) {
        this.maximumUnpaidItemStrikesInfoCount = maximumUnpaidItemStrikesInfoCount;
    }

    public String getMaximumUnpaidItemStrikesInfoPeriod() {
        return maximumUnpaidItemStrikesInfoPeriod;
    }

    public void setMaximumUnpaidItemStrikesInfoPeriod(String maximumUnpaidItemStrikesInfoPeriod) {
        this.maximumUnpaidItemStrikesInfoPeriod = maximumUnpaidItemStrikesInfoPeriod;
    }
}
