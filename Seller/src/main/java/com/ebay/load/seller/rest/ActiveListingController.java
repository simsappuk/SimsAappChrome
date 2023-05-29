package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.ActiveListings;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.EbayService;
import com.ebay.load.seller.service.StockService;
import com.ebay.soap.eBLBaseComponents.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ActiveListing/")
public class ActiveListingController {


    @Autowired
    EbayService ebayService;


    @RequestMapping(value = "/{id}/list", method = RequestMethod.GET)
    public ResponseEntity<List<ActiveListings>> loadCurrentRequest(@PathVariable("id") String id,
                                                                   @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                   @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return ebayService.findAllByOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId(),id, new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/post/{accountId}",method = RequestMethod.POST)
    public ResponseEntity<ItemType> postEbayListing(@PathVariable("accountId") String accountId,@RequestBody EbayListing item){
        return ebayService.postNewListing(accountId,item);
    }

    @RequestMapping(value = "/pushListing",method = RequestMethod.POST)
    public ResponseEntity<String> pushListing(@RequestParam(value = "accountId")String accountId,
                                              @RequestParam(value = "listingId")List<String> listingId){
        return  ebayService.pushToRelist(listingId,SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
    }

    @RequestMapping(value = "/revise/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<EbayListing> ReviseEbayListing(@PathVariable("accountId") String accountId,
                                                         @RequestParam(value = "itemId")String itemId,
                                                         @RequestParam(value = "amount")String amount,
                                                         @RequestParam(value = "quantity")String quantity,
                                                         @RequestParam(value = "listingType")String listingType,
                                                         @RequestParam(value="firstVariationName")String firstVariantName,
                                                         @RequestParam(value = "firstVariationValue")String firstVariantValue,
                                                         @RequestParam(value = "variantSku")String variantSku,
                                                         @RequestParam(value = "secondVariationName")String secondVariantName,
                                                         @RequestParam(value = "secondVariationValue")String secondVariantValue
                                                         ){
        if(listingType.equals("Standard"))
            return ebayService.reviseExistingListing(accountId,SessionUserInfo.getLoggedInUser().getUser().getId(),itemId,amount,quantity);
        else
            return ebayService.reviseVariantListing(accountId,SessionUserInfo.getLoggedInUser().getUser().getId(),itemId,amount,quantity,firstVariantName,firstVariantValue,secondVariantName,secondVariantValue,variantSku);
    }

    @RequestMapping(value = "/reviseSetNewSku",method = RequestMethod.GET)
    public ResponseEntity<EbayListing> ReviseSetNewSku(@RequestParam("accountId") String accountId,
                                                         @RequestParam(value = "itemId")String itemId,
                                                       @RequestParam(value = "sku")String sku
    ){
        return ebayService.reviseSetNewSKU(accountId,SessionUserInfo.getLoggedInUser().getUser().getId(),itemId,sku);
    }

    @RequestMapping(value="/{id}/database",method = RequestMethod.GET)
    public ResponseEntity<List<ActiveListings>> loadActiveListings( @PathVariable("id") String id,
                                                                    @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                    @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize)
            {
    return ebayService.findAll(SessionUserInfo.getLoggedInUser().getUser().getId(),id,new PageRequest(page, pageSize));
    }



    @ResponseBody
    @RequestMapping(value = "{appId}/{id}", method = RequestMethod.GET)
    ResponseEntity<EbayListing> getSingleStock(@PathVariable("id") String id, @PathVariable("appId") String accountId) {
        return ebayService.findStockById(SessionUserInfo.getLoggedInUser().getUser().getId(), accountId,id);

    }

    @RequestMapping(value = "/sorting", method = RequestMethod.GET)
    public ResponseEntity<List<ActiveListings>> loadCurrentRequest(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                                @RequestParam(value = "accountId", defaultValue = "*", required = false) String id,
                                                                @RequestParam(value = "sortBy",defaultValue = "quantity",required = false) String sortBy) {
        return ebayService.sortByOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId(),id, new PageRequest(page, pageSize,orderBy(sortBy)));

    }

    private Sort orderBy(String sortBy) {
        String[] s=sortBy.split("_");
        if(s[1].equals("asc"))
            return new Sort(Sort.Direction.ASC, s[0]);
        else
            return new Sort(Sort.Direction.DESC,s[0]);
    }

    @RequestMapping(value="/search",method=RequestMethod.GET)
    public ResponseEntity<List<ActiveListings>> getSearchItem(@RequestParam(value="id",defaultValue = "",required = false)String accountId,
                                                      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                      @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                      @RequestParam(value="q",defaultValue = "",required = false) String text){
        return ebayService.searchListing(text,SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page,pageSize));
    }
}


