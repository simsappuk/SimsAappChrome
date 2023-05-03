package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Spreadsheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpreadSheetRepository extends JpaRepository<Spreadsheet,Integer>
{
    Spreadsheet findByIdAndOwnerId(String spreadsheetId,String id);
    List<Spreadsheet> findByAccountId(String accountId);

    Spreadsheet findByIdAndOwnerIdAndAccountId(String id, String ownerId, String accountId);
}
