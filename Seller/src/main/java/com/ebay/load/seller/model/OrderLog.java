package com.ebay.load.seller.model;

import com.ebay.load.seller.dto.OrderItems;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name ="order_log")
public class OrderLog {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name="check_out_status")
    private String checkOutStatus;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="payment_instrument")
    private String paymentInstrument;

    @Column(name="created_time")
    private Calendar createdTime;

    @Column(name="address_street1")
    private String addressStreet1;

    @Column(name="address_street2")
    private String addressStreet2;

    @Column(name="address_city")
    private String addressCity;

    @Column(name="address_phone")
    private String addressPhone;

    @Column(name="address_postal_code")
    private String addressPostalCode;

    @Column(name="address_id")
    private String addressId;

    @Column(name="address_state")
    private String addressState;

    @Column(name="shipping_service")
    private String shippingService;

    @Column(name="paid_time")
    private Calendar paidTime;


    @Column(name="order_id")
    private String orderId;

    @Column(name="buyer_email_id")
    private String buyerEmailId;

    @Column(name="selling_record_number")
    private String sellingRecordNumber;

    @Column(name="buyer_name")
    private String buyerName;

    @Column(name="shipped_to_country")
    private String shippedToCountry;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="original_item_price")
    private String originalItemPrice;

    @Column(name="seller_email")
    private String sellerEmail;

    @Column(name="buyer_user_id")
    private String buyerUserId;

    @Column(name="seller_user_id")
    private String sellerUserId;

    @Column(name="cancel_status")
    private String cancelStatus;

    @Column(name="extended_order_id")
    private String extendedOrderId;

    @Column(name="total_amount")
    private String totalAmount;

    @Column(name="adjustment_amount")
    private String adjustmentAmount;

    @Column(name="amount_paid")
    private String amountPaid;

    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="transaction_date")
    private Calendar transactionDate;

    @Column(name="last_modified_date")
    private Calendar lastModifiedDate;

    @Column(name="item_discount_amount")
    private String itemDiscountAmount;

    @Column(name="sub_total")
    private String subTotal;

    @Column(name="total_amount_currency_id")
    private String totalAmountCurrencyId;

    @Column(name="sub_amount_currency_id")
    private String subTotalCurrencyId;

    @Column(name="condition_display_name")
    private String conditionDisplayName;

    @Column(name="quantity_purchased")
    private String quantityPurchased;

    @Column(name="vat_percent")
    private String vatPercent;

    @Column(name="grand_total")
    private String grandTotal;

    @Column(name="item_discount_price")
    private String itemDiscountPrice;

    @Column(name="shipping_service_cost")
    private String shippingServiceCost;

    @Column(name="shipping_service_cost_currency_id")
    private String shippingServiceCostCurrencyId;

    @Column(name="transaction_price")
    private String transactionPrice;

    @Column(name="grand_total_currency_id")
    private String grandTotalCurrencyId;

    @Column(name="original_item_price_currency_id")
    private String originalItemPriceCurrencyId;

    @Column(name="discount_price_currency_id")
    private String discountPriceCurrencyId;

    @Column(name="adjustment_amount_currency_id")
    private String adjustmentAmountCurrencyId;

    @Column(name="transaction_price_currency_id")
    private String transactionPriceCurrencyId;

    @Column(name="item_image")
    private String itemImage;

    @Column(name="item_id")
    private String itemId;

    @Column(name="title")
    private String title;

    @Column(name="sku")
    private String sku;

    @Column(name="order_state")
    private String orderState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOriginalItemPrice() {
        return originalItemPrice;
    }

    public void setOriginalItemPrice(String originalItemPrice) {
        this.originalItemPrice = originalItemPrice;
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

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getItemDiscountAmount() {
        return itemDiscountAmount;
    }

    public void setItemDiscountAmount(String itemDiscountAmount) {
        this.itemDiscountAmount = itemDiscountAmount;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
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

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getItemDiscountPrice() {
        return itemDiscountPrice;
    }

    public void setItemDiscountPrice(String itemDiscountPrice) {
        this.itemDiscountPrice = itemDiscountPrice;
    }

    public String getShippingServiceCost() {
        return shippingServiceCost;
    }

    public void setShippingServiceCost(String shippingServiceCost) {
        this.shippingServiceCost = shippingServiceCost;
    }

    public String getShippingServiceCostCurrencyId() {
        return shippingServiceCostCurrencyId;
    }

    public void setShippingServiceCostCurrencyId(String shippingServiceCostCurrencyId) {
        this.shippingServiceCostCurrencyId = shippingServiceCostCurrencyId;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
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

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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
