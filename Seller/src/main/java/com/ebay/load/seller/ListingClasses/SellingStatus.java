package com.ebay.load.seller.ListingClasses;

public class SellingStatus {
    private String bidCount;
    private String bidIncrementValue;
    private String bidIncrementCurrencyID;
    private String convertedCurrentPriceValue;
    private String convertedCurrentPriceCurrencyID;
    private String currentPriceValue;
    private String currentPriceCurrencyID;
    private String leadCount;
    private String minimumToBidValue;
    private String minimumToBidCurrencyID;
    private String quantitySold;
    private Boolean reserveMet;
    private Boolean secondChanceEligible;
    private String listingStatusValue;
    private String quantitySoldByPickUpInStore;


    public String getBidCount() {
        return bidCount;
    }

    public void setBidCount(String bidCount) {
        this.bidCount = bidCount;
    }

    public String getBidIncrementValue() {
        return bidIncrementValue;
    }

    public void setBidIncrementValue(String bidIncrementValue) {
        this.bidIncrementValue = bidIncrementValue;
    }

    public String getBidIncrementCurrencyID() {
        return bidIncrementCurrencyID;
    }

    public void setBidIncrementCurrencyID(String bidIncrementCurrencyID) {
        this.bidIncrementCurrencyID = bidIncrementCurrencyID;
    }

    public String getConvertedCurrentPriceValue() {
        return convertedCurrentPriceValue;
    }

    public void setConvertedCurrentPriceValue(String convertedCurrentPriceValue) {
        this.convertedCurrentPriceValue = convertedCurrentPriceValue;
    }

    public String getConvertedCurrentPriceCurrencyID() {
        return convertedCurrentPriceCurrencyID;
    }

    public void setConvertedCurrentPriceCurrencyID(String convertedCurrentPriceCurrencyID) {
        this.convertedCurrentPriceCurrencyID = convertedCurrentPriceCurrencyID;
    }

    public String getCurrentPriceValue() {
        return currentPriceValue;
    }

    public void setCurrentPriceValue(String currentPriceValue) {
        this.currentPriceValue = currentPriceValue;
    }

    public String getCurrentPriceCurrencyID() {
        return currentPriceCurrencyID;
    }

    public void setCurrentPriceCurrencyID(String currentPriceCurrencyID) {
        this.currentPriceCurrencyID = currentPriceCurrencyID;
    }

    public String getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(String leadCount) {
        this.leadCount = leadCount;
    }

    public String getMinimumToBidValue() {
        return minimumToBidValue;
    }

    public void setMinimumToBidValue(String minimumToBidValue) {
        this.minimumToBidValue = minimumToBidValue;
    }

    public String getMinimumToBidCurrencyID() {
        return minimumToBidCurrencyID;
    }

    public void setMinimumToBidCurrencyID(String minimumToBidCurrencyID) {
        this.minimumToBidCurrencyID = minimumToBidCurrencyID;
    }

    public String getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(String quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Boolean getReserveMet() {
        return reserveMet;
    }

    public void setReserveMet(Boolean reserveMet) {
        this.reserveMet = reserveMet;
    }

    public Boolean getSecondChanceEligible() {
        return secondChanceEligible;
    }

    public void setSecondChanceEligible(Boolean secondChanceEligible) {
        this.secondChanceEligible = secondChanceEligible;
    }

    public String getListingStatusValue() {
        return listingStatusValue;
    }

    public void setListingStatusValue(String listingStatusValue) {
        this.listingStatusValue = listingStatusValue;
    }

    public String getQuantitySoldByPickUpInStore() {
        return quantitySoldByPickUpInStore;
    }

    public void setQuantitySoldByPickUpInStore(String quantitySoldByPickUpInStore) {
        this.quantitySoldByPickUpInStore = quantitySoldByPickUpInStore;
    }

    public static class HighBidder {
        private Boolean aboutMePage;
        private String eiasToken;
        private String email;
        private String feedBackScore;
        private String positiveFeedBackPercent;
        private Boolean feedBackPrivate;
        private String feedBackRatingStar;
        private Boolean idVerified;
        private Boolean ebayGoodStanding;
        private Boolean newUser;
        private String site;
        private String status;
        private String userId;
        private String vatStatus;
        private Boolean userIdChanged;
        private Boolean userAnonymized;


        public Boolean getAboutMePage() {
            return aboutMePage;
        }

        public void setAboutMePage(Boolean aboutMePage) {
            this.aboutMePage = aboutMePage;
        }

        public String getEiasToken() {
            return eiasToken;
        }

        public void setEiasToken(String eiasToken) {
            this.eiasToken = eiasToken;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFeedBackScore() {
            return feedBackScore;
        }

        public void setFeedBackScore(String feedBackScore) {
            this.feedBackScore = feedBackScore;
        }

        public String getPositiveFeedBackPercent() {
            return positiveFeedBackPercent;
        }

        public void setPositiveFeedBackPercent(String positiveFeedBackPercent) {
            this.positiveFeedBackPercent = positiveFeedBackPercent;
        }

        public Boolean getFeedBackPrivate() {
            return feedBackPrivate;
        }

        public void setFeedBackPrivate(Boolean feedBackPrivate) {
            this.feedBackPrivate = feedBackPrivate;
        }

        public String getFeedBackRatingStar() {
            return feedBackRatingStar;
        }

        public void setFeedBackRatingStar(String feedBackRatingStar) {
            this.feedBackRatingStar = feedBackRatingStar;
        }

        public Boolean getIdVerified() {
            return idVerified;
        }

        public void setIdVerified(Boolean idVerified) {
            this.idVerified = idVerified;
        }

        public Boolean getEbayGoodStanding() {
            return ebayGoodStanding;
        }

        public void setEbayGoodStanding(Boolean ebayGoodStanding) {
            this.ebayGoodStanding = ebayGoodStanding;
        }

        public Boolean getNewUser() {
            return newUser;
        }

        public void setNewUser(Boolean newUser) {
            this.newUser = newUser;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVatStatus() {
            return vatStatus;
        }

        public void setVatStatus(String vatStatus) {
            this.vatStatus = vatStatus;
        }

        public Boolean getUserIdChanged() {
            return userIdChanged;
        }

        public void setUserIdChanged(Boolean userIdChanged) {
            this.userIdChanged = userIdChanged;
        }

        public Boolean getUserAnonymized() {
            return userAnonymized;
        }

        public void setUserAnonymized(Boolean userAnonymized) {
            this.userAnonymized = userAnonymized;
        }
    }
}
