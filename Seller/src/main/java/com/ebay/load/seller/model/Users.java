package com.ebay.load.seller.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
@Table(name="users")
public class Users
{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id")
    private String id;

@Column(name = "email")
    private String email;


@Transient
@Column(name="password")
private String password;

@Column(name="name")
private String name;


private String lastName;

private String accountId;

@Column(name="in_active")
private boolean inactive;

@Column(name="contact_information")
private String contactInformation;

@Column(name = "mobile_number")
private String mobilePhone;

/*
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name="user_role",joinColumns =@JoinColumn(name = "user_id"),inverseJoinColumns =@JoinColumn(name="role_id") )
private Set<Roles> role;
*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
       this.inactive = inactive;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
/*
   public Set<Roles> getRole() {
     return role;
    }

    public void setRole(Set<Roles> role) {
        this.role = role;
   }
*/}








