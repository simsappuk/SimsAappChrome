package com.ebay.load.seller.rest;


import com.ebay.load.seller.config.SessionUserInfo;

import com.ebay.load.seller.model.Relist;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.RelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/relist")
public class RelistController {

    @Autowired
    RelistService relistService;

    @RequestMapping(value = "/getData",method= RequestMethod.GET)
    public ResponseEntity<List<Relist>> getData(@RequestParam(value = "accountId")String accountId){
        return relistService.getContent(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
    }

    @RequestMapping(value = "/form/update",method= RequestMethod.GET)
    public ResponseEntity<Relist> getDataToupdate(@RequestParam(value = "accountId")String accountId,
                                                  @RequestParam(value = "id")String id){
        return relistService.getContentToUpdate(accountId,id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<List<Relist>> saveCompeteData(@RequestBody Relist relist,
                                                   @RequestParam String accountId){
        return  relistService.save(relist,SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
    }

    @RequestMapping(value = "/check/itemId",method = RequestMethod.GET)
    public ResponseEntity<String> checkItemExistence(@RequestParam(value = "accountId")String accountId,
                                                  @RequestParam(value = "itemId") String itemId){
        return  relistService.checkItemExistence(accountId,SessionUserInfo.getLoggedInUser().getUser().getId(),itemId);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String UpdateCompeteData(@RequestBody Relist relist){
        return  relistService.update(relist);
    }


    @RequestMapping(value = "/reListing/{accountId}",method = RequestMethod.POST)
    public ResponseEntity<Relist> reListing(@PathVariable("accountId") String accountId, @RequestBody Relist item){
        return relistService.reListing(accountId, SessionUserInfo.getLoggedInUser().getUser().getId(),item);
    }

    @RequestMapping(value="/delete/item/{id}/{accountId}",method=RequestMethod.POST)
    public ResponseEntity<List<Relist>> deleteItem(@PathVariable("id") List<String> id,
                                                           @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                           @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize,
                                                           @PathVariable(value="accountId") String accountId){
        return relistService.delete(id,accountId,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @RequestMapping(value="/search",method=RequestMethod.GET)
    public ResponseEntity<List<Relist>> getSearchItem(@RequestParam(value="id",defaultValue = "",required = false)String accountId,
                                                              @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                              @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                              @RequestParam(value="q",defaultValue = "",required = false) String text){
        return relistService.searchListing(text,SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page,pageSize));
    }
}
