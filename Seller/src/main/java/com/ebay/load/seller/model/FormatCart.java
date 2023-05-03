package com.ebay.load.seller.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Cart")
public class FormatCart extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

     @Column(name = "seller_paid_status")
    private String sellerPaidStatus;

    @Column(name = "position1")
    private String position;

    @Column(name = "custom_label")
    private String customLabel;

    @Column(name = "parent_record_number")
    private String parentRecordNumber;

    @Column(name = "shipped_date")
    private Date shippedDate;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "detail_href")
    private String detailHref;

    @Column(name = "url_stack")
    private String urlStack;

    @Column(name = "record")
    private String record;

    @Column(name = "title")
    private String title;

    @Column(name = "feedback_receivde_by_buyer")
    private String feedbackReceivedByBuyer;

    @Column(name = "shipped_status")
    private String shippedStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "sold_on")
    private String soldOn;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "tran_id")
    private String tranId;

    @Column(name = "records")
    private String records;

    @Column(name = "email_Sent")
    private String emailSent;

    @Column(name = "purchased_quantity")
    private String purchasedQty;

    @Column(name = "paid_date")
    private Date paidDate;

    @Column(name = "checkout_status")
    private String checkoutStatus;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "sale_currency_format")
    private String salePriceCurrency;

    @Column(name = "total_currency_format")
    private String totalPriceCurrency;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerPaidStatus() {
        return sellerPaidStatus;
    }

    public void setSellerPaidStatus(String sellerPaidStatus) {
        this.sellerPaidStatus = sellerPaidStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCustomLabel() {
        return customLabel;
    }

    public void setCustomLabel(String customLabel) {
        this.customLabel = customLabel;
    }

    public String getParentRecordNumber() {
        return parentRecordNumber;
    }

    public void setParentRecordNumber(String parentRecordNumber) {
        this.parentRecordNumber = parentRecordNumber;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDetailHref() {
        return detailHref;
    }

    public void setDetailHref(String detailHref) {
        this.detailHref = detailHref;
    }

    public String getUrlStack() {
        return urlStack;
    }

    public void setUrlStack(String urlStack) {
        this.urlStack = urlStack;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedbackReceivedByBuyer() {
        return feedbackReceivedByBuyer;
    }

    public void setFeedbackReceivedByBuyer(String feedbackReceivedByBuyer) {
        this.feedbackReceivedByBuyer = feedbackReceivedByBuyer;
    }

    public String getShippedStatus() {
        return shippedStatus;
    }

    public void setShippedStatus(String shippedStatus) {
        this.shippedStatus = shippedStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoldOn() {
        return soldOn;
    }

    public void setSoldOn(String soldOn) {
        this.soldOn = soldOn;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(String emailSent) {
        this.emailSent = emailSent;
    }

    public String getPurchasedQty() {
        return purchasedQty;
    }

    public void setPurchasedQty(String purchasedQty) {
        this.purchasedQty = purchasedQty;
    }


    public String getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(String checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }


    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSalePriceCurrency() {
        return salePriceCurrency;
    }

    public void setSalePriceCurrency(String salePriceCurrency) {
        this.salePriceCurrency = salePriceCurrency;
    }

    public String getTotalPriceCurrency() {
        return totalPriceCurrency;
    }

    public void setTotalPriceCurrency(String totalPriceCurrency) {
        this.totalPriceCurrency = totalPriceCurrency;
    }

}
