package com.ebay.load.seller.model;

import io.swagger.models.auth.In;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dropship")
public class Dropship {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String link;
    private String sellerInfo;
    private String sellerPrice;
    private Integer quantity;
    private String itemId;
    private String ownerId;
    private String ebayPrice;
    private String outOfStockPrice;
    private String ebayPaypalPercent;
    private String vatPercent;
    private String accountId;
    private String profitPercent;
    private String paypalTransactionCharge;
    private String condition;
    private String checkRevise;
    private Date lastEffectiveDate;
    private String source;
    private boolean pause;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(String sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public String getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(String sellerPrice) {
        this.sellerPrice = sellerPrice;
    }


    public String getEbayPrice() {
        return ebayPrice;
    }

    public void setEbayPrice(String ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(String profitPercent) {
        this.profitPercent = profitPercent;
    }

    public String getPaypalTransactionCharge() {
        return paypalTransactionCharge;
    }

    public void setPaypalTransactionCharge(String paypalTransactionCharge) {
        this.paypalTransactionCharge = paypalTransactionCharge;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getEbayPaypalPercent() {
        return ebayPaypalPercent;
    }

    public void setEbayPaypalPercent(String ebayPaypalPercent) {
        this.ebayPaypalPercent = ebayPaypalPercent;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getCheckRevise() {
        return checkRevise;
    }

    public void setCheckRevise(String checkRevise) {
        this.checkRevise = checkRevise;
    }

    public Date getLastEffectiveDate() {
        return lastEffectiveDate;
    }

    public void setLastEffectiveDate(Date lastEffectiveDate) {
        this.lastEffectiveDate = lastEffectiveDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOutOfStockPrice() {
        return outOfStockPrice;
    }

    public void setOutOfStockPrice(String outOfStockPrice) {
        this.outOfStockPrice = outOfStockPrice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
