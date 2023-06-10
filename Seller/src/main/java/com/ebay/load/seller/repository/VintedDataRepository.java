package com.ebay.load.seller.repository;
import com.ebay.load.seller.model.VintedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VintedDataRepository extends JpaRepository<VintedData,String> {
    @Query(value = "select accounts_id from accounts where account_name=?1",
            nativeQuery = true)
    String findByAccountName(String accountName);


    @Query(value = "select id from vinted_data where account_id=?1 and action=?2",
            nativeQuery = true)
    String findByIdAct(String accountId,String action);
    @Query(value = "select * from vinted_data where account_id=?1 and action=?2",
            nativeQuery = true)
    VintedData findByIdaa(String accountId,String action);


    Optional<VintedData> findById(Long id);
}
