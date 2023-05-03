package com.ebay.load.seller.model;

import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "stock")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stock extends ResponseEntity<List<Stock>> implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "item_category")
    private String itemCategory;

    private String itemSubCategory;

    @Column(name = "item_condition")
    private String itemCondition;

    @Column(name = "avg_price")
    private Double avgPrice;

    @Column(name = "order_ref_id")
    private String orderRefId;

    @Column(name = "buy_it_now_price")
    private Double buyItNowPrice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "start_effective_date")
    private Date startEffectiveDate;

    @Column(name = "last_effective_date")
    private Date lastEffectiveDate;

    @Column(name = "listing_duration")
    private String listingDuration;

    @Column(name = "listing_type")
    private String listingType;

    @Column(name = "online_quantity")
    private Integer onlineQuantity;

    @Column(name = "title")
    private String title;

    @ElementCollection
    private List<String> sku;

    @Column(name="sku_number")
    private Integer skuNumber;

    @Column(name="account_id")
    private String accountId;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "shipping_profile_name")
    private String shippingProfileName;

    @Column(name = "return_profile_name")
    private String returnProfileName;

    @Column(name = "payment_profile_name")
    private String paymentProfileName;

    @Column(name="stock_delete")
    private String stockDelete;

    private Integer stockLimit;
    private String modelName;
    private String paypalEmailId;
    private String stockSource;
    private String serialNumber;

    @ElementCollection
    private List<String> itemId;


    @ElementCollection
    private List<ItemDetails> itemDetails;

    @ElementCollection
    private List<String> ean;
    private String tariffCode;
    private Integer lineId;
    private String entireTitle;
    private Boolean linkAwaitingDispatch;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Double getBuyItNowPrice() {
        return buyItNowPrice;
    }

    public void setBuyItNowPrice(Double buyItNowPrice) {
        this.buyItNowPrice = buyItNowPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getStartEffectiveDate() {
        return startEffectiveDate;
    }

    public void setStartEffectiveDate(Date startEffectiveDate) {
        this.startEffectiveDate = startEffectiveDate;
    }


    public String getListingDuration() {
        return listingDuration;
    }

    public void setListingDuration(String listingDuration) {
        this.listingDuration = listingDuration;
    }

    public Integer getOnlineQuantity() {
        return onlineQuantity;
    }

    public void setOnlineQuantity(Integer onlineQuantity) {
        this.onlineQuantity = onlineQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSku() {
        return sku;
    }

    public void setSku(List<String> sku) {
        this.sku = sku;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShippingProfileName() {
        return shippingProfileName;
    }

    public void setShippingProfileName(String shippingProfileName) {
        this.shippingProfileName = shippingProfileName;
    }

    public String getReturnProfileName() {
        return returnProfileName;
    }

    public void setReturnProfileName(String returnProfileName) {
        this.returnProfileName = returnProfileName;
    }

    public String getPaymentProfileName() {
        return paymentProfileName;
    }

    public void setPaymentProfileName(String paymentProfileName) {
        this.paymentProfileName = paymentProfileName;
    }

    public Date getLastEffectiveDate() {
        return lastEffectiveDate;
    }

    public void setLastEffectiveDate(Date lastEffectiveDate) {
        this.lastEffectiveDate = lastEffectiveDate;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getOrderRefId() {
        return orderRefId;
    }

    public void setOrderRefId(String orderRefId) {
        this.orderRefId = orderRefId;
    }

    public String getStockDelete() {
        return stockDelete;
    }

    public void setStockDelete(String stockDelete) {
        this.stockDelete = stockDelete;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public Integer getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(Integer skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getStockLimit() {
        return stockLimit;
    }

    public void setStockLimit(Integer stockLimit) {
        this.stockLimit = stockLimit;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPaypalEmailId() {
        return paypalEmailId;
    }

    public void setPaypalEmailId(String paypalEmailId) {
        this.paypalEmailId = paypalEmailId;
    }

    public String getStockSource() {
        return stockSource;
    }

    public void setStockSource(String stockSource) {
        this.stockSource = stockSource;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<String> getItemId() {
        return itemId;
    }

    public void setItemId(List<String> itemId) {
        this.itemId = itemId;
    }

    public List<String> getEan() {
        return ean;
    }

    public void setEan(List<String> ean) {
        this.ean = ean;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public String getItemSubCategory() {
        return itemSubCategory;
    }

    public void setItemSubCategory(String itemSubCategory) {
        this.itemSubCategory = itemSubCategory;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public List<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getEntireTitle() {
        return entireTitle;
    }

    public void setEntireTitle(String entireTitle) {
        this.entireTitle = entireTitle;
    }

    public Boolean getLinkAwaitingDispatch() {
        return linkAwaitingDispatch;
    }

    public void setLinkAwaitingDispatch(Boolean linkAwaitingDispatch) {
        this.linkAwaitingDispatch = linkAwaitingDispatch;
    }
}
/*    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }*/
