package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Compete;
import com.ebay.load.seller.model.Reset;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.CompeteRepository;
import com.ebay.load.seller.repository.ResetRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.soap.eBLBaseComponents.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CompeteService {

    @Autowired
    CompeteRepository competeRepository;

    @Autowired
    EbayService ebayService;

    @Autowired
    ResetRepository resetRepository;

    @Autowired
    AccountsRepository accountsRepository;



    public ResponseEntity<Compete> getContent(String accountId) {
        List<Compete> list=competeRepository.findByOwnerIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
        return new ResponseEntity<List<Compete>>().withResults(list);
    }

    public List<String> getBuyingAndSellingPrice(String s) {
        String buyingPrice=s.substring(1);
        Double value=Math.round((Double.parseDouble(buyingPrice)-0.05) * 100D) / 100D;
        String sellingPrice="£"+value;
        ArrayList<String> results=new ArrayList<String>();
        results.add("£"+buyingPrice); results.add(sellingPrice);
        return results;
    }

    public ResponseEntity<String> newSellingPrice(String bp,String minValue) {
        String buyingPrice=bp.substring(1);
        try {
            if (minValue == null || minValue.equals(""))
                minValue = "0.05";
            Double value = Math.round((Double.parseDouble(buyingPrice) - Double.parseDouble(minValue)) * 100D) / 100D;
            String sellingPrice = "£" + value;
            return new ResponseEntity<String>().withResults(sellingPrice);
        }catch (Exception e){e.printStackTrace();}
        return new ResponseEntity<String>().withResults("Undefined");
    }

    public ResponseEntity<Compete> save(Compete compete, String accountId) {
        try {
            List<Compete> exist = competeRepository.findByEbayItemId(compete.getEbayItemId());
            List<Reset> list=resetRepository.findByOwnerIdAndAccountIdAndEbayItemIdAndListingHistory(compete.getOwnerId(),accountId,compete.getEbayItemId(),null);
            if (list.size()==0 && (exist.size() == 0 || compete.getVariantSku()!=null)) {
                if (compete.getListingType().equals("Standard")) {
                    ResponseEntity<EbayListing> result = ebayService.reviseExistingListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(), compete.getEbayItemId(), compete.getEbayPrice().substring(1), null);
                    if (result.isErrors()) {
                        return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
                    }
                } else {
                    ResponseEntity<EbayListing> result = ebayService.reviseVariantListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(), compete.getEbayItemId(), compete.getEbayPrice().substring(1), null, compete.getFirstVariationName(), compete.getFirstVariationValue(), compete.getSecondVariationName(), compete.getSecondVariationValue(), compete.getVariantSku());
                    if (result.isErrors()) {
                        return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
                    }
                }
                compete.setLastEffectiveDate(new Date());
                compete.setLink(getLink(compete));
                compete.setAccountId(accountId);
                if(list.size()==0)
                    compete.setPause(false);
                else
                    compete.setPause(true);
                compete.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
                Compete save = competeRepository.save(compete);
                return new ResponseEntity<>().withResults(save);
            } else if (compete.getCompeteItemId().equals(compete.getEbayItemId())) {
                return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("COMPETITOR'S ITEM ID MATCHES WITH OUR ITEM ID"));
            } else
                return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("ENTERED COMPETE INFO ALREADY EXIST"));
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    @Transactional
    public void  updateCompeteEbaySellingPrice(String accountId,String ownerId){
        List<Compete> list=competeRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                Compete compete = list.get(i);
                if(!compete.isPause()) {
                    String amount = String.valueOf(getPricingData(compete.getCompeteItemId(), null, "getMinValue",accountId).getResults().get(0));
                    if (!amount.equals(compete.getSellerPrice()) && !amount.matches("The listing you're looking for has ended.|We're sorry, something went wrong. Please try again.")) {
                        try {
                            ResponseEntity<String> newPrice = newSellingPrice(amount, compete.getSubtractValue());
                            if (compete.getEbayItemId() != null && compete.getEbayPrice() != null)
                                if (compete.getListingType().equals("Standard")) {
                                    ResponseEntity<EbayListing> result = ebayService.reviseExistingListing(accountId, ownerId, compete.getEbayItemId(), newPrice.getResults().substring(1), null);
                                    if (result.getResults().getDescription().equals("UPDATE SUCCESS!"))
                                        saveUpdated(compete, amount, accountId, ownerId, newPrice.getResults());
                                } else {
                                    ResponseEntity<EbayListing> result = ebayService.reviseVariantListing(accountId, ownerId, compete.getEbayItemId(), newPrice.getResults().substring(1), null, compete.getFirstVariationName(), compete.getFirstVariationValue(), compete.getSecondVariationName(), compete.getSecondVariationValue(), compete.getVariantSku());
                                    if (result.getResults().getDescription().equals("UPDATE SUCCESS!"))
                                        saveUpdated(compete, amount, accountId, ownerId, newPrice.getResults());
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (amount.matches("We're sorry, something went wrong. Please try again.|The listing you're looking for has ended.")) {
                        try {
                            competeRepository.delete(compete);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void saveUpdated(Compete compete,String amount,String accountId,String ownerId,String newPrice) {
        compete.setLastEffectiveDate(new Date());
        compete.setSellerPrice(amount);
        compete.setLink(getLink(compete));
        compete.setAccountId(accountId);
        compete.setOwnerId(ownerId);
        compete.setEbayPrice(newPrice);
        competeRepository.save(compete);
    }

    public ResponseEntity<Compete> delete(Compete compete) {
        competeRepository.delete(compete);
        return null;
    }

    public ResponseEntity<Compete> update(Compete compete, String accountId) {
        if (!compete.isPause()) {
            List<Compete> exist = competeRepository.findByIdAndEbayItemId(compete.getId(), compete.getEbayItemId());
            if (exist.size() == 1 || exist.size() == 0) {
                if (compete.getListingType().equals("Standard")) {
                    ResponseEntity<EbayListing> result = ebayService.reviseExistingListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(), compete.getEbayItemId(), compete.getEbayPrice().substring(1), null);
                    if (result.isErrors()) {
                        return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
                    }
                } else {
                    ResponseEntity<EbayListing> result = ebayService.reviseVariantListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(), compete.getEbayItemId(), compete.getEbayPrice().substring(1), null, compete.getFirstVariationName(), compete.getFirstVariationValue(), compete.getSecondVariationName(), compete.getSecondVariationValue(), compete.getVariantSku());
                    if (result.isErrors()) {
                        return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
                    }
                }
                compete.setLastEffectiveDate(new Date());
                compete.setLink(getLink(compete));
                compete.setAccountId(accountId);
                compete.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
                competeRepository.saveAndFlush(compete);
                return null;
            } else if (compete.getCompeteItemId().equals(compete.getEbayItemId())) {
                return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("UPDATE FAILED : COMPETITOR'S ITEM ID MATCHES WITH OUR ITEMID"));
            } else
                return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("UPDATE FAILED : COMPETE ITEM ID ALREADY EXIST"));
        }
        else
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("UPDATE FAILED : COMPETE ITEM ID IS ON PAUSE MODE"));
    }

    public ResponseEntity<Compete> getItemData(String id) {
        Compete c=competeRepository.findOneById(id);
        return new ResponseEntity<Compete>().withResults(c);
    }

    public String getLink(Compete compete){
        String url="";
        if(compete.getCompeteItemId().size()==1)
            url = "https://www.ebay.co.uk/itm/" + compete.getCompeteItemId().get(0);
        else
            url="https://www.ebay.co.uk/itm/" + getPricingData(compete.getCompeteItemId(),"getId",null,compete.getAccountId()).getResults().get(0);
        return url;
    }

    public ResponseEntity<List<Compete>> checkCompeteItemIdAvailability(String competeItemId, String accountId) {
        List<Compete> s=competeRepository.findByCompeteItemIdList(competeItemId,accountId);
        if(s.size()!=0)
            return new ResponseEntity<List<Compete>>().withResults(s);
        else
            return null;
    }

    public ResponseEntity<List<String>> getPricingData(List<String> itemIds,String idPosition,String minPrice,String accountId) {
        List<String> prices=new ArrayList<>();
        Accounts accounts =accountsRepository.findByAccountsId(accountId);
        for(int i=0;i<itemIds.size();i++){
            try {
                ItemType item = ebayService.geItem(itemIds.get(i), accounts);
                if (item!= null){
                    String s = String.valueOf(item.getStartPrice().getValue());
                if (s.matches("The listing you're looking for has ended.|We're sorry, something went wrong. Please try again."))
                    return new ResponseEntity<List<String>>().withResults(Collections.singletonList(s));
                else if (s.matches(itemIds.get(i)))
                    return new ResponseEntity<List<String>>().withResults(Collections.singletonList("Please Enter a valid Item ID."));
                else
                    prices.add(s.replace("£", ""));
                }
            }catch (Exception e){e.printStackTrace();}
        }
        return  getPrice(prices,itemIds,idPosition,minPrice);
    }

    private ResponseEntity<List<String>> getPrice(List<String> prices,List<String> competeItemIds,String idPosition,String minPrice) {
        String minValue=prices.get(0);Integer position=0;
        for(int j=1;j<prices.size();j++){
            if(Double.parseDouble(prices.get(j))<Double.parseDouble(minValue)){
                minValue=prices.get(j);position=j;
            }
        }
        if(minPrice!=null && idPosition==null)
            return new  ResponseEntity<List<String>>().withResults(Collections.singletonList("£" + minValue));
        else if(idPosition==null && minPrice== null)
            return new  ResponseEntity<List<String>>().withResults(getBuyingAndSellingPrice("£"+minValue));
        else
            return new  ResponseEntity<List<String>>().withResults(Collections.singletonList(competeItemIds.get(position)));
    }
}
