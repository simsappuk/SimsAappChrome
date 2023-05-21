package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.repository.VintedDataRepository;
import com.ebay.load.seller.repository.VintedDataRepository;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.VintedDataService;
import com.ebay.load.seller.service.VintedDataService;
import com.ebay.soap.eBLBaseComponents.ItemType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping(value = "/vintedData")
public class VintedDataController {
    @Autowired
    VintedDataService vintedDataService;


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public  VintedData vintedNewData(@RequestBody VintedData vintedData, @PathVariable("id") String account_name) {
        VintedData vintedData1= vintedDataService.save(vintedData,account_name);
        return vintedData1;
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public  VintedData vintedDataList(@RequestBody VintedData vintedData) {
        VintedData vintedData1= vintedDataService.save(vintedData,vintedData.accountId);
        return vintedData1;
    }
}
