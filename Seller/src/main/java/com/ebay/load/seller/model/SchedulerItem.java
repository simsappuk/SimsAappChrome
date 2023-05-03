package com.ebay.load.seller.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="schedulerItem")
public class SchedulerItem {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name="id")
    private String id;

    private String stockId;
    private String buyerEmailId;
    private String sellingRecordNumber;
    private String buyerName;
    private String shippedToCountry;
    private String schedulerId;
    private String orderId;
    private String purchaseOrderId;
    private String itemId;
    private String accountId;
    private Integer quantityAvailable;
    private String sku;
    private Long watchCount;
    private String itemName;
    private String itemDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getSchedulerId() {
        return schedulerId;
    }

    public void setSchedulerId(String schedulerId) {
        this.schedulerId = schedulerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Long watchCount) {
        this.watchCount = watchCount;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
