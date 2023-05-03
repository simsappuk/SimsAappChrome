package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Roles;
import com.ebay.load.seller.model.Vendors;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Vendors entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorsRepository extends JpaRepository<Vendors, Long> {
    Vendors findByNameAndChannelId(String name,String channelId);

    Vendors findByNameAndOrderIdAndChannelId(String sellerUserId, String orderId, String channelId);
}
