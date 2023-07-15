package com.ebay.load.seller.repository;
import com.ebay.load.seller.model.ActiveListings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ebay.load.seller.model.VintedListing;

import java.util.List;
import java.util.Optional;

public interface VintedListingRepository extends JpaRepository<VintedListing,String> {
    VintedListing findOneById(String Id);
    @Query(value = "select * from vinted where item_id=?1 and status=?2", nativeQuery = true)
    List<VintedListing> findByStockIdAndStatus(String stockId ,String status);
    @Query(value = "select * from vinted_listing where account_id=?1", nativeQuery = true)
    List<VintedListing> findByAccountId(String itemId);


    @Query(value = "select status from vinted where account_id=?1", nativeQuery = true)
    Optional<VintedListing> findByItemId(String Id);
    @Query(value = "select * from vinted_listing where item_id=?1", nativeQuery = true)
    VintedListing findByStockId(String itmeId);

    @Query(value = "select * from vinted_listing where item_id=?1", nativeQuery = true)
    VintedListing findByStockId(String accountId,String status);




}
