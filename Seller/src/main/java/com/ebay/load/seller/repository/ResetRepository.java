package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Reset;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ResetRepository extends JpaRepository<Reset,Long> {
    List<Reset> findByOwnerIdAndAccountIdAndListingHistoryNull(String id, String accountId);

    Reset findOneById(String id);

    Reset findByIdAndEbayItemIdAndAccountIdAndListingHistory(String id, String ebayItemId, String accountId,Boolean sold);

    List<Reset> findByOwnerIdAndAccountIdAndEbayItemIdAndListingHistory(String ownerId, String accountId, String ebayItemId,Boolean sold);

    @Query(value = "SELECT * from reset where owner_id=?1 and account_id=?2 and listing_history=?3 and last_effective_date between ?4 and ?5", nativeQuery = true)
    Page<Reset> findByOwnerIdAndAccountIdAndListingHistoryAndDate(String id, String accountId, boolean b, Date date, Date nextDate, Pageable p);

    Reset findByEbayItemIdAndAccountIdAndListingHistory(String itemId, String accountId, Boolean sold);
}
