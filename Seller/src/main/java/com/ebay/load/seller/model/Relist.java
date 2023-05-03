package com.ebay.load.seller.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "relist")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Relist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "link")
    private String link;

    @Column(name = "item_id")
    private String itemId;

    @ElementCollection
    private List<String> itemIdList;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "listed_quantity")
    private Integer listedQuantity;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Column(name = "re_list_quantity")
    private Integer reListQuantity;

    @Column(name = "new_item_id")
    private String newItemId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private Double price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "title")
    private String title;

    @Column(name = "last_effective_date")
    private Date lastEffectiveDate;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "item_exist")
    private String itemExist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<String> getItemIdList() {
        return itemIdList;
    }

    public void setItemIdList(List<String> itemIdList) {
        this.itemIdList = itemIdList;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getListedQuantity() {
        return listedQuantity;
    }

    public void setListedQuantity(Integer listedQuantity) {
        this.listedQuantity = listedQuantity;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Integer getReListQuantity() {
        return reListQuantity;
    }

    public void setReListQuantity(Integer reListQuantity) {
        this.reListQuantity = reListQuantity;
    }

    public String getNewItemId() {
        return newItemId;
    }

    public void setNewItemId(String newItemId) {
        this.newItemId = newItemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getItemExist() {
        return itemExist;
    }

    public void setItemExist(String itemExist) {
        this.itemExist = itemExist;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}