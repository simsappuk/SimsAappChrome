package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.ExternalStockLinks;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.EbayService;
import com.ebay.load.seller.service.ExternalStockLinksService;
import com.ebay.load.seller.service.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/externalStockLinks")
public class ExternalStockLinksController {

    @Autowired
    ExternalStockLinksService externalStockLinksService;


    @RequestMapping(value = "/getData",method= RequestMethod.GET)
    public ResponseEntity<ExternalStockLinks> getData(@RequestParam(value = "accountId")String accountId){
        return externalStockLinksService.getContent(accountId);
    }

    @RequestMapping(value="/getStockData",method= RequestMethod.GET)
    public ResponseEntity<String> getAmazonPrice(@RequestParam(value="url",defaultValue = "",required = false)String url,
                                                 @RequestParam(value = "accountId")String accountId){
        String output="";
        if(url.matches("[0-9]+"))
            output = externalStockLinksService.getEbayPrice(url,accountId);
        else
            output = HttpClient.sendGet(url);
        return new ResponseEntity<String>().withResults(output);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<ExternalStockLinks> saveData(@RequestBody ExternalStockLinks externalStockLinks,
                                                       @RequestParam String accountId){
        return  externalStockLinksService.save(externalStockLinks,accountId);
    }

    @RequestMapping(value = "/updateContent",method = RequestMethod.GET)
    public ResponseEntity<List<ExternalStockLinks>> updateContent(@RequestParam String accountId){
        return  externalStockLinksService.updateStockLinks(accountId, SessionUserInfo.getLoggedInUser().getUser().getId());
    }

    @RequestMapping(value="/delete/item/{id}/{accountId}",method=RequestMethod.POST)
    public ResponseEntity<List<ExternalStockLinks>> deleteContent(@PathVariable("id") List<String> id,
                                                           @PathVariable(value="accountId") String accountId){
        return externalStockLinksService.deleteContent(id,accountId,new PageRequest(0,100));
    }

}
