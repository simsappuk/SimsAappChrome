package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.LabelUpdates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LabelUpdatesRepository extends JpaRepository<LabelUpdates,String> {
    List<LabelUpdates> findByOwnerIdAndAccountIdAndLastEffectiveDate(String id, String accountId, Date date);
    @Query(value = "SELECT a.* FROM label_updates a where account_id=?2 and a.id in( select label_updates_id from label_updates_range where range=?3) and owner_id=?1" , nativeQuery = true)
    List<LabelUpdates> findByOwnerIdAndAccountIdAndRange(String id, String accountId,String range);
}
