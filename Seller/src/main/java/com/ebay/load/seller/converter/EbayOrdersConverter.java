package com.ebay.load.seller.converter;

import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Orders;

import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.service.EbayService;
import com.ebay.soap.eBLBaseComponents.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class EbayOrdersConverter {

    @Autowired
    EbayService ebayService;

    @Autowired
    AccountsRepository accountsRepository;

  public List<Orders> convertToOrders(OrderType[] ot,String accountId,String ownerId) {
      List<Orders> list = new <Orders>ArrayList();
      for (int i = 0; i < ot.length; i++) list.addAll(convertOrderTypeToOrders(ot[i],accountId,ownerId));
      return list;
  }


  public List<Orders> convertOrderTypeToOrders(OrderType ot,String accountId,String ownerId) {
        Accounts s=accountsRepository.findByAccountsId(accountId);
        List<Orders> ol=new ArrayList<Orders>();
        Integer Quantity=0;
        Orders eo = new Orders();
        eo.setAccountId(accountId);
        eo.setOrderId(ot.getOrderID());
        eo.setPrintDate(ot.getCreatedTime().getTime());
        for(int i=0;i<ot.getTransactionArray().getTransactionLength();i++) {
            Quantity+=ot.getTransactionArray().getTransaction()[i].getQuantityPurchased();
            eo.setQuantityPurchased(Quantity.toString());
        }
        eo.setOwnerIdPk(ownerId);
        eo.setSubTotal(String.valueOf(ot.getSubtotal().getValue()));
        eo.setSubTotalCurrencyId(ot.getSubtotal().getCurrencyID().value());
        eo.setOrderStatus(ot.getOrderStatus().value());
        if(ot.getPaidTime()!=null)
            eo.setPaidDate(ot.getPaidTime().getTime());
        if(ot.getBuyerCheckoutMessage()!=null)
            eo.setBuyerCheckoutMessage(ot.getBuyerCheckoutMessage());
        eo.setSellerEmail(ot.getSellerEmail());
        eo.setOrderRef(convertToInt(ot.getShippingDetails().getSellingManagerSalesRecordNumber()+""));
        eo.setBuyerEmailId(ot.getTransactionArray().getTransaction(0).getBuyer().getEmail());
        eo.setSellingRecordNumber(String.valueOf(ot.getShippingDetails().getSellingManagerSalesRecordNumber()));
        if(!ot.getShippingAddress().getName().equals(""))
           eo.setBuyerName(ot.getShippingAddress().getName());
        else
            eo.setBuyerName(ot.getTransactionArray().getTransaction(0).getBuyer().getUserFirstName()+" "+ot.getTransactionArray().getTransaction(0).getBuyer().getUserLastName());
        eo.setShippedToCountry(ot.getShippingAddress().getCountryName());
        eo.setBuyerUserId(ot.getBuyerUserID());
        if(ot.getShippingAddress().getCityName()!=null)
        eo.setBuyerCity(ot.getShippingAddress().getCityName());
        if(ot.getShippingAddress().getPhone()!=null)
        eo.setBuyerPhoneNumber(ot.getShippingAddress().getPhone());
        eo.setBuyerPostalCode(ot.getShippingAddress().getPostalCode());
        if(ot.getShippingAddress().getStateOrProvince()!=null)
        eo.setBuyerState(ot.getShippingAddress().getStateOrProvince());
        if(ot.getShippingAddress().getStreet1()!=null)
        eo.setBuyerStreet1(ot.getShippingAddress().getStreet1());
        if(ot.getShippingAddress().getStreet2()!=null)
        eo.setBuyerStreet2(ot.getShippingAddress().getStreet2());
        eo.setBuyerCountry(ot.getShippingAddress().getCountryName());
        if(ot.getShippingServiceSelected()!=null) {
            eo.setShippingServiceSelected(ot.getShippingServiceSelected().getShippingService());
            if (ot.getShippingServiceSelected().getShippingServiceCost() != null) {
                eo.setShippingServiceCost(String.valueOf(ot.getShippingServiceSelected().getShippingServiceCost().getValue()));
                eo.setShippingServiceCostCurrencyId(ot.getShippingServiceSelected().getShippingServiceCost().getCurrencyID().value());
            }
        }
        try{
            if(ot.getTransactionArray().getTransaction(0).getEBayCollectAndRemitTaxes()!=null && ot.getTransactionArray().getTransaction(0).getEBayCollectAndRemitTaxes().getAny()!=null)
                eo.setVatIossNumber(ebayService.getVatIOSSNumber(ot.getExtendedOrderID(),s));
        }catch (Exception e){}
        eo.setSellerUserId(ot.getSellerUserID());
        eo.setExtendedOrderId(ot.getExtendedOrderID());
        eo.setTotalAmount(String.valueOf(ot.getTotal().getValue()));
        eo.setTotalAmountCurrencyId(ot.getTotal().getCurrencyID().value());
        eo.setAdjustmentAmount(String.valueOf(ot.getAdjustmentAmount().getValue()));
        eo.setAmountPaid(String.valueOf(ot.getAmountPaid().getValue()));
        eo.setTransactionId(ot.getTransactionArray().getTransaction(0).getTransactionID());
        //eo.setLastModifiedDate(ot.getCheckoutStatus().getLastModifiedTime());
        eo.setTitle(ot.getTransactionArray().getTransaction(0).getItem().getTitle());
        if(ot.getTransactionArray().getTransaction(0).getItem().getSKU()!=null)
            eo.setSku(ot.getTransactionArray().getTransaction(0).getItem().getSKU());
        else if(ot.getTransactionArray().getTransaction(0).getVariation()!=null && ot.getTransactionArray().getTransaction(0).getVariation().getSKU()!=null)
            eo.setSku(ot.getTransactionArray().getTransaction(0).getVariation().getSKU());
       /* if(ot.getPaidTime()!=null)
        eo.setTransactionDate(ot.getPaidTime());
        if(eo.getTransactionDate()!=null)
        eo.setTransDateRef(removeTime(eo.getTransactionDate().getTime()));*/
        eo.setOwnerIdPk(ownerId);
        eo.setCancelStatus(ot.getCancelStatus().value());
        if(ot.getShippedTime()!=null)
        eo.setShippedDate(ot.getShippedTime().getTime());
        if(eo.getCancelStatus().equals("CancelClosedWithRefund")){
            eo.setStatus("CancelClosedWithRefund");
            eo.setOrderStatus("Cancelled");
        }
        else if(eo.getPaidDate()!=null && eo.getShippedDate()!=null && !eo.getOrderStatus().matches("Cancelled|CancelPending"))
          eo.setOrderStatus("Dispatched");
        else if(eo.getOrderStatus().equals("Cancelled"))
            eo.setStatus("CANCELLED");
        else if(eo.getCancelStatus().equals("CancelPending")) {
            eo.setStatus("CANCELLED");
            eo.setOrderStatus("Cancelled");
        }
        else if(eo.getPaidDate()==null && !eo.getOrderStatus().matches("Cancelled|CancelPending"))
            eo.setOrderStatus("AwaitingPayment");
        else if(eo.getPaidDate()!=null && eo.getShippedDate()==null && !eo.getOrderStatus().matches("Cancelled|CancelPending"))
            eo.setOrderStatus("AwaitingDispatch");
        else if(eo.getOrderStatus().matches("Cancelled|CancelPending")||eo.getCancelStatus().matches("CancelPending|CancelClosedWithRefund|Cancelled"))
            eo.setOrderStatus("Cancelled");
        if(ot.getCheckoutStatus().getStatus().value().equals("Complete"))
            eo.setStatus("COMPLETE");
        else if(ot.getCheckoutStatus().getStatus().value().equals("Incomplete"))
            eo.setStatus("INCOMPLETE");
        eo.setOrderType("EBAY");
        eo.setTransactionArrayLength(ot.getTransactionArray().getTransactionLength());
        if(ot.getTransactionArray().getTransaction(0).getShippingDetails().getShipmentTrackingDetails().length!=0)
            eo.setOrderTrackingId(ot.getTransactionArray().getTransaction(0).getShippingDetails().getShipmentTrackingDetails(0).getShipmentTrackingNumber());
        ol.add(eo);
        int ordQuantity=0;
        for(int i=0;i<ot.getTransactionArray().getTransaction().length;i++){
            Orders chldEo = new Orders();
            if(eo.getTransactionArrayLength()!=null)
            chldEo.setTransactionArrayLength(eo.getTransactionArrayLength());
            chldEo.setOrderRef(convertToInt(ot.getTransactionArray().getTransaction()[i].getShippingDetails().getSellingManagerSalesRecordNumber()+""));
            chldEo.setpOrderRef(eo.getOrderRef());
            chldEo.setOwnerIdPk(ownerId);
            ordQuantity+=convertToInt(chldEo.getQuantity());
            chldEo.setExtendedOrderId(eo.getExtendedOrderId());
            chldEo.setAccountId(accountId);
            if(eo.getVatIossNumber()!=null)
                chldEo.setVatIossNumber(eo.getVatIossNumber());
            chldEo.setCancelStatus(eo.getCancelStatus());
            chldEo.setOrderId(ot.getTransactionArray().getTransaction()[i].getOrderLineItemID());
            chldEo.setOrderStatus(ot.getOrderStatus().value());
            chldEo.setSellerEmail(eo.getSellerEmail());
            chldEo.setBuyerEmailId(eo.getBuyerEmailId());
            chldEo.setBuyerName(eo.getBuyerName());
            chldEo.setBuyerUserId(eo.getBuyerUserId());
            if(ot.getBuyerCheckoutMessage()!=null)
                chldEo.setBuyerCheckoutMessage(ot.getBuyerCheckoutMessage());
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
                if (ot.getShippingServiceSelected().getShippingServiceCost() != null) {
                    chldEo.setShippingServiceCost(String.valueOf(ot.getShippingServiceSelected().getShippingServiceCost().getValue()));
                    chldEo.setShippingServiceCostCurrencyId(ot.getShippingServiceSelected().getShippingServiceCost().getCurrencyID().value());
                }
            }
            if(ot.getShippedTime()!=null)
                chldEo.setShippedDate(ot.getShippedTime().getTime());
            chldEo.setBuyerCountry(ot.getShippingAddress().getCountryName());
            if(ot.getTransactionArray().getTransaction()[i].getItem().getSKU()!=null)
                chldEo.setSku(ot.getTransactionArray().getTransaction()[i].getItem().getSKU());
            else if(ot.getTransactionArray().getTransaction()[i].getVariation()!=null && ot.getTransactionArray().getTransaction()[i].getVariation().getSKU()!=null)
                chldEo.setSku(ot.getTransactionArray().getTransaction()[i].getVariation().getSKU());
            if(ot.getPaidTime()!=null)
            chldEo.setPaidDate(ot.getPaidTime().getTime());
            chldEo.setAmountPaid(String.valueOf(ot.getAmountPaid().getValue()));
            chldEo.setTotalAmount(String.valueOf(ot.getTransactionArray().getTransaction()[i].getTransactionPrice().getValue()));
            chldEo.setTotalAmountCurrencyId(String.valueOf(ot.getTransactionArray().getTransaction()[i].getTransactionPrice().getCurrencyID()));
            chldEo.setTransactionId(ot.getTransactionArray().getTransaction()[i].getTransactionID());
            chldEo.setTitle(ot.getTransactionArray().getTransaction()[i].getItem().getTitle());
            if(ot.getTransactionArray().getTransaction()[i].getVariation()!=null)
            chldEo.setItemImage(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationViewItemURL());
            chldEo.setQuantity(ot.getTransactionArray().getTransaction()[i].getQuantityPurchased()+"");
            chldEo.setItemId(ot.getTransactionArray().getTransaction()[i].getItem().getItemID());
            if(chldEo.getTitle().equals(eo.getTitle())) {
                eo.setItemId(chldEo.getItemId());
                eo.setQuantity(chldEo.getQuantity());
            }
            if(ot.getTransactionArray().getTransaction()[i].getVariation()!=null) {
                chldEo.setOrderSpecifics(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationSpecifics().getNameValueList());
                chldEo.setOrderVariationName(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationSpecifics().getNameValueList(0).getName());
                chldEo.setOrderVariationValue(ot.getTransactionArray().getTransaction()[i].getVariation().getVariationSpecifics().getNameValueList(0).getValue(0));
                if(eo.getItemId().equals("363011102296")){
                    eo.setOrderVariationName(chldEo.getOrderVariationName());
                    eo.setOrderVariationValue(chldEo.getOrderVariationValue());
                }
            }
            if(chldEo.getItemId().matches("111780112925|111934357486|111780055333|111811766516|112093376505|113595127047|362703651114|362498421957|362912470219"))
            {
                eo.setStatusId("Hide");
                chldEo.setStatusId("Hide");
            }
            if(chldEo.getItemId().matches("362992957721")){
                Integer total=0;NameValueListType nameValueListType=new NameValueListType();
                List<NameValueListType> list=new ArrayList<NameValueListType>();
                if(chldEo.getOrderSpecifics()!=null){
                    try {

                        for(int k=0;k<ot.getTransactionArray().getTransactionLength();k++){
                            for(int m=0;m<ot.getTransactionArray().getTransaction()[k].getVariation().getVariationSpecifics().getNameValueList().length;m++){
                                if(ot.getTransactionArray().getTransaction()[k].getVariation().getVariationSpecifics().getNameValueList()[m].getName().equals("Roll Qty")){
                                    total+=Integer.parseInt(ot.getTransactionArray().getTransaction()[k].getVariation().getVariationSpecifics().getNameValueList()[m].getValue(0).replaceAll("[^0-9\\\\s]",""))*ot.getTransactionArray().getTransaction()[k].getQuantityPurchased();
                            }
                        }
                    }

                    nameValueListType.setName("Variation Qty");
                    nameValueListType.setValue(new String[]{ot.getTransactionArray().getTransaction()[i].getQuantityPurchased().toString()});
                    list.add(nameValueListType);
                    for(int p=0;p<chldEo.getOrderSpecifics().length;p++)
                        list.add(chldEo.getOrderSpecifics()[p]);
                    chldEo.setTotalOrderSpecifics(list);
                    }catch (Exception e){e.printStackTrace();}
                }
                eo.setQuantityPurchased(String.valueOf(total));
            }
            chldEo.setOrderType("EBAY");
            ol.add(chldEo);
        }
        return ol;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static int convertToInt(String s){
        try{
            return Integer.parseInt(s);
        }catch(Exception e){
            return 0;
        }
    }























}
