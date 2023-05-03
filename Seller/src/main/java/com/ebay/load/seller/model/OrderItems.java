package com.ebay.load.seller.model;
/*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderItems.
 */
/*@Entity
@Table(name = "order_items")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)*/
public class OrderItems{/* implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "receive_date")
    private String receiveDate;

    @Column(name = "total_spending")
    private Long totalSpending;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status_id")
    private String statusId;

    @Column(name = "quantity_in")
    private Long quantityIn;

    @Column(name = "quantity_out")
    private Long quantityOut;

    @Column(name = "notes")
    private String notes;

    @OneToOne
    @JoinColumn(unique = true)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(unique = true)
    private Category category;

    @OneToOne
    @JoinColumn(unique = true)
    private Condition condition;

    @OneToOne
    @JoinColumn(unique = true)
    private NameEntity names;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Orders orders;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Stock users;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public OrderItems category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCondition() {
        return condition;
    }

    public OrderItems condition(String condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPrice() {
        return price;
    }

    public OrderItems price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public OrderItems quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getVendor() {
        return vendor;
    }

    public OrderItems vendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getStatus() {
        return status;
    }

    public OrderItems status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public OrderItems receiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
        return this;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Long getTotalSpending() {
        return totalSpending;
    }

    public OrderItems totalSpending(Long totalSpending) {
        this.totalSpending = totalSpending;
        return this;
    }

    public void setTotalSpending(Long totalSpending) {
        this.totalSpending = totalSpending;
    }

    public String getCurrency() {
        return currency;
    }

    public OrderItems currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatusId() {
        return statusId;
    }

    public OrderItems statusId(String statusId) {
        this.statusId = statusId;
        return this;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Long getQuantityIn() {
        return quantityIn;
    }

    public OrderItems quantityIn(Long quantityIn) {
        this.quantityIn = quantityIn;
        return this;
    }

    public void setQuantityIn(Long quantityIn) {
        this.quantityIn = quantityIn;
    }

    public Long getQuantityOut() {
        return quantityOut;
    }

    public OrderItems quantityOut(Long quantityOut) {
        this.quantityOut = quantityOut;
        return this;
    }

    public void setQuantityOut(Long quantityOut) {
        this.quantityOut = quantityOut;
    }

    public String getNotes() {
        return notes;
    }

    public OrderItems notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderItems status(OrderStatus orderStatus) {
        this.status = orderStatus;
        return this;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public Category getCategory() {
        return category;
    }

    public OrderItems category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Condition getCondition() {
        return condition;
    }

    public OrderItems condition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public NameEntity getNames() {
        return names;
    }

    public OrderItems names(NameEntity nameEntity) {
        this.names = nameEntity;
        return this;
    }

    public void setNames(NameEntity nameEntity) {
        this.names = nameEntity;
    }

    public Orders getOrders() {
        return orders;
    }

    public OrderItems orders(Orders orders) {
        this.orders = orders;
        return this;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Stock getUsers() {
        return users;
    }

    public OrderItems users(Stock stock) {
        this.users = stock;
        return this;
    }

    public void setUsers(Stock stock) {
        this.users = stock;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItems orderItems = (OrderItems) o;
        if (orderItems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderItems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderItems{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", condition='" + getCondition() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", vendor='" + getVendor() + "'" +
            ", status='" + getStatus() + "'" +
            ", receiveDate='" + getReceiveDate() + "'" +
            ", totalSpending=" + getTotalSpending() +
            ", currency='" + getCurrency() + "'" +
            ", statusId='" + getStatusId() + "'" +
            ", quantityIn=" + getQuantityIn() +
            ", quantityOut=" + getQuantityOut() +
            ", notes='" + getNotes() + "'" +
            "}";
    }*/
}
