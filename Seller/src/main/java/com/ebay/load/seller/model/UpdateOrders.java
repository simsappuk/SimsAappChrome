package com.ebay.load.seller.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name ="update_orders")
public class UpdateOrders {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private Date passingDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPassingDate() {
        return passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
    }
}
