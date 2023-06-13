package com.ebay.load.seller.service;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.repository.VintedListingRepository;
import com.ebay.load.seller.model.VintedListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class VintedListingService {

    VintedListingRepository vintedListingRepository;
    @Transactional
    public VintedListing save(VintedListing vintedListing, String accountId) {
        if (vintedListing.getId() == null) {
            vintedListing.setId(accountId);
            vintedListing.setStockId(vintedListing.getStockId());
            //VintedListing vintedListing1 = vintedListingRepository.save(vintedListing);
            return new VintedListing();
        } else if (vintedListing.getId() != null) {
            VintedListing s1 = updateVintedListing(vintedListing);
            //vintedListingRepository.save(s1);
        }
        return new VintedListing();
    }
    public VintedListing updateVintedListing(VintedListing s) {
//        s.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
//        s.setModifiedDate(new Date());
        VintedListing s2 = vintedListingRepository.findOneById(s.getId());

//        if (s2.getQuantityAvailable() != s.getQuantityAvailable()) {
//            s2.setLastEffectiveDate(new Date());
//            s2.setStockDelete("DELETED");
//            s2.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
//            s2.setModifiedDate(new Date());
//            s.setId(null);
//            s.setSku(cloneList(s2.getSku()));
//            /*
//                        if(s2.getSku().size()!=0){
//                            String sku=String.valueOf(s2.getSku()).replaceAll("[A-Za-z-+></]", "");
//                            s.setSkuNumber(Integer.valueOf(sku.substring(1,sku.length()-1)));
//                        }*/
//            stockRepository.save(s);

//        }
        return s;
    }
    public List<VintedListing> findAllById(String accountId, String action) {
        return vintedListingRepository.findAllById(accountId,action);
    }
}
