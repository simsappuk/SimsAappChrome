package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SalesController {
    @Autowired
    OrdersServiceImpl ordersServiceImpl;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> loadCurrentRequest(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                           @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                           @RequestParam(value = "filter", defaultValue = "*", required = false) String q) {
        return ordersServiceImpl.findAll(new PageRequest(page.intValue(), pageSize.intValue(), new Sort("createdDate")));
    }
}
