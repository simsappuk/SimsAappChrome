package com.ebay.load.seller.dto;

import com.ebay.load.seller.model.VariantItemDetails;

import java.util.Calendar;

public class OrderItems {
    private String itemId;
    private String title;
    private String sku;
    private String totalAmount;
    private String grandTotal;
    private String quantityPurchased;
    private String originalItemPrice;
    private String itemDiscountPrice;
    private String adjustmentAmount;
    private Double shippingServiceCost;
    private String shippingServiceCostCurrencyId;
    private String vatPercent;
    private String transactionPrice;
    private Calendar transactionDate;
    private String totalAmountCurrencyId;
    private String grandTotalCurrencyId;
    private String originalItemPriceCurrencyId;
    private String discountPriceCurrencyId;
    private String adjustmentAmountCurrencyId;
    private String transactionPriceCurrencyId;
    private String itemImage;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String 	getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String subTotal) {
        this.grandTotal = subTotal;
    }

    public String getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(String quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public String getOriginalItemPrice() {
        return originalItemPrice;
    }

    public void setOriginalItemPrice(String originalItemPrice) {
        this.originalItemPrice = originalItemPrice;
    }

    public String getItemDiscountPrice() {
        return itemDiscountPrice;
    }

    public void setItemDiscountPrice(String itemDiscountPrice) {
        this.itemDiscountPrice = itemDiscountPrice;
    }

    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getTotalAmountCurrencyId() {
        return totalAmountCurrencyId;
    }

    public void setTotalAmountCurrencyId(String totalAmountCurrencyId) {
        this.totalAmountCurrencyId = totalAmountCurrencyId;
    }

    public String getGrandTotalCurrencyId() {
        return grandTotalCurrencyId;
    }

    public void setGrandTotalCurrencyId(String grandTotalCurrencyId) {
        this.grandTotalCurrencyId = grandTotalCurrencyId;
    }

    public String getOriginalItemPriceCurrencyId() {
        return originalItemPriceCurrencyId;
    }

    public void setOriginalItemPriceCurrencyId(String originalItemPriceCurrencyId) {
        this.originalItemPriceCurrencyId = originalItemPriceCurrencyId;
    }

    public String getDiscountPriceCurrencyId() {
        return discountPriceCurrencyId;
    }

    public void setDiscountPriceCurrencyId(String discountPriceCurrencyId) {
        this.discountPriceCurrencyId = discountPriceCurrencyId;
    }

    public String getAdjustmentAmountCurrencyId() {
        return adjustmentAmountCurrencyId;
    }

    public void setAdjustmentAmountCurrencyId(String adjustmentAmountCurrencyId) {
        this.adjustmentAmountCurrencyId = adjustmentAmountCurrencyId;
    }

    public String getTransactionPriceCurrencyId() {
        return transactionPriceCurrencyId;
    }

    public void setTransactionPriceCurrencyId(String transactionPriceCurrencyId) {
        this.transactionPriceCurrencyId = transactionPriceCurrencyId;
    }

    public Double getShippingServiceCost() {
        return shippingServiceCost;
    }

    public void setShippingServiceCost(Double shippingServiceCost) {
        this.shippingServiceCost = shippingServiceCost;
    }

    public String getShippingServiceCostCurrencyId() {
        return shippingServiceCostCurrencyId;
    }

    public void setShippingServiceCostCurrencyId(String shippingServiceCostCurrencyId) {
        this.shippingServiceCostCurrencyId = shippingServiceCostCurrencyId;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Calendar transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
