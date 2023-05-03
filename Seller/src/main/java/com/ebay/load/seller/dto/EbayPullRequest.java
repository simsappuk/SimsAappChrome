package com.ebay.load.seller.dto;

import com.ebay.soap.eBLBaseComponents.ItemType;

public class EbayPullRequest {
    private ItemType[] items;
    private boolean errors;
    private String message;

    public ItemType[] getItems() {
        return items;
    }

    public void setItems(ItemType[] items) {
        this.items = items;
    }

    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public EbayPullRequest withItems(ItemType[] items){setItems(items); return this;}
    public EbayPullRequest withMessage(String msg){setMessage(msg); return this;}
    public EbayPullRequest withError(boolean msg){setErrors(msg); return this;}

}
