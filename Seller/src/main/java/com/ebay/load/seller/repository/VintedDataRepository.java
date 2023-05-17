package com.ebay.load.seller.repository;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.VintedData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VintedDataRepository extends JpaRepository<VintedData,String> {
    @Query(
            value = "SELECT * FROM vinted_data  WHERE account_id =?1 ", nativeQuery = true)
    Page<VintedData> findByAccountId(String accountId, Pageable p);
    @Query(
            value = "SELECT * FROM vinted_data  WHERE text =?1 ", nativeQuery = true)
    List<VintedData> findAllActiveVintedDataListing(String text);

    @Query(
            value = "SELECT * FROM vinted_data  WHERE action =?1  ",nativeQuery = true)

    List<VintedData> findAllSoldVintedDataListing(String action);

    @Query(
            value = "SELECT api_token FROM accounts where account_type=?1",nativeQuery = true )
    String findApiVintedDataToken(String accountId);

    @Query(
            value = " SELECT * FROM vinted_data  WHERE account_id in( select accounts_id =?1  and  inactive = 'false' ) and  TO_DATE(created_at,'DD/MM/YYYY') BETWEEN ?2 AND ?3"  ,
            nativeQuery = true)
    List<VintedData>  findAllVintedDataListing(String accountId, Date createdDate, Date updatedDate);
}
