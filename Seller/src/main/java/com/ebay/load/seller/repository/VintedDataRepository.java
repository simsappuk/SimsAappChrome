package com.ebay.load.seller.repository;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Dropship;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.model.VintedData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VintedDataRepository extends JpaRepository<VintedData,String> {
    @Query(value = "select accounts_id from accounts where account_name=?",
            nativeQuery = true)
    String findByAccountName(String accountName);

    @Query(value = "select id from vinted_data;",
            nativeQuery = true)
    String findByPrimaryKey(String accountName);

    VintedData findOneById(Long Id);

    @Query(value = "SELECT * FROM vinted_data a where id=?1 and account_name=?2 and action=?3 and text=?4", nativeQuery = true)
    List<VintedData> findByIdAndAccountIdAndActionAndTextAndCreatedAtAndUpdateAt(String id, String account_id, String action, String text,Date createdAt,Date updatedAt);


    @Query(value = "select id from vinted_data where account_id=?1 and action=?2",
            nativeQuery = true)
    Long findById(String accountId,String action);
}
