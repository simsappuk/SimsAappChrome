package com.ebay.load.seller.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ebay.load.seller.model.VintedListing;

import java.util.List;
import java.util.Optional;

public interface VintedListingRepository extends JpaRepository<VintedListing,String> {
    VintedListing findOneById(String Id);
    @Query(value = "select id from vinted where account_id=?1", nativeQuery = true)
    Optional<VintedListing> findById(String Id);


}
