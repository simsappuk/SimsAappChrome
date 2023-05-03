package com.ebay.load.seller.model;

import com.ebay.soap.eBLBaseComponents.NameValueListArrayType;
import com.ebay.soap.eBLBaseComponents.NameValueListType;
import com.ebay.soap.eBLBaseComponents.VariationType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;



@Entity
@Table(name ="orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders extends Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "order_reference_number")
    private String orderReferenceNumber;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "item_id")
    private String itemId;

    @Column(name="sku")
    private String sku;

    @Column(name="spreadsheet_id")
    private String spreadsheetId;

    @Column(name = "name")
    private String name;

    @Column(name = "status_id")
    private String statusId;


    @Column(name = "notes")
    private String notes;

    @Column(name = "owner_id_pk")
    private String ownerIdPk;

    @Column(name="title")
    private String  title;

    @OneToOne
    @JoinColumn(unique = true)
    private Vendors vendors;

    @Column(name="quantity")
    private String quantity;

    @Column(name="total")
    private String total;

    @Column(name="status")
    private String status;

    @Column(name="description")
    private String description;

    @Column(name="category")
    private String category;

  //  @Column(name="condition")
    //private String condition;

    @Column(name="price")
    private String price;

    @Column(name="received_date")
    private String receivedDate;

    @OneToOne
    @JoinColumn(name="condition_id")
    private Condition condition;


    @OneToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(name="order_id")
    private String orderId;

    @Column(name="buyer_email_id")
    private String buyerEmailId;

    @Column(name="selling_record_number")
    private String sellingRecordNumber;

    @Column(name="buyer_name")
    private String buyerName;

    @Column(name="shipped_to_country")
    private String shippedToCountry;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="original_item_price")
    private String originalItemPrice;

    @Column(name="seller_email")
    private String sellerEmail;

    @Column(name="buyer_user_id")
    private String buyerUserId;

    @Column(name="seller_user_id")
    private String sellerUserId;

    @Column(name="cancel_status")
    private String cancelStatus;

    @Column(name="extended_order_id")
    private String extendedOrderId;

    @Column(name="total_amount")
    private String totalAmount;

    @Column(name="adjustment_amount")
    private String adjustmentAmount;

    @Column(name="amount_paid")
    private String amountPaid;

    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="p_reference_number")
    private String pReferenceNumber;

    @Column(name="transaction_date")
    private Calendar transactionDate;

    @Column(name="last_modified_date")
    private Calendar lastModifiedDate;

    @Column(name="item_discount_amount")
    private String itemDiscountAmount;

    @Column(name="sub_total")
    private String subTotal;

    @Column(name="total_amount_currency_id")
    private String totalAmountCurrencyId;

    @Column(name="sub_amount_currency_id")
    private String subTotalCurrencyId;

    @Column(name="condition_display_name")
    private String conditionDisplayName;

    @Column(name="quantity_purchased")
    private String quantityPurchased;

    @Column(name="vat_percent")
    private String vatPercent;

    @Column(name="grand_total")
    private String grandTotal;

    @Column(name="item_discount_price")
    private String itemDiscountPrice;

    @Column(name="shipping_service_cost")
    private String shippingServiceCost;

    @Column(name="shipping_service_cost_currency_id")
    private String shippingServiceCostCurrencyId;

    @Column(name="transaction_price")
    private String transactionPrice;

    @Column(name="grand_total_currency_id")
    private String grandTotalCurrencyId;

    @Column(name="original_item_price_currency_id")
    private String originalItemPriceCurrencyId;

    @Column(name="discount_price_currency_id")
    private String discountPriceCurrencyId;

    @Column(name="adjustment_amount_currency_id")
    private String adjustmentAmountCurrencyId;

    @Column(name="transaction_price_currency_id")
    private String transactionPriceCurrencyId;

    @Column(name="item_image")
    private String itemImage;

    @Column(name="account_id")
    private String accountId;

    @Column(name = "last_effective_date")
    private Date lastEffectiveDate;

    @Column(name = "shipped_date")
    private Date shippedDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "print_date")
    private Date printDate;

    @Column(name = "paid_date")
    private Date paidDate;

    @Column(name = "replace_date")
    private Date replaceDate;


    @Column(name = "order_ref")
    private Integer orderRef;

    @Column(name = "p_order_ref")
    private Integer pOrderRef;

    @Column(name = "trans_date_ref")
    private Date transDateRef;

    @Column(name="transaction_array_length")
    private Integer transactionArrayLength;

    @Column(name = "sheet_Number")
    private String sheetNumber;

    @Column(name = "duplicate_id")
    private String duplicateId;

    @Column(name = "order_dropship_status")
    private String orderDropshipStatus;

    @Column(name = "check_dropship_order")
    private Boolean checkDropshipOrder;

    @Column(name = "buyer_street1")
    private String buyerStreet1;

    @Column(name = "buyer_street2")
    private String buyerStreet2;

    @Column(name = "buyer_city")
    private String buyerCity;

    @Column(name = "buyer_phone_number")
    private String buyerPhoneNumber;

    @Column(name = "buyer_postal_code")
    private String buyerPostalCode;

    @Column(name = "buyer_state")
    private String buyerState;

    @Column(name = "buyer_country")
    private String buyerCountry;

    @Column(name = "shipping_service_selected")
    private String shippingServiceSelected;

    @Column(name="order_variation_name")
    private String orderVariationName;

    @Column(name="order_variation_value")
    private String orderVariationValue;

    @Column(name="order_tracking_id")
    private String orderTrackingId;

    @Column(name="sent_text_message")
    private String sentTextMessage;

    @Column(name="order_specifics")
    private NameValueListType[] orderSpecifics;

    @Column(name="picking_type")
    private String pickingType;

    @Column(name = "check_reset_order")
    private Boolean checkResetOrder;

    @ElementCollection
    private List<NameValueListType> totalOrderSpecifics;

    @Column(name="buyer_checkout_message")
    private String buyerCheckoutMessage;

    @Column(name = "pdf_file")
    private byte[] pdfFile;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="vat_ioss_number")
    private String vatIossNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getOrderReferenceNumber() {
        return orderReferenceNumber;
    }

    public Orders orderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
        return this;
    }

    public void setOrderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Orders orderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public Orders name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusId() {
        return statusId;
    }

    public Orders statusId(String statusId) {
        this.statusId = statusId;
        return this;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getNotes() {
        return notes;
    }

    public Orders notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Vendors getVendors() {
        return vendors;
    }

    public Orders vendors(Vendors vendors) {
        this.setVendors(vendors);
        return this;
    }

    public void setVendors(Vendors vendors) {
        this.vendors = vendors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public boolean equals(Orders obj) {

        try{
            if(this.buyerPhoneNumber==null){
                if(obj.buyerPhoneNumber!=null)
                    return false;
            } else if (obj.buyerPhoneNumber!=null && !this.buyerPhoneNumber.equals(obj.buyerPhoneNumber)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerPostalCode==null){
                if(obj.buyerPostalCode!=null)
                    return false;
            } else if (obj.buyerPostalCode!=null && !this.buyerPostalCode.equals(obj.buyerPostalCode)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerState==null){
                if(obj.buyerState!=null)
                    return false;
            } else if (obj.buyerState!=null && !this.buyerState.equals(obj.buyerState)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerCountry==null){
                if(obj.buyerCountry!=null)
                    return false;
            } else if (obj.buyerCountry!=null && !this.buyerCountry.equals(obj.buyerCountry)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.shippingServiceSelected==null){
                if(obj.shippingServiceSelected!=null)
                    return false;
            } else if (obj.shippingServiceSelected!=null && !this.shippingServiceSelected.equals(obj.shippingServiceSelected)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerCity==null){
                if(obj.buyerCity!=null)
                    return false;
            } else if (obj.buyerCity!=null && !this.buyerCity.equals(obj.buyerCity)) return false;
        }catch(Exception e){ e.printStackTrace();}
        try{
            if(this.buyerStreet2==null){
                if(obj.buyerStreet2!=null)
                    return false;
            } else if (obj.buyerStreet2!=null && !this.buyerStreet2.equals(obj.buyerStreet2)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerStreet1==null){
                if(obj.buyerStreet1!=null)
                    return false;
            } else if (obj.buyerStreet1!=null && !this.buyerStreet1.equals(obj.buyerStreet1)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try {
            if(this.orderDate==null){
                if(obj.orderDate!=null)
                    return false;
            } else if (obj.orderDate!=null && !this.orderDate.equals(obj.orderDate)) return false;
        }catch(Exception e){ e.printStackTrace();}
        if(!this.orderType.equals(obj.orderType)) return false;

        try{
            if(this.itemId==null){
                if(obj.itemId!=null)
                    return false;
            } else if (obj.itemId!=null && !this.itemId.equals(obj.itemId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(this.paidDate==null){
                if(obj.paidDate!=null)
                    return false;
            } else if (obj.paidDate!=null && !sdf.format(this.paidDate).equals(sdf.format(obj.paidDate))) return false;
        }catch(Exception e){ e.printStackTrace();}

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(this.shippedDate==null){
                if(obj.shippedDate!=null)
                    return false;
            } else if (obj.shippedDate!=null && !sdf.format(this.shippedDate).equals(sdf.format(obj.shippedDate))) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.sku==null){
                if(obj.sku!=null)
                    return false;
            } else if (obj.sku!=null && !this.sku.equals(obj.sku)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.name==null){
                if(obj.name!=null)
                    return false;
            } else if (obj.name!=null && !this.name.equals(obj.name)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.statusId==null){
                if(obj.statusId!=null)
                    return false;
            } else if (obj.statusId!=null && !this.statusId.equals(obj.statusId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.title==null){
                if(obj.title!=null)
                    return false;
            } else if (obj.title!=null && !this.title.equals(obj.title)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.quantityPurchased==null){
                if(obj.quantityPurchased!=null)
                    return false;
            } else if (obj.quantityPurchased!=null && !this.quantityPurchased.equals(obj.quantityPurchased)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.total==null){
                if(obj.total!=null)
                    return false;
            } else if (obj.total!=null && !this.total.equals(obj.total)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.status==null){
                if(obj.status!=null)
                    return false;
            } else if (obj.status!=null && !this.status.equals(obj.status)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.description==null){
                if(obj.description!=null)
                    return false;
            } else if (obj.description!=null && !this.description.equals(obj.description)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.category==null){
                if(obj.category!=null)
                    return false;
            } else if (obj.category!=null && !this.category.equals(obj.category)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.condition==null){
                if(obj.condition!=null)
                    return false;
            } else if (obj.condition!=null && !this.condition.equals(obj.condition)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.orderId==null){
                if(obj.orderId!=null)
                    return false;
            } else if (obj.orderId!=null && !this.orderId.equals(obj.orderId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerEmailId==null){
                if(obj.buyerEmailId!=null)
                    return false;
            } else if (obj.buyerEmailId!=null && !this.buyerEmailId.equals(obj.buyerEmailId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.sellingRecordNumber==null){
                if(obj.sellingRecordNumber!=null)
                    return false;
            } else if (obj.sellingRecordNumber!=null && !this.sellingRecordNumber.equals(obj.sellingRecordNumber)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerName==null){
                if(obj.buyerName!=null)
                    return false;
            } else if (obj.buyerName!=null && !this.buyerName.equals(obj.buyerName)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.shippedToCountry==null){
                if(obj.shippedToCountry!=null)
                    return false;
            } else if (obj.shippedToCountry!=null && !this.shippedToCountry.equals(obj.shippedToCountry)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.orderStatus==null){
                if(obj.orderStatus!=null)
                    return false;
            } else if (obj.orderStatus!=null && !this.orderStatus.equals(obj.orderStatus)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.originalItemPrice==null){
                if(obj.originalItemPrice!=null)
                    return false;
            } else if (obj.originalItemPrice!=null && !this.originalItemPrice.equals(obj.originalItemPrice)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.sellerEmail==null){
                if(obj.sellerEmail!=null)
                    return false;
            } else if (obj.sellerEmail!=null && !this.sellerEmail.equals(obj.sellerEmail)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerUserId==null){
                if(obj.buyerUserId!=null)
                    return false;
            } else if (obj.buyerUserId!=null && !this.buyerUserId.equals(obj.buyerUserId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.sellerUserId==null){
                if(obj.sellerUserId!=null)
                    return false;
            } else if (obj.sellerUserId!=null && !this.sellerUserId.equals(obj.sellerUserId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.cancelStatus==null){
                if(obj.cancelStatus!=null)
                    return false;
            } else if (obj.cancelStatus!=null && !this.cancelStatus.equals(obj.cancelStatus)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.extendedOrderId==null){
                if(obj.extendedOrderId!=null)
                    return false;
            } else if (obj.extendedOrderId!=null && !this.extendedOrderId.equals(obj.extendedOrderId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.totalAmount==null){
                if(obj.totalAmount!=null)
                    return false;
            } else if (obj.totalAmount!=null && !this.totalAmount.equals(obj.totalAmount)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.adjustmentAmount==null){
                if(obj.adjustmentAmount!=null)
                    return false;
            } else if (obj.adjustmentAmount!=null && !this.adjustmentAmount.equals(obj.adjustmentAmount)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.amountPaid==null){
                if(obj.amountPaid!=null)
                    return false;
            } else if (obj.amountPaid!=null && !this.amountPaid.equals(obj.amountPaid)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.transactionId==null){
                if(obj.transactionId!=null)
                    return false;
            } else if (obj.transactionId!=null && !this.transactionId.equals(obj.transactionId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.pReferenceNumber==null){
                if(obj.pReferenceNumber!=null)
                    return false;
            } else if (obj.pReferenceNumber!=null && !this.pReferenceNumber.equals(obj.pReferenceNumber)) return false;
        }catch(Exception e){ e.printStackTrace();}

        //  if( && !this.transactionDate.equals(obj.transactionDate)) return false;

        try{
            if(this.itemDiscountAmount==null){
                if(obj.itemDiscountAmount!=null)
                    return false;
            } else if (obj.itemDiscountAmount!=null && !this.itemDiscountAmount.equals(obj.itemDiscountAmount)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.subTotal==null){
                if(obj.subTotal!=null)
                    return false;
            } else if (obj.subTotal!=null && !this.subTotal.equals(obj.subTotal)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.conditionDisplayName==null){
                if(obj.conditionDisplayName!=null)
                    return false;
            } else if (obj.conditionDisplayName!=null && !this.conditionDisplayName.equals(obj.conditionDisplayName)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.vatPercent==null){
                if(obj.vatPercent!=null)
                    return false;
            } else if (obj.vatPercent!=null && !this.vatPercent.equals(obj.vatPercent)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.grandTotal==null){
                if(obj.grandTotal!=null)
                    return false;
            } else if (obj.grandTotal!=null && !this.grandTotal.equals(obj.grandTotal)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.itemDiscountPrice==null){
                if(obj.itemDiscountPrice!=null)
                    return false;
            } else if (obj.itemDiscountPrice!=null && !this.itemDiscountPrice.equals(obj.itemDiscountPrice)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.shippingServiceCost==null){
                if(obj.shippingServiceCost!=null)
                    return false;
            } else if (obj.shippingServiceCost!=null && !this.shippingServiceCost.equals(obj.shippingServiceCost)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.transactionPrice==null){
                if(obj.transactionPrice!=null)
                    return false;
            } else if (obj.transactionPrice!=null && !this.transactionPrice.equals(obj.transactionPrice)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.itemImage==null){
                if(obj.itemImage!=null)
                    return false;
            } else if (obj.itemImage!=null && !this.itemImage.equals(obj.itemImage)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.orderVariationName==null){
                if(obj.orderVariationName!=null)
                    return false;
            } else if (obj.orderVariationName!=null && !this.orderVariationName.equals(obj.orderVariationName)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.orderVariationValue==null){
                if(obj.orderVariationValue!=null)
                    return false;
            } else if (obj.orderVariationValue!=null && !this.orderVariationValue.equals(obj.orderVariationValue)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.orderTrackingId==null){
                if(obj.orderTrackingId!=null)
                    return false;
            } else if (obj.orderTrackingId!=null && !this.orderTrackingId.equals(obj.orderTrackingId)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.sentTextMessage==null){
                if(obj.sentTextMessage!=null)
                    return false;
            } else if (obj.sentTextMessage!=null && !this.sentTextMessage.equals(obj.sentTextMessage)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.pickingType==null){
                if(obj.pickingType!=null)
                    return false;
            } else if (obj.pickingType!=null && !this.pickingType.equals(obj.pickingType)) return false;
        }catch(Exception e){ e.printStackTrace();}

        try{
            if(this.buyerCheckoutMessage==null){
                if(obj.buyerCheckoutMessage!=null)
                    return false;
            } else if (obj.buyerCheckoutMessage!=null && !this.buyerCheckoutMessage.equals(obj.buyerCheckoutMessage)) return false;
        }catch(Exception e){ e.printStackTrace();}

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + getId() +
                ", orderReferenceNumber='" + getOrderReferenceNumber() + "'" +
                ", orderDate='" + getOrderDate() + "'" +
                ", name='" + getName() + "'" +
                ", statusId='" + getStatusId() + "'" +
                ", notes='" + getNotes() + "'" +
                "}";
    }

    public String getOwnerIdPk() {
        return ownerIdPk;
    }

    public void setOwnerIdPk(String ownerIdPk) {
        this.ownerIdPk = ownerIdPk;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }


    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpreadsheetId() {
        return spreadsheetId;
    }

    public void setSpreadsheetId(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerEmailId() {
        return buyerEmailId;
    }

    public void setBuyerEmailId(String buyerEmailId) {
        this.buyerEmailId = buyerEmailId;
    }

    public String getSellingRecordNumber() {
        return sellingRecordNumber;
    }

    public void setSellingRecordNumber(String sellingRecordNumber) {
        this.sellingRecordNumber = sellingRecordNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getShippedToCountry() {
        return shippedToCountry;
    }

    public void setShippedToCountry(String shippedToCountry) {
        this.shippedToCountry = shippedToCountry;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOriginalItemPrice() {
        return originalItemPrice;
    }

    public void setOriginalItemPrice(String originalItemPrice) {
        this.originalItemPrice = originalItemPrice;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getExtendedOrderId() {
        return extendedOrderId;
    }

    public void setExtendedOrderId(String extendedOrderId) {
        this.extendedOrderId = extendedOrderId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Calendar transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Calendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getItemDiscountAmount() {
        return itemDiscountAmount;
    }

    public void setItemDiscountAmount(String itemDiscountAmount) {
        this.itemDiscountAmount = itemDiscountAmount;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTotalAmountCurrencyId() {
        return totalAmountCurrencyId;
    }

    public void setTotalAmountCurrencyId(String totalAmountCurrencyId) {
        this.totalAmountCurrencyId = totalAmountCurrencyId;
    }

    public String getSubTotalCurrencyId() {
        return subTotalCurrencyId;
    }

    public void setSubTotalCurrencyId(String subTotalCurrencyId) {
        this.subTotalCurrencyId = subTotalCurrencyId;
    }

    public String getConditionDisplayName() {
        return conditionDisplayName;
    }

    public void setConditionDisplayName(String conditionDisplayName) {
        this.conditionDisplayName = conditionDisplayName;
    }

    public String getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(String quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getItemDiscountPrice() {
        return itemDiscountPrice;
    }

    public void setItemDiscountPrice(String itemDiscountPrice) {
        this.itemDiscountPrice = itemDiscountPrice;
    }

    public String getShippingServiceCost() {
        return shippingServiceCost;
    }

    public void setShippingServiceCost(String shippingServiceCost) {
        this.shippingServiceCost = shippingServiceCost;
    }

    public String getShippingServiceCostCurrencyId() {
        return shippingServiceCostCurrencyId;
    }

    public void setShippingServiceCostCurrencyId(String shippingServiceCostCurrencyId) {
        this.shippingServiceCostCurrencyId = shippingServiceCostCurrencyId;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getGrandTotalCurrencyId() {
        return grandTotalCurrencyId;
    }

    public void setGrandTotalCurrencyId(String grandTotalCurrencyId) {
        this.grandTotalCurrencyId = grandTotalCurrencyId;
    }

    public String getOriginalItemPriceCurrencyId() {
        return originalItemPriceCurrencyId;
    }

    public void setOriginalItemPriceCurrencyId(String originalItemPriceCurrencyId) {
        this.originalItemPriceCurrencyId = originalItemPriceCurrencyId;
    }

    public String getDiscountPriceCurrencyId() {
        return discountPriceCurrencyId;
    }

    public void setDiscountPriceCurrencyId(String discountPriceCurrencyId) {
        this.discountPriceCurrencyId = discountPriceCurrencyId;
    }

    public String getAdjustmentAmountCurrencyId() {
        return adjustmentAmountCurrencyId;
    }

    public void setAdjustmentAmountCurrencyId(String adjustmentAmountCurrencyId) {
        this.adjustmentAmountCurrencyId = adjustmentAmountCurrencyId;
    }

    public String getTransactionPriceCurrencyId() {
        return transactionPriceCurrencyId;
    }

    public void setTransactionPriceCurrencyId(String transactionPriceCurrencyId) {
        this.transactionPriceCurrencyId = transactionPriceCurrencyId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Date getLastEffectiveDate() {
        return lastEffectiveDate;
    }

    public void setLastEffectiveDate(Date lastEffectiveDate) {
        this.lastEffectiveDate = lastEffectiveDate;
    }

    public String getpReferenceNumber() {
        return pReferenceNumber;
    }

    public void setpReferenceNumber(String pReferenceNumber) {
        this.pReferenceNumber = pReferenceNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }

    public Date getReplaceDate() {
        return replaceDate;
    }

    public void setReplaceDate(Date replaceDate) {
        this.replaceDate = replaceDate;
    }


    public Integer getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(Integer orderRef) {
        this.orderRef = orderRef;
    }

    public Integer getpOrderRef() {
        return pOrderRef;
    }

    public void setpOrderRef(Integer pOrderRef) {
        this.pOrderRef = pOrderRef;
    }

    public Date getTransDateRef() {
        return transDateRef;
    }

    public void setTransDateRef(Date transDateRef) {
        this.transDateRef = transDateRef;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Integer getTransactionArrayLength() {
        return transactionArrayLength;
    }

    public void setTransactionArrayLength(Integer transactionArrayLength) {
        this.transactionArrayLength = transactionArrayLength;
    }

    public String getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(String sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public String getDuplicateId() {
        return duplicateId;
    }

    public void setDuplicateId(String duplicateId) {
        this.duplicateId = duplicateId;
    }

    public String getOrderDropshipStatus() {
        return orderDropshipStatus;
    }

    public void setOrderDropshipStatus(String orderDropshipStatus) {
        this.orderDropshipStatus = orderDropshipStatus;
    }

    public Boolean getCheckDropshipOrder() {
        return checkDropshipOrder;
    }

    public void setCheckDropshipOrder(Boolean checkDropshipOrder) {
        this.checkDropshipOrder = checkDropshipOrder;
    }

    public String getBuyerStreet1() {
        return buyerStreet1;
    }

    public void setBuyerStreet1(String buyerStreet1) {
        this.buyerStreet1 = buyerStreet1;
    }

    public String getBuyerStreet2() {
        return buyerStreet2;
    }

    public void setBuyerStreet2(String buyerStreet2) {
        this.buyerStreet2 = buyerStreet2;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public String getBuyerPostalCode() {
        return buyerPostalCode;
    }

    public void setBuyerPostalCode(String buyerPostalCode) {
        this.buyerPostalCode = buyerPostalCode;
    }

    public String getBuyerState() {
        return buyerState;
    }

    public void setBuyerState(String buyerState) {
        this.buyerState = buyerState;
    }

    public String getShippingServiceSelected() {
        return shippingServiceSelected;
    }

    public void setShippingServiceSelected(String shippingServiceSelected) {
        this.shippingServiceSelected = shippingServiceSelected;
    }

    public String getBuyerCountry() {
        return buyerCountry;
    }

    public void setBuyerCountry(String buyerCountry) {
        this.buyerCountry = buyerCountry;
    }

    public String getOrderVariationName() {
        return orderVariationName;
    }

    public void setOrderVariationName(String orderVariationName) {
        this.orderVariationName = orderVariationName;
    }

    public String getOrderVariationValue() {
        return orderVariationValue;
    }

    public void setOrderVariationValue(String orderVariationValue) {
        this.orderVariationValue = orderVariationValue;
    }

    public String getOrderTrackingId() {
        return orderTrackingId;
    }

    public void setOrderTrackingId(String orderTrackingId) {
        this.orderTrackingId = orderTrackingId;
    }

    public String getSentTextMessage() {
        return sentTextMessage;
    }

    public void setSentTextMessage(String sentTextMessage) {
        this.sentTextMessage = sentTextMessage;
    }

    public NameValueListType[] getOrderSpecifics() {
        return orderSpecifics;
    }

    public void setOrderSpecifics(NameValueListType[] orderSpecifics) {
        this.orderSpecifics = orderSpecifics;
    }

    public String getPickingType() {
        return pickingType;
    }

    public void setPickingType(String pickingType) {
        this.pickingType = pickingType;
    }

    public List<NameValueListType> getTotalOrderSpecifics() {
        return totalOrderSpecifics;
    }

    public void setTotalOrderSpecifics(List<NameValueListType> totalOrderSpecifics) {
        this.totalOrderSpecifics = totalOrderSpecifics;
    }

    public Boolean getCheckResetOrder() {
        return checkResetOrder;
    }

    public void setCheckResetOrder(Boolean checkResetOrder) {
        this.checkResetOrder = checkResetOrder;
    }

    public String getBuyerCheckoutMessage() {
        return buyerCheckoutMessage;
    }

    public void setBuyerCheckoutMessage(String buyerCheckoutMessage) {
        this.buyerCheckoutMessage = buyerCheckoutMessage;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getVatIossNumber() {
        return vatIossNumber;
    }

    public void setVatIossNumber(String vatIossNumber) {
        this.vatIossNumber = vatIossNumber;
    }
}



