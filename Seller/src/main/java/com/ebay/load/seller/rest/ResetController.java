package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Reset;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/reset")
public class ResetController {
    @Autowired
    ResetService resetService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Reset> saveResetData(@RequestBody Reset reset,
                                                 @RequestParam(value = "accountId")String accountId){
        return  resetService.save(reset,accountId,SessionUserInfo.getLoggedInUser().getUser().getId());
    }

    @RequestMapping(value = "/getItemDetails",method= RequestMethod.GET)
    public ResponseEntity<Reset> getDetails(@RequestParam(value = "itemId")String itemId,
                                            @RequestParam(value = "accountId")String accountId
                                            ){
        return resetService.getPriceAndQuantity(itemId,accountId, SessionUserInfo.getLoggedInUser().getUser().getId());
    }

    @RequestMapping(value = "/getData",method= RequestMethod.GET)
    public ResponseEntity<Reset> getData(@RequestParam(value = "accountId")String accountId){
        return resetService.getContent(accountId);
    }

    @RequestMapping(value = "/getCurrentDayhistory/data",method= RequestMethod.GET)
    public ResponseEntity<Reset> getCurrentDatehistory(@RequestParam(value = "accountId")String accountId,
                                         @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                         @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize){
        return resetService.getCurrentHistory(accountId,new PageRequest(page,pageSize));
    }


    @RequestMapping(value = "/getHistory/byDate",method= RequestMethod.GET)
    public ResponseEntity<Reset> getDateHistory(@RequestParam(value = "accountId")String accountId,
                                                       @RequestParam(value = "date", required = false) String date,
                                                       @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                       @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize){
        return resetService.getHistoryByDate(accountId,getDate(date),new PageRequest(page,pageSize));
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    public ResponseEntity<Reset> getItemData(@PathVariable("id") String id){
        return resetService.getItemData(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<Reset> updateResetData(@RequestBody Reset reset,@RequestParam String accountId){
        return  resetService.update(reset,accountId);
    }

    @RequestMapping(value = "/update/adRate",method = RequestMethod.POST)
    public ResponseEntity<String> updateAdRatePercent(@RequestBody Reset reset,@RequestParam String accountId){
        return resetService.updateAdRate(reset,accountId);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseEntity<Reset> deleteResetData(@RequestBody Reset reset){
        return  resetService.delete(reset);
    }

    public static Date getDate(String myDate){
        try {
            Date  sd = new SimpleDateFormat("yyyy-MM-dd").parse(myDate);
            return  sd;
        }catch(Exception e){}
        return new Date();
    }

    @RequestMapping(value="/SetOrderToReset/{id}",method=RequestMethod.POST)
    public ResponseEntity<List<Orders>> dropshipItem(@PathVariable("id") List<String> id,
                                                     @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                     @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize) throws ParseException {
        return resetService.MarkOrderAsReset(id,new PageRequest(page.intValue(),pageSize.intValue()));
    }

}
