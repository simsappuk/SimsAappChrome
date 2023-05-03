
package com.ims.orm.model;


import com.ims.orm.common.TenantBaseEntity;
import com.ims.orm.common.UserGroup;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Users extends TenantBaseEntity {

    private static final long serialVersionUID = -2175150694352541150L;

   // @Indexed("user_username_t")
    private String username = null;
    private String password = null;
  //  @Indexed("user_firstname_t")
    private String firstname;
   // @Indexed("user_lastname_t")
    private String lastname;
  //  @Indexed("user_email_s")
    private String email;
    private String promoCode;
  //  @Indexed("user_enabled_b")
    private Boolean enabled = false;
  //  @Indexed("user_company_t")
    private String company;
  //  @Indexed("user_job_title_t")
    private String jobTitle;
  //  @Indexed("user_phone_t")
    private String phoneNumber;



    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> authorities = new HashSet<>();

  /*  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "users_user_groups",
            joinColumns = {
                    @JoinColumn(name = "users_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_groups_id", nullable = false, updatable = false)}
    )
    private Set<UserGroup> userGroups = new HashSet<>();
*/
  //  @Indexed("entitled_groups_ss")
    @ElementCollection(fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
    private Set<String> userGroupIds = new HashSet<>();

     private Boolean orgPublicPageEnabled;

  //  @Indexed("user_source_t")
    private String source;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

  /*  public Organization getOrganization() {
        return organization;
    }*/

 /*   public void setOrganization(Organization organization) {
        this.organization = organization;
    }*/

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
/*
    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }
*/
    public Set<String> getUserGroupIds() {
        for (UserGroup ug : getUserGroups()) {
            userGroupIds.add(ug.getId());
        }
        return userGroupIds;
    }

    public void setUserGroupIds(Set<String> userGroupIds) {
        this.userGroupIds = userGroupIds;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }
/*
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public DataCenter getPreferredDataCenter() {
        return preferredDataCenter;
    }

    public void setPreferredDataCenter(DataCenter preferredDataCenter) {
        this.preferredDataCenter = preferredDataCenter;
    }
*/

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
/*
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }
*/
    public Boolean getOrgPublicPageEnabled() {
        return orgPublicPageEnabled;
    }

    public void setOrgPublicPageEnabled(Boolean orgPublicPageEnabled) {
        this.orgPublicPageEnabled = orgPublicPageEnabled;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }
}
