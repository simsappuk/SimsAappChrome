package com.ebay.load.seller.dto;




import java.util.Calendar;
import java.util.List;


public class EbayOrders {
    private String checkOutStatus;
    private String paymentMethod;
    private String paymentInstrument;
    private Calendar createdTime;
    private String addressStreet1;
    private String addressStreet2;
    private String addressCity;
    private String addressPhone;
    private String addressPostalCode;
    private String addressId;
    private String addressState;
    private String shippingService;
    private Calendar paidTime;
    private String orderId;
    private String buyerEmailId;
    private String sellingRecordNumber;
    private String buyerName;
    private String shippedToCountry;
    private String itemId;
    private String orderStatus;
    private String originalItemPrice;
    private String sellerEmail;
    private String buyerUserId;
    private String sellerUserId;
    private String cancelStatus;
    private String extendedOrderId;
    private String totalAmount;
    private String adjustmentAmount;
    private String amountPaid;
    private String transactionId;
    private List<OrderItems> item;
    private Calendar transactionDate;
    private Calendar lastModifiedDate;
    private String title;
    private String sku;
    private String itemDiscountAmount;
    private String subTotal;
    private String totalAmountCurrencyId;
    private String subTotalCurrencyId;
    private String conditionDisplayName;
    private String quantityPurchased;
    private String vatPercent;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getExtendedOrderId() {
        return extendedOrderId;
    }

    public void setExtendedOrderId(String extendedOrderId) {
        this.extendedOrderId = extendedOrderId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getConditionDisplayName() {
        return conditionDisplayName;
    }

    public void setConditionDisplayName(String conditionDisplayName) {
        this.conditionDisplayName = conditionDisplayName;
    }

    public String getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(String quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getTotalAmountCurrencyId() {
        return totalAmountCurrencyId;
    }

    public void setTotalAmountCurrencyId(String totalAmountCurrencyId) {
        this.totalAmountCurrencyId = totalAmountCurrencyId;
    }

    public String getSubTotalCurrencyId() {
        return subTotalCurrencyId;
    }

    public void setSubTotalCurrencyId(String subTotalCurrencyId) {
        this.subTotalCurrencyId = subTotalCurrencyId;
    }


    public String getOriginalItemPrice() {
        return originalItemPrice;
    }

    public void setOriginalItemPrice(String originalItemPrice) {
        this.originalItemPrice = originalItemPrice;
    }

    public String getItemDiscountAmount() {
        return itemDiscountAmount;
    }

    public void setItemDiscountAmount(String itemDiscountAmount) {
        this.itemDiscountAmount = itemDiscountAmount;
    }


    public List<OrderItems> getItem() {
        return item;
    }

    public void setItem(List<OrderItems> item) {
        this.item = item;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Calendar transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Calendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getBuyerEmailId() {
        return buyerEmailId;
    }

    public void setBuyerEmailId(String buyerEmailId) {
        this.buyerEmailId = buyerEmailId;
    }

    public String getSellingRecordNumber() {
        return sellingRecordNumber;
    }

    public void setSellingRecordNumber(String sellingRecordNumber) {
        this.sellingRecordNumber = sellingRecordNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getShippedToCountry() {
        return shippedToCountry;
    }

    public void setShippedToCountry(String shippedToCountry) {
        this.shippedToCountry = shippedToCountry;
    }

    public String getCheckOutStatus() {
        return checkOutStatus;
    }

    public void setCheckOutStatus(String checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(String paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public String getAddressStreet1() {
        return addressStreet1;
    }

    public void setAddressStreet1(String addressStreet1) {
        this.addressStreet1 = addressStreet1;
    }

    public String getAddressStreet2() {
        return addressStreet2;
    }

    public void setAddressStreet2(String addressStreet2) {
        this.addressStreet2 = addressStreet2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getShippingService() {
        return shippingService;
    }

    public void setShippingService(String shippingService) {
        this.shippingService = shippingService;
    }

    public Calendar getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Calendar paidTime) {
        this.paidTime = paidTime;
    }
}