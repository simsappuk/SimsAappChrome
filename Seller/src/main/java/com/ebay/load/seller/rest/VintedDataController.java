package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.VintedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/vintedData")
public class VintedDataController {
    @Autowired
    VintedDataService vintedDataService;


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public  VintedData vintedNewData(@RequestBody VintedData vintedData, @PathVariable("id") String account_name) {
        return vintedDataService.save(vintedData,account_name);
    }
    @RequestMapping(value = "/{id}/{action}", method = RequestMethod.GET)
    public  VintedData vintedgetNewData(@PathVariable(value="id") String accountId,@PathVariable(value="action") String action) {
        return vintedDataService.findAllById(accountId,action);
    }
}
