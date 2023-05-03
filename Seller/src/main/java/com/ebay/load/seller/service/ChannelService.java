package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Channel;
import com.ebay.load.seller.repository.ChannelRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChannelService
{
@Autowired
    private ChannelRepository  channelRepository;

    public ResponseEntity<List<Channel>> findAllPurchaseOrderChannels(Pageable p) {
        Page<Channel> channel = channelRepository.findByLogNameNull(p);
        ResponseEntity<List<Channel>> res =  new ResponseEntity<List<Channel>>().withResults(channel.getContent()).withTotalElements(channel.getTotalElements()).withTotalPages(channel.getTotalPages());
        return res;
    }

    public ResponseEntity<List<Channel>> findAllLogChannels(Pageable p) {
        Page<Channel> channel = channelRepository.findByLogNameNotNull(p);
        ResponseEntity<List<Channel>> res =  new ResponseEntity<List<Channel>>().withResults(channel.getContent()).withTotalElements(channel.getTotalElements()).withTotalPages(channel.getTotalPages());
        return res;
    }

}
