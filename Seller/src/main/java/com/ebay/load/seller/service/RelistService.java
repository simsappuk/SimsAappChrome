package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.*;

import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.ActiveListingRepository;
import com.ebay.load.seller.repository.ActivityLogRepository;
import com.ebay.load.seller.repository.RelistRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.call.EndItemCall;
import com.ebay.sdk.call.RelistFixedPriceItemCall;
import com.ebay.soap.eBLBaseComponents.EndReasonCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class RelistService {

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    RelistRepository relistRepository;

    @Autowired
    ActiveListingRepository activeListingRepository;

    @Autowired
    EbayService ebayService;

    @Autowired
    ActivityLogRepository activityLogRepository;

    public ResponseEntity<Relist> reListing(String accountId, String ownerId, Relist listingDetails) {
        Accounts s=accountsRepository.findByIdAndOwnerIdPk(accountId,ownerId);
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        apiContext.setApiLogging(new ApiLogging());
        try{
            EndItemCall endItem=new EndItemCall(apiContext);
            endItem.setItemID(listingDetails.getItemId());
            endItem.setEndingReason(EndReasonCodeType.NOT_AVAILABLE);
            endItem.endItem();
            RelistFixedPriceItemCall relistItemCall=new RelistFixedPriceItemCall(apiContext);
            ItemType item=new ItemType();
            item.setItemID(listingDetails.getItemId());
            item.setSite(SiteCodeType.UK);
            relistItemCall.setItemToBeRelisted(item);
            relistItemCall.relistFixedPriceItem();
            String latestItemId=relistItemCall.getReturnedItemID();
            if(listingDetails.getPrice()==null)
                ebayService.reviseExistingListing(accountId,ownerId,latestItemId,null, String.valueOf(listingDetails.getReListQuantity()));
            else
                ebayService.reviseExistingListing(accountId,ownerId,latestItemId, String.valueOf(listingDetails.getPrice()),String.valueOf(listingDetails.getReListQuantity()));
            listingDetails.setRemainingQuantity(listingDetails.getRemainingQuantity()-listingDetails.getReListQuantity());
            listingDetails.setNewItemId(latestItemId);
        }catch (Exception e){e.printStackTrace();
            return new ResponseEntity<Relist>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        return new ResponseEntity<Relist>().withResults(listingDetails);
    }

    @Transactional
    public void schedulerReList(String ownerId,String accountId){
        List<Relist> relists=relistRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        for(int i=0;i<relists.size();i++){
            Relist relist=relists.get(i);
            ActiveListings activeListings=activeListingRepository.findByOwnerIdAndAccountIdAndItemId(ownerId,accountId,relist.getNewItemId());
            if(activeListings!=null && activeListings.getQuantityAvailable()==0 && relist.getRemainingQuantity()!=0){
                if(relist.getRemainingQuantity()!=0 && relist.getRemainingQuantity()<relist.getReListQuantity())
                    relist.setReListQuantity(relist.getRemainingQuantity());
                if(relist.getRemainingQuantity()!=0) {
                    reListing(accountId, ownerId, relist);
                    ArrayList<String> previousItem = new ArrayList<>();
                    if (relist.getItemIdList().size() != 0)
                        previousItem.addAll(relist.getItemIdList());
                    previousItem.add(relist.getItemId());
                    relist.setItemIdList(previousItem);
                    relist.setItemId(relist.getNewItemId());
                    relist.setLastEffectiveDate(new Date());
                }
                relistRepository.save(relist);
            }
        }
    }

    public ResponseEntity<List<Relist>> getContent(String ownerId,String accountId) {
        List<Relist> list=relistRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        return new ResponseEntity<List<Relist>>().withResults(list);
    }

    @Transactional
    public ResponseEntity<List<Relist>> save(Relist relist,String ownerId, String accountId) {
        Relist exist=relistRepository.findByAccountIdAndOwnerIdAndItemId(accountId,ownerId,relist.getItemId());
        if(exist==null)
        try {
            ActiveListings activeListings=activeListingRepository.findByOwnerIdAndAccountIdAndItemId(ownerId,accountId,relist.getItemId());
            if(activeListings==null)
                return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("ItemID Doesn't Exist"));
            else
                if(relist.getRemainingQuantity()!=0 && relist.getRemainingQuantity()<relist.getReListQuantity())
                    relist.setReListQuantity(relist.getRemainingQuantity());
                if(relist.getRemainingQuantity()!=0) {
                    if(activeListings.getQuantityAvailable()==0) {
                        ResponseEntity<Relist> result = reListing(accountId, ownerId, relist);
                        if (result.isErrors()) {
                            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText(result.getMessages().get(0).getMessageText()));
                        }
                        ArrayList<String> previousItem = new ArrayList<>();
                        previousItem.add(relist.getItemId());
                        relist.setItemIdList(previousItem);
                    }
                    else
                        relist.setNewItemId(relist.getItemId());
                    relist.setItemId(relist.getNewItemId());
                    relist.setLink("https://www.ebay.co.uk/itm/" + relist.getNewItemId());
                    relist.setLastEffectiveDate(new Date());
                    relist.setAccountId(accountId);
                    relist.setOwnerId(ownerId);
                    relistRepository.save(relist);

                }
                return getContent(ownerId, accountId);
        }catch (Exception e){
            return new ResponseEntity<Relist>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
        else if(exist.getNewItemId().equals(relist.getItemId()))
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("ENTERED INFO ALREADY EXIST"));
        else
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("ENTERED INFO ALREADY EXIST"));
    }

    public ResponseEntity<List<Relist>> delete(List<String> id, String accountId, PageRequest pageRequest) {
        for(int i=0;i<id.size();i++) {
            Relist relist = relistRepository.findByIdAndAccountId(id.get(i),accountId);
            relistRepository.delete(relist);
        }
        List<Relist> s=relistRepository.findByOwnerIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
        return new ResponseEntity<List<Relist>>().withResults(s);
    }


    public String update(Relist relist) {
        if(relist.getRemainingQuantity()==null)
            relist.setRemainingQuantity(0);
        relistRepository.saveAndFlush(relist);
        return "Saved";
    }

    public ResponseEntity<Relist> getContentToUpdate(String accountId, String id) {
        Relist relist=relistRepository.findByIdAndAccountId(id,accountId);
        return new ResponseEntity<Relist>().withResults(relist);
    }

    public ResponseEntity<String> checkItemExistence(String accountId, String id, String itemId) {
        ActiveListings activeListings=activeListingRepository.findByOwnerIdAndAccountIdAndItemId(id,accountId,itemId);
        if(activeListings==null)
            return new ResponseEntity<String>().withResults("ItemID doesn't exist,Please Recheck");
        else
            return new ResponseEntity<String>().withResults("ItemID Exist");
    }

    @Transactional
    public void addRelistLog(Relist relist){
        try {
            ActivityLog logExistence = activityLogRepository.findByOwnerIdAndAccountIdAndItemNumber(relist.getOwnerId(), relist.getAccountId(), relist.getNewItemId());
            if (logExistence == null) {
                Accounts accounts = accountsRepository.findByAccountsId(relist.getAccountId());
                Channel channel = new Channel();
                channel.setId("24");
                ActivityLog activityLog = new ActivityLog();
                activityLog.setItemNumber("<a href='https://www.ebay.co.uk/itm/" + relist.getNewItemId() + "'" + "target='_blank'>" + relist.getNewItemId() + "</a>");
                if (relist.getLastEffectiveDate() != null)
                    activityLog.setModifiedDate(relist.getLastEffectiveDate());
                if (relist.getRemainingQuantity() != null)
                    activityLog.setQuantity(relist.getRemainingQuantity());
                if (relist.getSku() != null)
                    activityLog.setSku(relist.getSku());
                if (relist.getItemIdList()!=null && relist.getItemIdList().size()!= 0)
                    activityLog.setItemIdList(relist.getItemIdList());
                if (accounts!=null)
                    activityLog.setSpreadsheetId(accounts.getSpreadSheetId());
                activityLog.setTitle(relist.getTitle());
                activityLog.setAccountId(relist.getAccountId());
                activityLog.setOwnerId(relist.getOwnerId());
                activityLog.setChannel(channel);
                activityLog.setReListQuantity(relist.getReListQuantity());
                activityLogRepository.save(activityLog);
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public ResponseEntity<List<Relist>> searchListing(String text, String ownerId, String accountId, PageRequest pageRequest) {
        if(text.matches("[0-9]+")){
            Relist relist=relistRepository.findByOwnerIdAndAccountIdAndNewItemId(ownerId,accountId,text);
            return new ResponseEntity<List<Relist>>().withResults(Collections.singletonList(relist));
        }
        else if(text.equals("")){
            return getContent(ownerId,accountId);
        }
        else {
            List<Relist> title = relistRepository.findByOwnerIdAndAccountIdAndTitleContainingIgnoreCase(ownerId, accountId, text);
            return new ResponseEntity<List<Relist>>().withResults(title);
        }
    }
}
