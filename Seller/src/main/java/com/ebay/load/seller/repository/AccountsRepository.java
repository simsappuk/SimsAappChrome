package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountsRepository extends JpaRepository<Accounts,String>
{
  //  @Query(value = "SELECT * FROM accounts", nativeQuery = true)
  Page<Accounts> findByOwnerIdPk(String ownerIdPk,Pageable p);

    @Query(value = "SELECT * FROM accounts where accounts_id=?",nativeQuery = true)
    Accounts findByAccountsId(String id);

    Accounts findByIdAndOwnerIdPk(String id, String ownerIdPk);

    Accounts findByAccountName(String accountName);

    Accounts findByAccountNameAndAccountType(String poundMonkey, String ebay);
}
