package com.ebay.load.seller.repository;


import com.ebay.load.seller.model.ActivityLog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {
    ActivityLog findByIdAndOwnerId(String id, String ownerId);

    ActivityLog findByAccountIdAndSellerOrderId(String id, String extendedOrderId);

    List<ActivityLog> findByOwnerIdAndAccountIdAndSpreadsheetIdAndChannelId(String ownerId, String accountId, String spreadsheetId, String channelId, Sort sort);

    ActivityLog findOneById(String logId);

    ActivityLog findByOwnerIdAndAccountIdAndItemNumber(String ownerId, String accountId, String newItemId);

    List<ActivityLog> findBySheetNumber(String gdrive);

    ActivityLog findBySpreadsheetIdAndSheetNumber(String spreadsheetId, String gdrive);
}