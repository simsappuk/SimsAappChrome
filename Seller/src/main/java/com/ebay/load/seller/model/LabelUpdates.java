package com.ebay.load.seller.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="labelUpdates")
public class LabelUpdates {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    private String accountId;
    private String ownerId;
    private String initialRecordNumber;
    private String finalRecordNumber;
    private Date lastEffectiveDate;
    private String selected;
    @ElementCollection
    private List<String> range;

    public String getId() {
        return id;
    }
;
    public void setId(String id) {
        this.id = id;
    }

    public String getInitialRecordNumber() {
        return initialRecordNumber;
    }

    public void setInitialRecordNumber(String initialRecordNumber) {
        this.initialRecordNumber = initialRecordNumber;
    }

    public String getFinalRecordNumber() {
        return finalRecordNumber;
    }

    public void setFinalRecordNumber(String finalRecordNumber) {
        this.finalRecordNumber = finalRecordNumber;
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

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public List<String> getRange() {
        return range;
    }

    public void setRange(List<String> range) {
        this.range = range;
    }
}
