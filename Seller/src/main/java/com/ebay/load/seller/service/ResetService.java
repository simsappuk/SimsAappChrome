package com.ebay.load.seller.service;


import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.*;
import com.ebay.load.seller.repository.*;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.soap.eBLBaseComponents.VariationType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ResetService {
    
    @Autowired
    ResetRepository resetRepository;

    @Autowired
    EbayService ebayService;

    @Autowired
    CompeteRepository competeRepository;

    @Autowired
    DropshipRepository dropshipRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    AccountsRepository accountsRepository;

    public ResponseEntity<Reset> getContent(String accountId) {
        List<Reset> list=resetRepository.findByOwnerIdAndAccountIdAndListingHistoryNull(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
        return new ResponseEntity<List<Reset>>().withResults(list);
    }

    public ResponseEntity<Reset> save(Reset reset, String accountId,String ownerId) {
        List<Reset> list=resetRepository.findByOwnerIdAndAccountIdAndEbayItemIdAndListingHistory(ownerId,accountId,reset.getEbayItemId(),null);
        pauseDropship(reset.getEbayItemId());
        pauseCompete(reset.getEbayItemId());
        if(list.size()==0 || (reset.getVariantSku()!=null && !reset.getVariantSku().equals(""))) {
            reset.setLastEffectiveDate(new Date());
            try {
                if (reset.getListingType().equals("Standard"))
                    ebayService.reviseExistingListing(accountId, ownerId, reset.getEbayItemId(), reset.getEbayPrice(), reset.getExistedQuantity().toString());
                else
                    ebayService.reviseVariantListing(accountId, ownerId, reset.getEbayItemId(), reset.getEbayPrice(), reset.getExistedQuantity().toString(), reset.getFirstVariationName(), reset.getFirstVariationValue(), reset.getSecondVariationName(), reset.getSecondVariationValue(), reset.getVariantSku());
            } catch (Exception e) {
                return new ResponseEntity<Reset>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
            }
            Reset save = resetRepository.save(reset);
            return new ResponseEntity<Reset>().withResults(save);
        }
        else
            return new ResponseEntity<Reset>().withErrors(true).withMessages(new Message().withMessageText("ITEM ID ALREADY EXIST"));
    }

    public ResponseEntity<Reset> getPriceAndQuantity(String itemId,String accountId,String ownerId) {
        Accounts accounts=accountsRepository.findByAccountsId(accountId);
        ResponseEntity <EbayListing> listingDetails=ebayService.findStockById(ownerId,accountId,itemId);
        Reset reset=new Reset();
        if(listingDetails.getResults().getVariantItemDetails()==null)
            reset.setListingType("Standard");
        else
            reset.setListingType("UniqueVariation");
        reset.setVariationsTypes(listingDetails.getResults().getVariationsTypes());
        if(listingDetails.getResults().getTotalQuantitySold()!=null)
            reset.setTotalQuantitySold(listingDetails.getResults().getTotalQuantitySold());
        reset.setEbayItemId(itemId);
        reset.setUpdated(false);
        reset.setSoldFromReset(0);
        try {
            HashMap updateAdInfo=getAdRate(itemId, accounts.getOauthApiToken());
            reset.setBidPercentage(updateAdInfo.get("adRate").toString());
            reset.setAdId(updateAdInfo.get("adId").toString());
            reset.setCampaignId(updateAdInfo.get("campaignId").toString());
        }catch (Exception e){reset.setBidPercentage("Token Expired");}
        reset.setTitle(listingDetails.getResults().getTitle());
        reset.setAccountId(accountId);reset.setOwnerId(ownerId);
        reset.setExistedQuantity(listingDetails.getResults().getQuantityAvailable());
        reset.setAfterPrice(listingDetails.getResults().getStartPriceValue());
        return new ResponseEntity<Reset>().withResults(reset);
    }

    public void reviseListingPrice(String accountId, String ownerId) {
        List<Reset> list=resetRepository.findByOwnerIdAndAccountIdAndListingHistoryNull(ownerId,accountId);
        for(int i=0;i<list.size();i++){
            try {
                if(!list.get(i).getUpdated()) {
                    ResponseEntity<EbayListing> ebayListing = ebayService.findStockById(ownerId, accountId, list.get(i).getEbayItemId());
                    if (list.get(i).getListingType().equals("Standard"))
                        setStandardQuantitySold(ebayListing.getResults(), list.get(i));
                    else
                        setVariantQuantitySold(ebayListing.getResults(), list.get(i));
                    if (list.get(i).getListingType().equals("Standard") && ebayListing.getResults().getTotalQuantitySold() - list.get(i).getTotalQuantitySold() >= list.get(i).getQuantity()) {
                        if(list.get(i).getDualMode()!=null && list.get(i).getDualMode())
                            ebayService.reviseExistingListing(accountId, ownerId, list.get(i).getEbayItemId(), list.get(i).getNextResetPrice(), list.get(i).getNextResetQuantity().toString());
                        else {
                            ebayService.reviseExistingListing(accountId, ownerId, list.get(i).getEbayItemId(), list.get(i).getAfterPrice(), ebayListing.getResults().getQuantityAvailable().toString());
                            continueCompete(list.get(i).getEbayItemId());
                        }
                        list.get(i).setUpdated(true);
                        list.get(i).setLastEffectiveDate(new Date());
                        resetRepository.save(list.get(i));
                    } else if (!list.get(i).getListingType().equals("Standard")) {
                        VariationType variation = ebayListing.getResults().getVariationsTypes().getVariation()[i];
                        for (int j = 0; j < list.get(i).getVariationsTypes().getVariation().length; j++) {
                            if (variation.getSKU().equals(list.get(i).getVariantSku())) {
                                if (variation.getSellingStatus().getQuantitySold() - list.get(i).getTotalQuantitySold() >= list.get(i).getQuantity()) {
                                    if(list.get(i).getDualMode()!=null && list.get(i).getDualMode())
                                        ebayService.reviseVariantListing(accountId, ownerId, list.get(i).getEbayItemId(), list.get(i).getNextResetPrice(), list.get(i).getNextResetQuantity().toString(), list.get(i).getFirstVariationName(), list.get(i).getFirstVariationValue(), list.get(i).getSecondVariationName(), list.get(i).getSecondVariationValue(), list.get(i).getVariantSku());
                                    else {
                                        ebayService.reviseVariantListing(accountId, ownerId, list.get(i).getEbayItemId(), list.get(i).getAfterPrice(), variation.getQuantity().toString(), list.get(i).getFirstVariationName(), list.get(i).getFirstVariationValue(), list.get(i).getSecondVariationName(), list.get(i).getSecondVariationValue(), list.get(i).getVariantSku());
                                        continueCompete(list.get(i).getEbayItemId());
                                    }
                                    list.get(i).setUpdated(true);
                                    list.get(i).setLastEffectiveDate(new Date());
                                    resetRepository.save(list.get(i));
                                }
                            }
                        }
                    }
                }
                if(list.get(i).getUpdated() && (list.get(i).getDualMode()!=null && list.get(i).getDualMode())){
                    list.get(i).setMoveToAwaitingDispatch(false);
                    resetRepository.save(list.get(i));
                    ResponseEntity<EbayListing> ebayListing = ebayService.findStockById(ownerId, accountId, list.get(i).getEbayItemId());
                    if (list.get(i).getListingType().equals("Standard"))
                        setStandardQuantitySold(ebayListing.getResults(), list.get(i));
                    else
                        setVariantQuantitySold(ebayListing.getResults(), list.get(i));
                    if (list.get(i).getListingType().equals("Standard") && ebayListing.getResults().getTotalQuantitySold() - list.get(i).getTotalQuantitySold() - list.get(i).getQuantity() >= list.get(i).getNextResetQuantity()) {
                        ebayService.reviseExistingListing(accountId, ownerId, list.get(i).getEbayItemId(), list.get(i).getAfterPrice(), ebayListing.getResults().getQuantityAvailable().toString());
                        list.get(i).setNextResetUpdated(true);
                        continueCompete(list.get(i).getEbayItemId());
                        list.get(i).setLastEffectiveDate(new Date());
                        resetRepository.save(list.get(i));
                    } else if (!list.get(i).getListingType().equals("Standard")) {
                        VariationType variation = ebayListing.getResults().getVariationsTypes().getVariation()[i];
                        for (int j = 0; j < list.get(i).getVariationsTypes().getVariation().length; j++) {
                            if (variation.getSKU().equals(list.get(i).getVariantSku())) {
                                if (variation.getSellingStatus().getQuantitySold() - list.get(i).getTotalQuantitySold() - list.get(i).getQuantity() >= list.get(i).getNextResetQuantity()) {
                                    ebayService.reviseVariantListing(accountId, ownerId, list.get(i).getEbayItemId(), list.get(i).getAfterPrice(), variation.getQuantity().toString(), list.get(i).getFirstVariationName(), list.get(i).getFirstVariationValue(), list.get(i).getSecondVariationName(), list.get(i).getSecondVariationValue(), list.get(i).getVariantSku());
                                    list.get(i).setNextResetUpdated(true);
                                    continueCompete(list.get(i).getEbayItemId());
                                    list.get(i).setLastEffectiveDate(new Date());
                                    resetRepository.save(list.get(i));
                                }
                            }
                        }

                    }
                }
            }catch (Exception e){e.printStackTrace();}
        }

    }

    public ResponseEntity<Reset> getItemData(String id) {
        Reset reset=resetRepository.findOneById(id);
        Accounts accounts=accountsRepository.findByAccountsId(reset.getAccountId());
        try {
            ResponseEntity<EbayListing> listingDetails = ebayService.findStockById(reset.getOwnerId(), reset.getAccountId(), reset.getEbayItemId());
            if (reset.getListingType().equals("Standard")) {
                reset.setExistedQuantity(listingDetails.getResults().getQuantityAvailable());
                reset.setTotalQuantitySold(listingDetails.getResults().getTotalQuantitySold());
                reset.setEbayPrice(listingDetails.getResults().getStartPriceValue());
            } else {
                for (int i = 0; i < listingDetails.getResults().getVariationsTypes().getVariation().length; i++) {
                    if (reset.getVariantSku().equals(listingDetails.getResults().getVariationsTypes().getVariation()[i].getSKU())) {
                        reset.setExistedQuantity(listingDetails.getResults().getVariationsTypes().getVariation()[i].getQuantity());
                        reset.setEbayPrice(String.valueOf(listingDetails.getResults().getVariationsTypes().getVariation()[i].getStartPrice().getValue()));
                        reset.setTotalQuantitySold(listingDetails.getResults().getVariationsTypes().getVariation()[i].getSellingStatus().getQuantitySold());
                    }
                }
            }
            try {
                HashMap updateAdInfo=getAdRate(reset.getEbayItemId(), accounts.getOauthApiToken());
                reset.setBidPercentage(updateAdInfo.get("adRate").toString());
                reset.setAdId(updateAdInfo.get("adId").toString());
                reset.setCampaignId(updateAdInfo.get("campaignId").toString());
            }catch (Exception e){reset.setBidPercentage("Token Expired");}
            resetRepository.save(reset);
        }catch (Exception e){}
        return new ResponseEntity<Reset>().withResults(reset);
    }

    public ResponseEntity<Reset> update(Reset reset, String accountId) {
        Reset exist=resetRepository.findByIdAndEbayItemIdAndAccountIdAndListingHistory(reset.getId(),reset.getEbayItemId(),reset.getAccountId(),null);
        if(exist!=null) {
            reset.setLastEffectiveDate(new Date());
            try {
                if (reset.getListingType().equals("Standard"))
                    ebayService.reviseExistingListing(accountId, reset.getOwnerId(), reset.getEbayItemId(), reset.getEbayPrice(), reset.getExistedQuantity().toString());
                else
                    ebayService.reviseVariantListing(accountId, reset.getOwnerId(), reset.getEbayItemId(), reset.getEbayPrice(), reset.getExistedQuantity().toString(), reset.getFirstVariationName(), reset.getFirstVariationValue(), reset.getSecondVariationName(), reset.getSecondVariationValue(), reset.getVariantSku());
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Reset>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
            }
            reset.setUpdated(false);
            pauseDropship(reset.getEbayItemId());
            pauseCompete(reset.getEbayItemId());
            reset.setSoldFromReset(0);
            Reset save = resetRepository.save(reset);
            return new ResponseEntity<>().withResults(save);
        }
        return null;
    }

    public ResponseEntity<Reset> delete(Reset reset) {
        try{
            resetRepository.delete(reset);
        }catch (Exception e){return new ResponseEntity<Reset>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));}
        return new ResponseEntity<Reset>().withErrors(false);
    }

    public void setStandardQuantitySold(EbayListing ebayListing,Reset reset){
        Integer totalSold;
        if(reset.getTotalQuantitySold()>=ebayListing.getTotalQuantitySold())
            totalSold=reset.getTotalQuantitySold()-ebayListing.getTotalQuantitySold();
        else
            totalSold=ebayListing.getTotalQuantitySold()-reset.getTotalQuantitySold();
        saveQuantitySold(totalSold,reset);
        reset.setSoldFromReset(totalSold);
        resetRepository.save(reset);
    }

    public void setVariantQuantitySold(EbayListing ebayListing,Reset reset){
        Integer totalSold=0;
        for (int j = 0; j < reset.getVariationsTypes().getVariation().length; j++) {
            VariationType variation = ebayListing.getVariationsTypes().getVariation()[j];
            if (variation.getSKU().equals(reset.getVariantSku())) {
                if(variation.getSellingStatus().getQuantitySold()>=reset.getTotalQuantitySold())
                    totalSold=variation.getSellingStatus().getQuantitySold()-reset.getTotalQuantitySold();
                else
                    totalSold=reset.getTotalQuantitySold()-variation.getSellingStatus().getQuantitySold();
                saveQuantitySold(totalSold,reset);
                reset.setSoldFromReset(totalSold);
                resetRepository.save(reset);
            }
        }
    }

    public void saveQuantitySold(Integer totalSold,Reset reset){
        if(totalSold!=0) {
            if(totalSold-reset.getSoldFromReset()>0)
                saveToHistory(reset, totalSold - reset.getSoldFromReset());
            else if(totalSold-reset.getSoldFromReset()<0)
                saveToHistory(reset,reset.getSoldFromReset()-totalSold);
        }
    }

    public void pauseCompete(String itemId){
        List<Compete> exist = competeRepository.findByEbayItemId(itemId);
        if(exist.size()!=0) {
            for (int i = 0; i < exist.size(); i++){
                exist.get(i).setPause(true);
                competeRepository.save(exist.get(i));
            }
        }
    }

    public void pauseDropship(String itemId){
        List<Dropship> exist = dropshipRepository.findByItemId(itemId);
        if(exist.size()!=0) {
            for (int i = 0; i < exist.size(); i++){
                exist.get(i).setPause(true);
                dropshipRepository.save(exist.get(i));
            }
        }
    }

    public Boolean continueDropship(String itemId){
        List<Dropship> exist = dropshipRepository.findByItemId(itemId);
        if(exist.size()!=0) {
            for (int i = 0; i < exist.size(); i++){
                exist.get(i).setPause(false);
                dropshipRepository.save(exist.get(i));
            }
            return true;
        }
        else
            return false;
    }

    public void continueCompete(String itemId){
        if(!continueDropship(itemId)) {
            List<Compete> exist = competeRepository.findByEbayItemId(itemId);
            if (exist.size() != 0) {
                for (int i = 0; i < exist.size(); i++) {
                    exist.get(i).setPause(false);
                    competeRepository.save(exist.get(i));
                }
            }
        }
    }

    public void saveToHistory(Reset reset,Integer totalSold){
        Reset soldListing=new Reset();
        soldListing.setTitle(reset.getTitle());
        soldListing.setOwnerId(reset.getOwnerId());
        soldListing.setAccountId(reset.getAccountId());
        soldListing.setLastEffectiveDate(new Date());
        soldListing.setListingHistory(true);
        if(reset.getBidPercentage()!=null)
        soldListing.setBidPercentage(reset.getBidPercentage());
        soldListing.setEbayItemId(reset.getEbayItemId());
        soldListing.setEbayPrice(reset.getEbayPrice());
        soldListing.setListingType(reset.getListingType());
        soldListing.setQuantity(totalSold);
        resetRepository.save(soldListing);
    }

    public ResponseEntity<Reset> getCurrentHistory(String accountId, PageRequest pageRequest) {
        Page<Reset> list=resetRepository.findByOwnerIdAndAccountIdAndListingHistoryAndDate(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,true,new Date(),getNextDate(new Date()),pageRequest);
        return new ResponseEntity<List<Reset>>().withResults(list.getContent()).withTotalPages(list.getTotalPages()).withTotalElements(list.getTotalElements());
    }

    public ResponseEntity<Reset> getHistoryByDate(String accountId, Date date, PageRequest pageRequest) {
        Page<Reset> list=resetRepository.findByOwnerIdAndAccountIdAndListingHistoryAndDate(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,true,date,getNextDate(date),pageRequest);
        return new ResponseEntity<List<Reset>>().withResults(list.getContent()).withTotalPages(list.getTotalPages()).withTotalElements(list.getTotalElements());
    }

    private Date getNextDate(Date passingDate) {
        if(removeTime(new Date()).equals(passingDate))
            return new Date();
        else {
            Calendar c = Calendar.getInstance();
            c.setTime(passingDate);
            c.add(Calendar.DAY_OF_YEAR, 1);
            return removeTime(c.getTime());
        }
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

    public ResponseEntity<List<Orders>> MarkOrderAsReset(List<String> id, PageRequest pageRequest) {
        for (int i = 0; i < id.size(); i++) {
            Orders order = ordersRepository.findOneById(id.get(i));
            if (order != null && order.getPaidDate() != null) {
                order.setCheckResetOrder(true);
                ordersRepository.save(order);
            }
            List<Orders> o = ordersRepository.findByOwnerIdPkAndOrderTypeAndPOrderRefAndPaidDateNotNull(order.getOwnerIdPk(), "EBAY", order.getOrderRef());
            for (int j = 0; j < o.size(); j++) {
                    o.get(j).setCheckResetOrder(true);
                    ordersRepository.save(o.get(j));
            }

        }
        return null;
    }

    public HashMap getAdRate(String listingId,String token){
        String campaignId=getCampaignId(listingId,token);
        HashMap<String, String> map = new HashMap<>();
        String url = "https://api.ebay.com/sell/marketing/v1/ad_campaign/"+campaignId+"/ad?listing_ids="+listingId;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
        org.springframework.http.ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        map.put("adId",result.getBody().split("adId\":")[1].split(",")[0].replace("\"",""));
        map.put("campaignId",campaignId);
        map.put("adRate",result.getBody().split("Percentage\":")[1].split(",")[0].replace("\"",""));
        return map;
    }

    private String getCampaignId(String listingId, String token){
        String url = "https://api.ebay.com/sell/marketing/v1/ad_campaign/find_campaign_by_ad_reference?listing_id="+listingId;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
        org.springframework.http.ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return Objects.requireNonNull(result.getBody()).split("campaignId\":")[1].split(",")[0].replace("\"","");
    }

    public ResponseEntity<String> updateAdRate(Reset reset,String accountId){
        Accounts s=accountsRepository.findByAccountsId(accountId);
        String url = "https://api.ebay.com/sell/marketing/v1/ad_campaign/"+reset.getCampaignId()+"/ad/"+reset.getAdId()+"/update_bid";
        RestTemplate restTemplate = new RestTemplate();
        JSONObject body = new JSONObject();
        try {
            body.put("bidPercentage",reset.getBidPercentage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+s.getOauthApiToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(body.toString(), httpHeaders);
        try {
            org.springframework.http.ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        }catch (Exception e){
            return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        return new ResponseEntity<String>().withResults("Success");
        }

}
