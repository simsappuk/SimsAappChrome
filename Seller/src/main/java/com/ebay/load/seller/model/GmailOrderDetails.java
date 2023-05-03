package com.ebay.load.seller.model;

import javax.persistence.Embeddable;

@Embeddable
public class GmailOrderDetails {
    private String quantity;
    private String title;
    private String deliveryService;
    private String itemPrice;
    private String sellerUserId;
    private String extendedOrderId;

    public GmailOrderDetails(String title,String quantity,String deliveryService,String itemPrice,String sellerUserId,String extendedOrderId){
        this.title=title;
        this.quantity=quantity;
        this.deliveryService=deliveryService;
        this.itemPrice=itemPrice;
        this.sellerUserId=sellerUserId;
        this.extendedOrderId=extendedOrderId;
    }

    public GmailOrderDetails(){

    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(String deliveryService) {
        this.deliveryService = deliveryService;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getExtendedOrderId() {
        return extendedOrderId;
    }

    public void setExtendedOrderId(String extendedOrderId) {
        this.extendedOrderId = extendedOrderId;
    }
}
