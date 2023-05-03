package com.ebay.load.seller.repository;


import com.ebay.load.seller.model.ActiveListings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ActiveListingRepository extends JpaRepository<ActiveListings,String> {
    Page<ActiveListings> findByOwnerIdAndAccountId(String ownerId, String accountId, Pageable p);
    List<ActiveListings> findByOwnerIdAndAccountId(String ownerId, String accountId, Sort sort);
    @Transactional
    Long deleteByAccountId(String id);
    List<ActiveListings> findByOwnerIdAndAccountId(String ownerId, String id);
    ActiveListings findByIdAndOwnerIdAndAccountId(String Id,String ownerId,String accountId);
    ActiveListings findByOwnerIdAndAccountIdAndItemId(String ownerId, String accountId, String itemId);
    List<ActiveListings> findByOwnerIdAndAccountIdAndTitleContainingIgnoreCase(String ownerId, String accountId, String text);
}
