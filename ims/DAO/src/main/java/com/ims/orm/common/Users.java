
package com.ims.orm.common;


import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Users entity.
 *
 * @author Intesar Mohammed
 */
//@Cacheable(value = "userCache")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
@Entity
//@SolrDocument(solrCoreName = "collection1")
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

 //   @Indexed("cli_blacklist_t")
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
    private String cliBlackList;

    //@Field("user_org_s")
    //@Indexed(stored = false, readonly = true)
   // @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
  //  private Organization organization;

    //@Field("user_profile_s")
    //@Indexed(stored = false)
   // @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
  //  private Profile profile;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> authorities = new HashSet<>();

    //@Field("user_groups_ss")
    //@Indexed(stored = false, readonly = true)
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
    @JoinTable(name = "users_user_groups",
            joinColumns = {
                    @JoinColumn(name = "users_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_groups_id", nullable = false, updatable = false)}
    )
    private Set<UserGroup> userGroups = new HashSet<>();

  //  @Indexed("entitled_groups_ss")
    @ElementCollection(fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
    private Set<String> userGroupIds = new HashSet<>();

    //@Field("user_datacenter_s")
    //@Indexed(stored = false, readonly = true)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user_region")
   // private DataCenter preferredDataCenter;

    //@Column(nullable = false, columnDefinition = "int default 0")
    private Integer memoryUsed;

    // @Column(nullable = false, columnDefinition = "int default 0")
    private Integer cpuUsed;

  //  @Indexed("user_org_id_s")
    private String orgId;
  //  @Indexed("user_org_desc_t")
    private String orgDescription;

    private Boolean orgPublicPageEnabled;

  //  @Indexed("user_source_t")
    private String source;

    @PreUpdate
    public void preMergeU() {
        memoryUsed = memoryUsed == null ? 0 : memoryUsed;
        cpuUsed = cpuUsed == null ? 0 : cpuUsed;
        if (userGroupIds != null) {
            userGroupIds.clear();
        }
        if (CollectionUtils.isNotEmpty(userGroups)) {
            for (UserGroup ug : userGroups) {
                userGroupIds.add(ug.getId());
            }
        }
    }


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

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

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
    public Integer getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Integer memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Integer getCpuUsed() {
        return cpuUsed;
    }

    public void setCpuUsed(Integer cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public String getCliBlackList() {
        return cliBlackList;
    }

    public void setCliBlackList(String cliBlackList) {
        this.cliBlackList = cliBlackList;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

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
