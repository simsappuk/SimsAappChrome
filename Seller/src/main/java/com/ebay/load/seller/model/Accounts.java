package com.ebay.load.seller.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name ="accounts")
public class Accounts extends Audit {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "accounts_id")
    private String id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "api_token")
    private String apiToken;

    @Column(name = "oauth_api_token")
    private String oauthApiToken;

    @Column(name="inactive")
    private Boolean inactive;

    @Column(name = "owner_id_pk")
    private String ownerIdPk;

    @Column(name = "tenant_id_pk")
    private String tenantIdPk;

    @Column(name = "url")
    private String url;

    @Column(name ="spread_sheet_id")
    private String spreadSheetId;

    private String awsAccessKeyId;
    private String awsClientSecretId;
    private String awsDeveloperId;
    private String awsSellerId;
    private String awsMarketPlaceId;
    private String awsAuthToken;
    private Boolean updateIndicator;



    public String getAccountName() {
        return accountName;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getOwnerIdPk() {
        return ownerIdPk;
    }

    public void setOwnerIdPk(String ownerIdPk) {
        this.ownerIdPk = ownerIdPk;
    }

    public String getTenantIdPk() {
        return tenantIdPk;
    }

    public void setTenantIdPk(String tenantIdPk) {
        this.tenantIdPk = tenantIdPk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getOauthApiToken() {
        return oauthApiToken;
    }

    public void setOauthApiToken(String oauthApiToken) {
        this.oauthApiToken = oauthApiToken;
    }

    public String getSpreadSheetId() {
        return spreadSheetId;
    }

    public void setSpreadSheetId(String spreadSheetId) {
        this.spreadSheetId = spreadSheetId;
    }

    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    public void setAwsAccessKeyId(String awsAccessKeyId) {
        this.awsAccessKeyId = awsAccessKeyId;
    }

    public String getAwsClientSecretId() {
        return awsClientSecretId;
    }

    public void setAwsClientSecretId(String awsClientSecretId) {
        this.awsClientSecretId = awsClientSecretId;
    }

    public String getAwsDeveloperId() {
        return awsDeveloperId;
    }

    public void setAwsDeveloperId(String awsDeveloperId) {
        this.awsDeveloperId = awsDeveloperId;
    }

    public String getAwsSellerId() {
        return awsSellerId;
    }

    public void setAwsSellerId(String awsSellerId) {
        this.awsSellerId = awsSellerId;
    }

    public String getAwsMarketPlaceId() {
        return awsMarketPlaceId;
    }

    public void setAwsMarketPlaceId(String awsMarketPlaceId) {
        this.awsMarketPlaceId = awsMarketPlaceId;
    }

    public String getAwsAuthToken() {
        return awsAuthToken;
    }

    public void setAwsAuthToken(String awsAuthToken) {
        this.awsAuthToken = awsAuthToken;
    }

    public Boolean getUpdateIndicator() {
        return updateIndicator;
    }

    public void setUpdateIndicator(Boolean updateIndicator) {
        this.updateIndicator = updateIndicator;
    }
}