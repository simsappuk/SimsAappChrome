package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Compete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CompeteRepository extends JpaRepository<Compete,Long> {
    List<Compete> findByIdAndEbayItemId(String id, String ebayItemId);
    Compete findOneById(String id);

    List<Compete> findByEbayItemId(String ebayItemId);

    List<Compete> findByOwnerIdAndAccountId(String id, String accountId);

    @Query(value = "SELECT a.* FROM compete a where account_id=?2 and a.id in( select compete_id from compete_compete_item_id where compete_item_id=?1)" , nativeQuery = true)
    List<Compete> findByCompeteItemIdList(String itemId,String accountId);
}
