package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.Channel;

import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/channel")
public class ChannelController
{
    @Autowired
    private ChannelService channelService;

    @RequestMapping(value= "",method=RequestMethod.GET)
    public ResponseEntity<List<Channel>> getAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize)
    {
        return channelService.findAllPurchaseOrderChannels(new PageRequest(page.intValue(), pageSize.intValue()));

    }


    @RequestMapping(value= "/log",method=RequestMethod.GET)
    public ResponseEntity<List<Channel>> getLogChannels(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize)
    {
        return channelService.findAllLogChannels(new PageRequest(page.intValue(), pageSize.intValue()));

    }


}