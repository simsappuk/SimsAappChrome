package com.ebay.load.seller.converter;
import com.ebay.load.seller.model.Orders;
import com.ebay.soap.eBLBaseComponents.OrderType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EbayPurchaseOrdersConverter {
    public List<Orders> convertToOrders(OrderType orderTypes, String accountId, String ownerId) {
        List<Orders> list = new <Orders>ArrayList();
            list.addAll(convertOrderTypeToPurchaseOrders(orderTypes, accountId, ownerId));
        return list;
    }

    public List<Orders> convertOrderTypeToPurchaseOrders(OrderType ot, String accountId, String ownerId) {
        List<Orders> ol = new ArrayList<Orders>();
        Integer quantity = 0;
        Orders orders=new Orders();
        orders.setAccountId(accountId);
        orders.setOrderId(ot.getOrderID());
        if(ot.getCreatedTime()!=null)
        orders.setCreatedDate(ot.getCreatedTime().getTime());
        for (int i = 0; i < ot.getTransactionArray().getTransactionLength(); i++) {
            quantity += ot.getTransactionArray().getTransaction()[i].getQuantityPurchased();
            orders.setQuantityPurchased(String.valueOf(quantity));
        }
        orders.setOwnerIdPk(ownerId);
        if(ot.getTransactionArray().getTransaction()[0].getItem().getConditionDisplayName()!=null)
        orders.setConditionDisplayName(ot.getTransactionArray().getTransaction()[0].getItem().getConditionDisplayName());
        if(ot.getShippedTime()!=null)
            orders.setShippedDate(ot.getShippedTime().getTime());
        if(ot.getTransactionArray().getTransaction().length==1)
            orders.setItemId(ot.getTransactionArray().getTransaction()[0].getItem().getItemID());
        orders.setSellingRecordNumber(String.valueOf(ot.getShippingDetails().getSellingManagerSalesRecordNumber()));
        orders.setSellerUserId(ot.getSellerUserID());
        if(ot.getShippedTime()!=null)
            orders.setStatus("DISPATCHED");
        else if(ot.getCancelStatus().value().equals("Cancelled")||ot.getOrderStatus().value().equals("CancelClosedWithRefund") )
            orders.setStatus("CANCELLED");
        else if(ot.getShippedTime()==null)
            orders.setStatus("AWAITING SHIPMENT");
        if(ot.getPaidTime()!=null)
            orders.setPaidDate(ot.getPaidTime().getTime());
        else
            orders.setStatus("AWAITING PAYMENT");
        if(!ot.getShippingAddress().getName().equals(""))
            orders.setBuyerName(ot.getShippingAddress().getName());
        else
            orders.setBuyerName(ot.getTransactionArray().getTransaction(0).getBuyer().getUserFirstName()+" "+ot.getTransactionArray().getTransaction(0).getBuyer().getUserLastName());
        orders.setShippedToCountry(ot.getShippingAddress().getCountryName());
        orders.setBuyerUserId(ot.getBuyerUserID());
        if(ot.getShippingAddress().getCityName()!=null)
            orders.setBuyerCity(ot.getShippingAddress().getCityName());
        if(ot.getShippingAddress().getPhone()!=null)
            orders.setBuyerPhoneNumber(ot.getShippingAddress().getPhone());
        orders.setBuyerPostalCode(ot.getShippingAddress().getPostalCode());
        if(ot.getShippingAddress().getStateOrProvince()!=null)
            orders.setBuyerState(ot.getShippingAddress().getStateOrProvince());
        if(ot.getShippingAddress().getStreet1()!=null)
            orders.setBuyerStreet1(ot.getShippingAddress().getStreet1());
        if(ot.getShippingAddress().getStreet2()!=null)
            orders.setBuyerStreet2(ot.getShippingAddress().getStreet2());
        orders.setBuyerCountry(ot.getShippingAddress().getCountryName());
        if(ot.getShippingServiceSelected()!=null) {
            orders.setShippingServiceSelected(ot.getShippingServiceSelected().getShippingService());
            orders.setShippingServiceCost(String.valueOf(ot.getShippingServiceSelected().getShippingServiceCost().getValue()));
            orders.setShippingServiceCostCurrencyId(ot.getShippingServiceSelected().getShippingServiceCost().getCurrencyID().value());
        }
        orders.setSellerUserId(ot.getSellerUserID());
        orders.setSubTotal(String.valueOf(ot.getSubtotal().getValue()));
        orders.setSubTotalCurrencyId(ot.getSubtotal().getCurrencyID().value());
        orders.setSellerEmail(ot.getSellerEmail());
        orders.setTransactionDate(ot.getPaidTime());
        orders.setBuyerEmailId(ot.getTransactionArray().getTransaction(0).getBuyer().getEmail());
        orders.setTransactionArrayLength(ot.getTransactionArray().getTransactionLength());
        orders.setExtendedOrderId(ot.getExtendedOrderID());
        orders.setTotalAmount(String.valueOf(ot.getTotal().getValue()));
        orders.setTotalAmountCurrencyId(ot.getTotal().getCurrencyID().value());
        orders.setAdjustmentAmount(String.valueOf(ot.getAdjustmentAmount().getValue()));
        orders.setAmountPaid(String.valueOf(ot.getAmountPaid().getValue()));
        orders.setTransactionId(ot.getTransactionArray().getTransaction(0).getTransactionID());
        orders.setLastModifiedDate(ot.getCheckoutStatus().getLastModifiedTime());
        orders.setTitle(ot.getTransactionArray().getTransaction(0).getItem().getTitle());
        orders.setSku(ot.getTransactionArray().getTransaction(0).getItem().getSKU());
        orders.setPaymentMethod(ot.getCheckoutStatus().getPaymentMethod().value());
        if(ot.getTransactionArray().getTransaction(0).getShippingDetails()!=null && ot.getTransactionArray().getTransaction(0).getShippingDetails().getShipmentTrackingDetails().length!=0)
            orders.setOrderTrackingId(ot.getTransactionArray().getTransaction(0).getShippingDetails().getShipmentTrackingDetails(0).getShipmentTrackingNumber());
        ol.add(orders);

        if(ot.getTransactionArray().getTransactionLength()!=1)
        for(int i=0;i<ot.getTransactionArray().getTransaction().length;i++){
            Orders chldEo = new Orders();
            chldEo.setOrderRef(convertToInt(ot.getTransactionArray().getTransaction()[i].getShippingDetails().getSellingManagerSalesRecordNumber()+""));
            chldEo.setOwnerIdPk(ownerId);
            chldEo.setExtendedOrderId(orders.getExtendedOrderId());
            chldEo.setAccountId(accountId);
            chldEo.setPaymentMethod(orders.getPaymentMethod());
            if(ot.getCreatedTime()!=null)
            chldEo.setCreatedDate(ot.getCreatedTime().getTime());
            chldEo.setOrderId(ot.getTransactionArray().getTransaction()[i].getOrderLineItemID());
            chldEo.setOrderStatus(ot.getOrderStatus().value());
            chldEo.setSellerEmail(orders.getSellerEmail());
            chldEo.setBuyerEmailId(orders.getBuyerEmailId());
            chldEo.setBuyerName(orders.getBuyerName());
            if(ot.getTransactionArray().getTransaction(i).getShippingDetails()!=null && ot.getTransactionArray().getTransaction(i).getShippingDetails().getShipmentTrackingDetails().length!=0)
                chldEo.setOrderTrackingId(ot.getTransactionArray().getTransaction(i).getShippingDetails().getShipmentTrackingDetails(0).getShipmentTrackingNumber());
            chldEo.setBuyerUserId(orders.getBuyerUserId());
            if(ot.getShippingAddress().getCityName()!=null)
                chldEo.setBuyerCity(ot.getShippingAddress().getCityName());
            if(ot.getShippingAddress().getPhone()!=null)
                chldEo.setBuyerPhoneNumber(ot.getShippingAddress().getPhone());
            chldEo.setBuyerPostalCode(ot.getShippingAddress().getPostalCode());
            if(ot.getShippingAddress().getStateOrProvince()!=null)
                chldEo.setBuyerState(ot.getShippingAddress().getStateOrProvince());
            if(ot.getShippingAddress().getStreet1()!=null)
                chldEo.setBuyerStreet1(ot.getShippingAddress().getStreet1());
            if(ot.getShippingAddress().getStreet2()!=null)
                chldEo.setBuyerStreet2(ot.getShippingAddress().getStreet2());
            if(ot.getShippingServiceSelected()!=null) {
                chldEo.setShippingServiceSelected(ot.getShippingServiceSelected().getShippingService());
                chldEo.setShippingServiceCost(String.valueOf(ot.getShippingServiceSelected().getShippingServiceCost().getValue()));
                chldEo.setShippingServiceCostCurrencyId(ot.getShippingServiceSelected().getShippingServiceCost().getCurrencyID().value());
            }
            if(ot.getShippedTime()!=null)
                chldEo.setShippedDate(ot.getShippedTime().getTime());
            chldEo.setBuyerCountry(ot.getShippingAddress().getCountryName());
            chldEo.setSku(ot.getTransactionArray().getTransaction()[i].getItem().getSKU());
            if(ot.getPaidTime()!=null)
                chldEo.setPaidDate(ot.getPaidTime().getTime());
            chldEo.setAmountPaid(String.valueOf(ot.getAmountPaid().getValue()));
            chldEo.setTotalAmount(String.valueOf(ot.getTransactionArray().getTransaction()[i].getTransactionPrice().getValue()));
            chldEo.setTotalAmountCurrencyId(String.valueOf(ot.getTransactionArray().getTransaction()[i].getTransactionPrice().getCurrencyID()));
            chldEo.setTransactionId(ot.getTransactionArray().getTransaction()[i].getTransactionID());
            chldEo.setTitle(ot.getTransactionArray().getTransaction()[i].getItem().getTitle());
            if(ot.getTransactionArray().getTransaction()[i].getItem().getConditionDisplayName()!=null)
            chldEo.setConditionDisplayName(ot.getTransactionArray().getTransaction()[i].getItem().getConditionDisplayName());
            if(ot.getTransactionArray().getTransaction()[i].getVariation()!=null)
                chldEo.setItemImage(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationViewItemURL());
            chldEo.setSku(ot.getTransactionArray().getTransaction()[i].getItem().getSKU());
            chldEo.setItemId(ot.getTransactionArray().getTransaction()[i].getItem().getItemID());
            if(ot.getTransactionArray().getTransaction()[i].getVariation()!=null) {
                chldEo.setOrderVariationName(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationSpecifics().getNameValueList(0).getName());
                chldEo.setOrderVariationValue(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationSpecifics().getNameValueList(0).getValue(0));
            }
            chldEo.setQuantityPurchased(ot.getTransactionArray().getTransaction()[i].getQuantityPurchased()+"");
            ol.add(chldEo);
        }

        return ol;
    }

    public static int convertToInt(String s){
        try{
            return Integer.parseInt(s);
        }catch(Exception e){
            return 0;
        }
    }
}
