package com.ebay.load.seller.ListingClasses;

//import com.sun.xml.internal.fastinfoset.util.StringArray;
import org.springframework.security.core.parameters.P;

public class Seller {

    private Boolean aboutMePage;
    private String email;
    private String feedBackScore;
    private String positiveFeedBackPercent;
    private Boolean feedBackPrivate;
    private String feedBackRatingStar;
    private Boolean idVerified;
    private Boolean ebayGoodStanding;
    private Boolean newUser;
    //private Boolean registrationDate;
    private String site;
    private String status;
    private String userId;
    private String vatStatus;
    private Boolean userIdChanged;
    private Boolean motorsDealer;

    public Boolean getAboutMePage() {
        return aboutMePage;
    }

    public void setAboutMePage(Boolean aboutMePage) {
        this.aboutMePage = aboutMePage;
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

    public Boolean getMotorsDealer() {
        return motorsDealer;
    }

    public void setMotorsDealer(Boolean motorsDealer) {
        this.motorsDealer = motorsDealer;
    }


public static class SellerInfo{
        private String merchandizingPref;
        private String StoreUrl;
        private  Boolean safePaymentExempt;

        private Boolean allowPaymentEdit;
        private Boolean cipBankAccountStored;
        private Boolean checkOutEnabled;
        private Boolean goodStanding;
        private Boolean qualifiesForB2BVat;
        private Boolean storeOwner;

    public String getMerchandizingPref() {
        return merchandizingPref;
    }

    public void setMerchandizingPref(String merchandizingPref) {
        this.merchandizingPref = merchandizingPref;
    }

    public String getStoreUrl() {
        return StoreUrl;
    }

    public void setStoreUrl(String storeUrl) {
        StoreUrl = storeUrl;
    }

    public Boolean getSafePaymentExempt() {
        return safePaymentExempt;
    }

    public void setSafePaymentExempt(Boolean safePaymentExempt) {
        this.safePaymentExempt = safePaymentExempt;
    }


    public Boolean getAllowPaymentEdit() {
        return allowPaymentEdit;
    }

    public void setAllowPaymentEdit(Boolean allowPaymentEdit) {
        this.allowPaymentEdit = allowPaymentEdit;
    }

    public Boolean getCipBankAccountStored() {
        return cipBankAccountStored;
    }

    public void setCipBankAccountStored(Boolean cipBankAccountStored) {
        this.cipBankAccountStored = cipBankAccountStored;
    }

    public Boolean getCheckOutEnabled() {
        return checkOutEnabled;
    }

    public void setCheckOutEnabled(Boolean checkOutEnabled) {
        this.checkOutEnabled = checkOutEnabled;
    }

    public Boolean getGoodStanding() {
        return goodStanding;
    }

    public void setGoodStanding(Boolean goodStanding) {
        this.goodStanding = goodStanding;
    }

    public Boolean getQualifiesForB2BVat() {
        return qualifiesForB2BVat;
    }

    public void setQualifiesForB2BVat(Boolean qualifiesForB2BVat) {
        this.qualifiesForB2BVat = qualifiesForB2BVat;
    }

    public Boolean getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(Boolean storeOwner) {
        this.storeOwner = storeOwner;
    }
}
}