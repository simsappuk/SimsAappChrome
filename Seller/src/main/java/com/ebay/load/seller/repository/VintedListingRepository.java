package com.ebay.load.seller.repository;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.data.jpa.repository.Query;
import com.ebay.load.seller.model.VintedListing;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface VintedListingRepository {
    VintedListing findOneById(String Id);
    @Query(value = "select id from vinted where account_id=?1", nativeQuery = true)
    String findById(String Id);
    List<VintedListing> findAllById(String listingType, String stockCode);
}
