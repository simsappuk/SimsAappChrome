package com.ebay.load.seller.service;

import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.*;
import com.ebay.load.seller.repository.SchedulerItemRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SchedulerItemService {

    @Autowired
    StockService stockService;

    @Autowired
    OrdersServiceImpl ordersServiceImpl;

    @Autowired
    SchedulerItemRepository schedulerItemRepository;

    public ResponseEntity<List<SchedulerItemService>> getSchedulerItem(List<Accounts> s,Orders e,Scheduler s2,List<EbayListing> el){
         SchedulerItem si= new SchedulerItem();
        for (int i = 0; i < s.size(); i++) {
            Accounts s1 = s.get(i);
                String orderSku = e.getSku();
                if (orderSku != null) {
                    Stock stockCheck = stockService.getStock(orderSku,s1.getId());
                    Orders order=ordersServiceImpl.getOrders(orderSku);
                    for(int j=0;j<el.size();j++){
                        if(el.get(j).getSku()==orderSku){
                            si.setWatchCount(el.get(j).getWatchCount());
                            si.setQuantityAvailable(Integer.valueOf(el.get(j).getQuantity()));
                        }
                    }
                    if(order!=null)
                    si.setPurchaseOrderId(order.getId());
                    si.setItemId(stockCheck.getItemId().get(0));
                    si.setSchedulerId(s2.getId());
                    si.setOrderId(e.getOrderId());
                    si.setStockId(stockCheck.getId());
                    si.setItemName(stockCheck.getTitle());
                    si.setAccountId(s1.getId());
                    si.setBuyerEmailId(e.getBuyerEmailId());
                    si.setBuyerName(e.getBuyerName());
                    si.setSellingRecordNumber(e.getSellingRecordNumber());
                    si.setShippedToCountry(e.getShippedToCountry());
                    schedulerItemRepository.save(si);
                }
        }
        return null;
    }
}