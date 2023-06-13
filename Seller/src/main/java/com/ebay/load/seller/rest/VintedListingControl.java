package com.ebay.load.seller.rest;

import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.model.VintedListing;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.VintedListingRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.VintedListingService;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetMyeBaySellingCall;
import com.ebay.soap.eBLBaseComponents.ItemListCustomizationType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vintedListing")
public class VintedListingControl {
    private VintedListingRepository vintedListingRepository;

    private VintedListingService vintedListingService;


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public VintedListing vintedNewListing(@RequestBody VintedListing vintedListing, @PathVariable("id") String account_name) {
        String s =vintedListingRepository.findById(account_name);
        return vintedListingService.save(vintedListing,account_name);
    }
    @RequestMapping(value = "/{id}/{action}", method = RequestMethod.GET)
    public  VintedListing vintedgetNewListing(@PathVariable(value="id") String accountId,@PathVariable(value="action") String action) {
        //return vintedListingService.findAllById(accountId,action);
        return new VintedListing();
    }
//    @RequestMapping(value = "/{id}/{action}", method = RequestMethod.GET)
//    public  List<VintedListing> vintedgetNewListing(@PathVariable(value="id") String accountId,@PathVariable(value="action") String action) {
//        return vintedListingService.findAllById(accountId,action);
//    }
    @RequestMapping(value="/listings/database",method=RequestMethod.GET)
    public ResponseEntity<List<Vinted>> getStock(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                 @RequestParam(value="accountId",defaultValue = "",required = false) String accountId){
//        return stockService.getListingsAndCheckWithStock(accountId, new PageRequest(page.intValue(),pageSize.intValue()));
        Pageable p = new PageRequest(page.intValue(),pageSize.intValue());
        String a = "findByAccountsId(accountId)";
        boolean processing = true;
        int totLoaded = 0;

//        if (a != null) {
//            int currentPage = 1;
//            Pageable pb = new PageRequest(currentPage, p.getPageSize());
//            do {
//                ResponseEntity < List <EbayListing>> l = getListings(a, pb);
//                if (l != null && !l.isErrors()) {
//                    for (int j = 0; j < l.getResults().size(); j++) {
//                        Stock sk = stockRepository.findByOwnerIdAndItemIdAndAccountId(a.getOwnerIdPk(), a.getId(), l.getResults().get(j).getItemId());
//                        if (sk == null) {
//                            saveListingDetailsToStock(l.getResults().get(j), a);
//                        } else if (l.getResults().get(j).getVariantItemDetails() != null) {
//                            updateListingDetailsInStock(sk, l.getResults().get(j));
//                        }
//                    }
//                } else if (l.isErrors() == true)
//                    return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(l.getMessages().get(0).getMessageText()));
//                totLoaded += l.getResults().size();
//                if (totLoaded >= l.getTotalElements()) processing = false;
//                if (totLoaded < l.getTotalElements()) {
//                    currentPage++;
//                    pb = new PageRequest(currentPage, p.getPageSize());
//                }
//            } while (processing);
//        } else {
//            return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("Account Not Found"));
//        }
//        Page< Stock > s = stockRepository.findAll(p);
//        setItemIdToStock(accountId);
//        return new ResponseEntity < List < Stock >> ().withResults(s.getContent()).withTotalPages(s.getTotalPages()).withTotalElements(s.getTotalElements());
        return null;
    }

    public ResponseEntity < List < EbayListing >> getListings(Accounts s, Pageable p) {

        ItemType[] tempActiveItems = null;
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
        try {
            GetMyeBaySellingCall api = new GetMyeBaySellingCall(apiContext);
            ItemListCustomizationType itemListType = new ItemListCustomizationType();
            itemListType.setInclude(Boolean.TRUE);
            PaginationType page = new PaginationType();
            page.setPageNumber(p.getPageNumber());
            itemListType.setPagination(page);
            api.setActiveList(itemListType);
            api.setScheduledList(itemListType);
            api.setSoldList(itemListType);
            api.setUnsoldList(itemListType);
            try {
                api.getMyeBaySelling();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity < List < EbayListing >> ().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
            }

            if (api.getReturnedActiveList() != null)
                tempActiveItems = (api.getReturnedActiveList().getItemArray()).getItem();
            int totalNumberOfEntries = api.getReturnedActiveList().getPaginationResult().getTotalNumberOfEntries();
            int totalNumberOfPages = api.getReturnedActiveList().getPaginationResult().getTotalNumberOfPages();
            final ItemType[] activeItems = tempActiveItems;
            if (activeItems != null) {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}








