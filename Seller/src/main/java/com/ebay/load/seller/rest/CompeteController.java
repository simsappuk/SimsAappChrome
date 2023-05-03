package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.Compete;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.CompeteService;
import com.ebay.load.seller.service.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/compete")
public class CompeteController {

    @Autowired
    CompeteService competeService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Compete> saveCompeteData(@RequestBody Compete compete,
                                                   @RequestParam String accountId){
        return  competeService.save(compete,accountId);
    }

    @RequestMapping(value="/competeItemId",method=RequestMethod.GET)
    public ResponseEntity<List<Compete>> getId(@RequestParam(value="itemId",defaultValue = "",required = false) String competeItemId,
                                              @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return competeService.checkCompeteItemIdAvailability(competeItemId,accountId);
    }

    @RequestMapping(value = "/getData",method= RequestMethod.GET)
    public ResponseEntity<Compete> getData(@RequestParam(value = "accountId")String accountId){
        return competeService.getContent(accountId);
    }

    @RequestMapping(value="/getBuyingAndSellingPrice",method=RequestMethod.GET)
    public ResponseEntity<List<String>> getBuyingAndSellingPrice(@RequestParam(value="url",defaultValue = "",required = false)List<String> url,
                                                                 @RequestParam(value = "accountId")String accountId){
        return competeService.getPricingData(url,null,null,accountId);
    }

    @RequestMapping(value = "/calculate/getNewSellingPrice",method= RequestMethod.GET)
    public ResponseEntity<String> getNewSP(@RequestParam(value = "bp",defaultValue = "",required = false) String buyingPrice,
                                           @RequestParam(value = "subtract",defaultValue = "0.05",required = false) String subtractValue){
        return competeService.newSellingPrice(buyingPrice,subtractValue);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseEntity<Compete> deleteCompeteData(@RequestBody Compete compete){
        return  competeService.delete(compete);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<Compete> updateCompeteData(@RequestBody Compete compete,@RequestParam String accountId){
        return  competeService.update(compete,accountId);
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    public ResponseEntity<Compete> getItemData(@PathVariable("id") String id){
        return competeService.getItemData(id);
    }
}
