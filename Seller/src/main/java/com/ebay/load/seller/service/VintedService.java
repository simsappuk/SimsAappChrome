package com.ebay.load.seller.service;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class VintedService {
    @Autowired
    static
    VintedRepository vintedRepository;
    public Vinted postNewListing(String accountId, Vinted item) {
            Optional<Vinted> s = vintedRepository.findById(accountId);
            ApiContext apiContext = new ApiContext();
            ApiCredential cred = apiContext.getApiCredential();
            //cred.seteBayToken(s.getApiToken());
            //apiContext.setApiServerUrl(s.getUrl());
            apiContext.setSite(SiteCodeType.UK);
            return null;
    }
}
