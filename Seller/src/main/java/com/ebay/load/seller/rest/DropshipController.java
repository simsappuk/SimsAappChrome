package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Dropship;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.DropshipService;
import com.ebay.load.seller.service.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static com.ebay.load.seller.rest.AwaitingDispatchController.getDate;
import static com.ebay.load.seller.rest.AwaitingDispatchController.getNextDate;

@RestController
@RequestMapping(value = "api/dropship")
public class DropshipController {

    @Autowired
    DropshipService dropshipService;

    @RequestMapping(value = "/getData",method= RequestMethod.GET)
    public ResponseEntity<Dropship> getData(@RequestParam(value = "accountId")String accountId){
        return dropshipService.getContent(accountId);
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    public ResponseEntity<Dropship> getItemData(@PathVariable("id") String id){
        return dropshipService.getItemData(id);
    }

    @RequestMapping(value = "/newCondition/getSellingPrice",method= RequestMethod.GET)
    public ResponseEntity<String> getNewSP(@RequestParam(value = "condition",defaultValue = "",required = false) String condition,
                                             @RequestParam(value = "epp",defaultValue = "",required = false) String ebayPaypalPercent,
                                             @RequestParam(value = "transFee",defaultValue = "",required = false) String transactionFee,
                                             @RequestParam(value = "profit",defaultValue = "",required = false) String profitPercent,
                                             @RequestParam(value = "bp",defaultValue = "",required = false) String buyingPrice,
                                             @RequestParam(value = "vat",defaultValue = "",required = false) String vatPercent
                                             ){
        return dropshipService.newSellingPrice(condition,ebayPaypalPercent,transactionFee,profitPercent,buyingPrice,vatPercent);
    }


    @RequestMapping(value = "/calculateVat",method= RequestMethod.GET)
    public ResponseEntity<String> getVat(@RequestParam(value = "condition",defaultValue = "",required = false) String condition,
                                           @RequestParam(value = "sp",defaultValue = "",required = false) String sellingPrice,
                                           @RequestParam(value = "bp",defaultValue = "",required = false) String buyingPrice
    ){
        return dropshipService.calculateVat(condition,buyingPrice,sellingPrice);
    }


    @RequestMapping(value="/getAmazonData",method=RequestMethod.GET)
    public ResponseEntity<String> getAmazonPrice(@RequestParam(value="url",defaultValue = "",required = false)String url){
        String s = HttpClient.sendGet(url);
        return new ResponseEntity<String>().withResults(s.replace("£",""));
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Dropship> saveDropshipData(@RequestBody Dropship dropship,
                                                     @RequestParam String accountId){
      return  dropshipService.save(dropship,accountId);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseEntity<Dropship> deleteDropshipData(@RequestBody Dropship dropship){
        return  dropshipService.delete(dropship);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<Dropship> updateDropshipData(@RequestBody Dropship dropship,@RequestParam String accountId){
        return  dropshipService.update(dropship,accountId);
    }

    @RequestMapping(value="/dropship/order/{id}",method=RequestMethod.POST)
    public ResponseEntity<List<Orders>> dropshipItem(@PathVariable("id") List<String> id,
                                                            @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                            @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize) throws ParseException {
        return dropshipService.MarkOrderAsDropship(id,new PageRequest(page.intValue(),pageSize.intValue()));
    }
    @RequestMapping(value="/dropshipPurchasedOrder/{id}/{logId}",method=RequestMethod.POST)
    public ResponseEntity<List<Orders>> dropshipPurchasedOrder(@PathVariable("id") String id,
                                                               @PathVariable("logId") String logId){
        return dropshipService.dropshipPurchasedOrder(id,logId);
    }

    @RequestMapping(value="/undoDropship/order/{id}",method=RequestMethod.POST)
    public ResponseEntity<List<Orders>> undoDropshippedItem(@PathVariable("id") List<String> id,
                                                            @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                            @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize){
        return dropshipService.undoDropshippedItem(id,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @RequestMapping(value = "/{appId}/dropshipOrders", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getDropshipOrders(@PathVariable("appId") String accountId,
                                                   @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return dropshipService.findAllDropshipOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize,orderBy("orderRef_desc")));

    }

    @RequestMapping(value = "/{appId}/dropshipOrders/byDate", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getDropshipOrdersByDate(@PathVariable("appId") String accountId,
                                                   @RequestParam(value = "date", required = false) String date,
                                                   @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return dropshipService.findAllDropshipOrdersByDate(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,getDate(date), getNextDate(getDate(date)),new PageRequest(page, pageSize));

    }


    private Sort orderBy(String sortBy) {
        String[] s=sortBy.split("_");
        if(s[1].equals("asc"))
            return new Sort(Sort.Direction.ASC, s[0]);
        else
            return new Sort(Sort.Direction.DESC,s[0]);
    }
}
