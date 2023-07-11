package com.ebay.load.seller.service;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.repository.VintedListingRepository;
import com.ebay.load.seller.model.VintedListing;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class VintedListingService {
    @Autowired
    VintedRepository vintedRepository;


}
