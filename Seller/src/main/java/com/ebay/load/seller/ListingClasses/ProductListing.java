package com.ebay.load.seller.ListingClasses;

//import com.sun.xml.internal.fastinfoset.util.StringArray;

public class ProductListing {
    private Boolean incudeStockPhotoUrl;
    private Boolean useStockPhotoUrlAsGallery;
    private String productReferenceId;
    private String ean;
    private String Mpn;
    private Boolean includeEbayProductDetails;


    public Boolean getIncudeStockPhotoUrl() {
        return incudeStockPhotoUrl;
    }

    public void setIncudeStockPhotoUrl(Boolean incudeStockPhotoUrl) {
        this.incudeStockPhotoUrl = incudeStockPhotoUrl;
    }

    public Boolean getUseStockPhotoUrlAsGallery() {
        return useStockPhotoUrlAsGallery;
    }

    public void setUseStockPhotoUrlAsGallery(Boolean useStockPhotoUrlAsGallery) {
        this.useStockPhotoUrlAsGallery = useStockPhotoUrlAsGallery;
    }

    public String getProductReferenceId() {
        return productReferenceId;
    }

    public void setProductReferenceId(String productReferenceId) {
        this.productReferenceId = productReferenceId;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getMpn() {
        return Mpn;
    }

    public void setMpn(String mpn) {
        Mpn = mpn;
    }

    public Boolean getIncludeEbayProductDetails() {
        return includeEbayProductDetails;
    }

    public void setIncludeEbayProductDetails(Boolean includeEbayProductDetails) {
        this.includeEbayProductDetails = includeEbayProductDetails;
    }

}
