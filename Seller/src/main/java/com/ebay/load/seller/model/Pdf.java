package com.ebay.load.seller.model;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pdf")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pdf {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String buyerName;
    private String buyerUserId;
    private String buyerCountry;
    private String buyerStreet1;
    private String buyerStreet2;
    private String itemDiscount;
    private String totalAmount;
    private String subTotal;
    private String postageFee;
    private String paidDate;
    private String placedDate;
    private String paymentMethod;
    @ElementCollection
    private List<GmailOrderDetails> orderDetails;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getBuyerCountry() {
        return buyerCountry;
    }

    public void setBuyerCountry(String buyerCountry) {
        this.buyerCountry = buyerCountry;
    }

    public String getBuyerStreet1() {
        return buyerStreet1;
    }

    public void setBuyerStreet1(String buyerStreet1) {
        this.buyerStreet1 = buyerStreet1;
    }

    public String getBuyerStreet2() {
        return buyerStreet2;
    }

    public void setBuyerStreet2(String buyerStreet2) {
        this.buyerStreet2 = buyerStreet2;
    }

    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getPostageFee() {
        return postageFee;
    }

    public void setPostageFee(String postageFee) {
        this.postageFee = postageFee;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(String placedDate) {
        this.placedDate = placedDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<GmailOrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<GmailOrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
