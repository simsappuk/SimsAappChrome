package com.ebay.load.seller.service;

import com.ebay.load.seller.dto.EbayOrders;
import com.ebay.load.seller.model.OrderLog;
import com.ebay.load.seller.repository.OrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLogService {

    @Autowired
    OrderLogRepository orderLogRepository;

    public void OrderLogDetails(List<EbayOrders> e){
        OrderLog ol=new OrderLog();
        for(int i=0;i<e.size();i++) {
            ol.setItemId(e.get(i).getItemId());
            ol.setSku(e.get(i).getSku());
            ol.setSellingRecordNumber(e.get(i).getSellingRecordNumber());
            ol.setTitle(e.get(i).getTitle());
            ol.setQuantityPurchased(e.get(i).getQuantityPurchased());
            ol.setOrderStatus(e.get(i).getOrderStatus());
            ol.setCancelStatus(e.get(i).getCancelStatus());
            ol.setOrderId(e.get(i).getOrderId());
            ol.setOrderStatus(e.get(i).getOrderStatus());
            ol.setSellerEmail(e.get(i).getSellerEmail());
            ol.setBuyerEmailId(e.get(i).getBuyerEmailId());
            ol.setBuyerName(e.get(i).getBuyerName());
            ol.setShippedToCountry(e.get(i).getShippedToCountry());
            ol.setBuyerUserId(e.get(i).getBuyerUserId());
            ol.setSellerUserId(e.get(i).getSellerUserId());
            ol.setCancelStatus(e.get(i).getCancelStatus());
            ol.setExtendedOrderId(e.get(i).getExtendedOrderId());
            ol.setAdjustmentAmount(e.get(i).getAdjustmentAmount());
            ol.setAmountPaid(e.get(i).getAmountPaid());
            ol.setTransactionId(e.get(i).getTransactionId());
            ol.setLastModifiedDate(e.get(i).getLastModifiedDate());
            ol.setTitle(e.get(i).getTitle());
            ol.setTransactionDate(e.get(i).getTransactionDate());
            if(e.get(i).getSku()!=null)
                ol.setSku(e.get(i).getSku());
            ol.setConditionDisplayName(e.get(i).getConditionDisplayName());
            if(e.get(i).getQuantityPurchased()!=null)
                ol.setQuantityPurchased(e.get(i).getQuantityPurchased());
            if(e.get(i).getItem()!=null && e.get(i).getItem().get(0).getVatPercent()!=null)
                ol.setVatPercent(e.get(i).getItem().get(0).getVatPercent());
            ol.setItemId(e.get(i).getItemId());
            ol.setCheckOutStatus(e.get(i).getCheckOutStatus());
            ol.setPaymentMethod(e.get(i).getPaymentMethod());
            ol.setPaymentInstrument(e.get(i).getPaymentInstrument());
            ol.setShippingService(e.get(i).getShippingService());
            ol.setAddressCity(e.get(i).getAddressCity());
            ol.setAddressId(e.get(i).getAddressId());
            ol.setAddressPhone(e.get(i).getAddressPhone());
            ol.setAddressPostalCode(e.get(i).getAddressPostalCode());
            ol.setAddressState(e.get(i).getAddressState());
            ol.setAddressStreet1(e.get(i).getAddressStreet1());
            ol.setAddressStreet2(e.get(i).getAddressStreet2());
            ol.setPaidTime(e.get(i).getPaidTime());
            ol.setCreatedTime(e.get(i).getCreatedTime());
            if(e.get(i).getItem().size()!=0 && e.get(i).getItem().get(0).getShippingServiceCost()!=null)
            ol.setShippingServiceCost(e.get(i).getItem().get(0).getShippingServiceCost().toString());
            OrderLog db=orderLogRepository.findBySellingRecordNumber(e.get(i).getSellingRecordNumber());
            if(e.get(i).getCheckOutStatus()=="Complete")
                ol.setOrderState("COMPLETED");
            if(db==null)
            orderLogRepository.save(ol);
        }
    }
}
