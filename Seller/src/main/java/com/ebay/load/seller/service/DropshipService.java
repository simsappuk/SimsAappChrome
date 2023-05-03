package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.ActivityLog;
import com.ebay.load.seller.model.Dropship;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.DropshipRepository;
import com.ebay.load.seller.repository.OrdersRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.soap.eBLBaseComponents.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DropshipService {

    @Autowired
    DropshipRepository dropshipRepository;

    @Autowired
    EbayService ebayService;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ActivityLogService activityLogService;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    ResetService resetService;


    public ResponseEntity<Dropship> getContent(String accountId) {
        List<Dropship> d=dropshipRepository.findByOwnerIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
        return new ResponseEntity<List<Dropship>>().withResults(d);
    }

    public ResponseEntity<Dropship> getItemData(String id) {
          Dropship d=dropshipRepository.findOneById(id);
        return new ResponseEntity<Dropship>().withResults(d);
    }


    public ResponseEntity<Dropship> save(Dropship dropship,String accountId) {
        Dropship exist=dropshipRepository.findByLink(dropship.getLink());
        if(exist==null) {
                ResponseEntity<EbayListing> result = ebayService.reviseExistingListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(), dropship.getItemId(), dropship.getEbayPrice(), "5");
                if(result.isErrors()){
                    return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
                }
                dropship.setLastEffectiveDate(new Date());
                dropship.setAccountId(accountId);
                dropship.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
                Dropship save = dropshipRepository.save(dropship);
                resetService.pauseCompete(dropship.getItemId());
                return new ResponseEntity<>().withResults(save);
        }
        else
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("Dropship Item Link Exist Already"));
    }

    public ResponseEntity<String> newSellingPrice(String condition,String ebayPaypalPercent,String transFee,String profit,String sellerPrice,String vat){
        if(ebayPaypalPercent!=null){
            if(ebayPaypalPercent.equals("undefined")||ebayPaypalPercent.equals("")||ebayPaypalPercent.equals("null"))
            ebayPaypalPercent="12";}
        else
            ebayPaypalPercent="12";
        if(profit!=null) {
            if (profit.equals("undefined") || profit.equals("")||profit.equals("null"))
                profit = "10";
        }
        else
            profit="10";
        if(condition.equals("New")) {
            Double buyingPrice = Double.parseDouble(sellerPrice) * 5 / 6;
            Double upperValue = 0.20+ buyingPrice;
            Double numerator = 6 * upperValue;

            Double ebayPayPalPercent = 6 * ((Double.parseDouble(ebayPaypalPercent))/100);
            Double profitPercent = 6 * ((Double.parseDouble(profit))/100);
            Double denominator = 5 - ebayPayPalPercent - profitPercent;
            Double value = numerator / denominator;
            Double roundOff=Math.round(value * 100D) / 100D;
            String[] split=roundOff.toString().split("\\.");
            if(Integer.parseInt(split[1])>49) {
                Double result = Double.parseDouble(split[0]) + 0.99;
                return new ResponseEntity<String>().withResults(String.valueOf(result));
            }
            else {
                Double result = Double.parseDouble(split[0]) + 0.49;
                return new ResponseEntity<String>().withResults(String.valueOf(result));
            }
        }
        else{
          Double finalBp=Double.parseDouble(sellerPrice)*0.83;
          Double numerator=finalBp+0.20;
          Double denominator=0.83-(Double.parseDouble(ebayPaypalPercent)/100)-(Double.parseDouble(profit)/100);
          Double value = numerator / denominator;
            Double roundOff=Math.round(value * 100D) / 100D;
            String[] split=roundOff.toString().split("\\.");
            if(Integer.parseInt(split[1])>49) {
                Double result = Double.parseDouble(split[0]) + 0.99;
                return new ResponseEntity<String>().withResults(String.valueOf(result));
            }
            else {
                Double result = Double.parseDouble(split[0]) + 0.49;
                return new ResponseEntity<String>().withResults(String.valueOf(result));
            }

        }
    }

    public void updateEbaySellingPrice(String accountId,String ownerId){
        List<Dropship> list=dropshipRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        Accounts s =accountsRepository.findByAccountsId(accountId);String amount="";Integer quantityAvaialble = null;
        ResponseEntity<String> newPrice;
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                Dropship dropship = list.get(i);
                if (!dropship.isPause()) {
                    if (dropship.getSource().equals("ebay")) {
                        ItemType item = ebayService.geItem(dropship.getLink(), s);
                        quantityAvaialble = item.getQuantity() - item.getSellingStatus().getQuantitySold();
                        if (quantityAvaialble == 0)
                            amount = dropship.getOutOfStockPrice();
                        else
                            amount = String.valueOf(item.getStartPrice().getValue());
                    } else
                        amount = HttpClient.sendGet(dropship.getLink());
                    if (!amount.equals(dropship.getSellerPrice())) {
                        if (quantityAvaialble != 0)
                            newPrice = newSellingPrice(dropship.getCondition(), dropship.getEbayPaypalPercent(), "0.2", dropship.getProfitPercent(), amount, dropship.getVatPercent());
                        else
                            newPrice = new ResponseEntity<>().withResults(dropship.getOutOfStockPrice());
                        if (dropship.getItemId() != null && dropship.getEbayPrice() != null)
                            try {
                                ResponseEntity<EbayListing> result = ebayService.reviseExistingListing(accountId, ownerId, dropship.getItemId(), newPrice.getResults(), "5");
                                if (result.getResults().getDescription().equals("UPDATE SUCCESS!")) {
                                    dropship.setLastEffectiveDate(new Date());
                                    dropship.setSellerPrice(amount);
                                    dropship.setEbayPrice(newPrice.getResults());
                                    ResponseEntity<String> newVat = calculateVat(dropship.getCondition(), dropship.getSellerPrice(), dropship.getEbayPrice());
                                    dropship.setVatPercent(newVat.getResults());
                                    dropship.setAccountId(accountId);
                                    dropship.setOwnerId(ownerId);
                                    dropshipRepository.saveAndFlush(dropship);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                }
            }
        }
    }

    public ResponseEntity<Dropship> delete(Dropship dropship) {
        dropshipRepository.delete(dropship);
        return null;
    }

    public ResponseEntity<Dropship> update(Dropship dropship,String accountId) {
        ResponseEntity<EbayListing> result=ebayService.reviseExistingListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(),dropship.getItemId(), dropship.getEbayPrice(), "5");
        if(result.isErrors())
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
        dropship.setLastEffectiveDate(new Date());
        dropship.setAccountId(accountId);
        dropship.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
        resetService.pauseCompete(dropship.getItemId());
        dropshipRepository.saveAndFlush(dropship);
        return null;
    }

    public ResponseEntity<String> calculateVat(String condition, String buyingPrice, String sellingPrice) {
        if(condition.equals("New")){
            Double value= (Double.valueOf(sellingPrice)-Double.valueOf(buyingPrice.substring(1)))/6;
            Double roundOff=Math.round(value * 100D) / 100D;
            String vat= String.valueOf(roundOff);
            return new ResponseEntity<String>().withResults(vat);
        }
        else{
            Double value= (Double.valueOf(sellingPrice)-Double.valueOf(buyingPrice.substring(1)))*0.1666;
            Double roundOff=Math.round(value * 100D) / 100D;
            String vat= String.valueOf(roundOff);
            return new ResponseEntity<String>().withResults(vat);
        }

    }

    public ResponseEntity<List<Orders>> findAllDropshipOrders(String ownerId, String accountId, Pageable p) {
        List<Orders> dropship=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndOrderDropshipStatusNotNull(ownerId, accountId, "EBAY", null, null, p.getSort());
        return ebayService.paginate(dropship,p);
    }

    public ResponseEntity<List<Orders>> findAllDropshipOrdersByDate(String ownerId, String accountId,Date date,Date nextDate, Pageable p) {
        List<Orders> dropship=ordersRepository.findByOwnerIdPkAndAccountIdAndOrderStatusAndShippedDate(ownerId, accountId,"DROPSHIPPED", date,nextDate);
        return ebayService.paginate(dropship,p);
    }

    public ResponseEntity<List<Orders>> MarkOrderAsDropship(List<String> id, PageRequest pageRequest) {
        for(int i=0;i<id.size();i++) {
            Orders order = ordersRepository.findOneById(id.get(i));
            if(order!=null && order.getPaidDate()!=null){
                order.setOrderDropshipStatus("DROPSHIPPED");
                order.setOrderStatus("DROPSHIPPED");
                order.setShippedDate(new Date());
                ordersRepository.save(order);
            }
            List<Orders> o=ordersRepository.findByOwnerIdPkAndOrderTypeAndPOrderRefAndPaidDateNotNull(order.getOwnerIdPk(),"EBAY",order.getOrderRef());
            for(int j=0;j<o.size();j++){
                if(o.get(j).getOrderDropshipStatus()==null && o.get(j).getPaidDate()!=null) {
                    o.get(j).setOrderDropshipStatus("DROPSHIPPED");
                    o.get(j).setOrderStatus("DROPSHIPPED");
                    o.get(j).setShippedDate(new Date());
                   // ebayService.updateStockQuantity(o.get(j));
                }
                ordersRepository.save(o.get(j));
            }

        }
        return null;
    }

    public ResponseEntity<List<Orders>> undoDropshippedItem(List<String> id, PageRequest pageRequest) {
        for(int i=0;i<id.size();i++) {
            Orders order = ordersRepository.findOneById(id.get(i));
            if(order!=null && order.getPaidDate()!=null){
                order.setOrderDropshipStatus(null);
                order.setOrderStatus("AwaitingDispatch");
                order.setShippedDate(null);
                ordersRepository.save(order);
            }
            List<Orders> o=ordersRepository.findByOwnerIdPkAndOrderTypeAndPOrderRefAndPaidDateNotNull(order.getOwnerIdPk(),"EBAY",order.getOrderRef());
            for(int j=0;j<o.size();j++){
                if(o.get(j).getOrderDropshipStatus()==null && o.get(j).getPaidDate()!=null) {
                    o.get(j).setOrderDropshipStatus(null);
                    order.setOrderStatus("Completed");
                    o.get(j).setShippedDate(null);
                    // ebayService.updateStockQuantity(o.get(j));
                }
                ordersRepository.save(o.get(j));
            }

        }
        return null;
    }

    public ResponseEntity<List<Orders>> dropshipPurchasedOrder(String id,String logId) {
        try {
            Orders order = ordersRepository.findOneById(id);
            if (order != null && order.getPaidDate() != null) {
                order.setOrderDropshipStatus("DROPSHIPPED");
                order.setOrderStatus("DROPSHIPPED");
                order.setShippedDate(new Date());
                ordersRepository.save(order);
            }
            if (order.getpOrderRef() != null)
                checkMainOrderToBeDropshippedOrNot(order);
            if (logId != null) {
                activityLogService.saveLog(logId, order);
            }
        }catch (Exception e){
            ebayService.send("simsapp2020@gmail.com","8333830600","shaiksazidh@gmail.com","Error in ActivityLog",e.getMessage());
        }
        return null;
    }
    public void checkMainOrderToBeDropshippedOrNot(Orders order){
        List<Orders> check=ordersRepository.findByPOrderRef(order.getpOrderRef());
        ArrayList<String> status=new ArrayList<>();
        for(int i=0;i<check.size();i++){
            status.add(check.get(i).getOrderStatus());
        }
        if(getResult(status)!=null){
            Orders entireOrder=ordersRepository.findByOrderRef(order.getpOrderRef());
            dropshipPurchasedOrder(entireOrder.getId(),null);
        }
    }
    public String getResult(List<String> status){
        for(int j=0;j<status.size();j++) {
            if(!status.get(j).equals("DROPSHIPPED"))
                return null;
        }
        return "Dropship-Entire-Order";
    }
}
