
package com.ims.orm.model;


import com.ims.orm.common.UserGroup;
import com.ims.orm.entity.audit.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Tenant extends BaseEntity {

    private static final long serialVersionUID = -2175150694352541150L;

    @Column(unique = true)
    // @Indexed("tenant_name_s")
    private String name = null;
    // private String email = null;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> defaultAuthorities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "tenant_default_group_ids", joinColumns = {
            @JoinColumn(name = "tenant_id", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "group_id", nullable = false, updatable = false)})
    private Set<UserGroup> userGroups = new HashSet<>();

    private String title;
    private String logo;
    private String color;
    private String company;
    private String department;
    private String contactName;
    private String contactTitle;
    private String contactEmail;
    private String contactPhone;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getDefaultAuthorities() {
        return defaultAuthorities;
    }

    public void setDefaultAuthorities(Set<String> defaultAuthorities) {
        this.defaultAuthorities = defaultAuthorities;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
