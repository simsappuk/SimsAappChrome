package com.ebay.load.seller.repository;
import com.ebay.load.seller.model.Vinted;

import com.ebay.load.seller.model.VintedListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface VintedRepository extends JpaRepository<Vinted,String> {

    VintedListing findByIdAndOwnerIdAndAccountId(String Id, String ownerId, String accountId);

    Page<Vinted> findByOwnerIdAndAccountId(String ownerId, String accountId, Pageable p);


    @Query(
            value = "select coalesce(a.id, b.b) from\n" +
                    "(select cast ('0' as text) as b  )b left join \n" +
                    "(SELECT id FROM public.vinted where item_id=?1 limit 1 ) a on 1=1",
            nativeQuery = true)
    String findExistingByItemId(String itemId);
    @Query(
            value = "SELECT accounts_id FROM public.accounts where account_name= ?1",
            nativeQuery = true)
    String findItemIdByAccountId(String accountId);


    @Query(
            value = "SELECT * FROM vinted  WHERE item_Closing_Action = 'null' and account_id in( select accounts_id from accounts where account_name =?1  and  inactive = 'false' )",
            nativeQuery = true)

    List<Vinted> findAllActiveListing(String accountName);


    @Query(
            value = "SELECT * FROM vinted  WHERE item_Closing_Action = 'sold' and account_id in( select accounts_id from accounts where account_name =?1  and  inactive = 'false' )",
            nativeQuery = true)

    List<Vinted> findAllSoldListing(String accountName);

    @Query(
            value = "SELECT api_token FROM accounts where account_type='vinted' and accounts_id=?1",
            nativeQuery = true
    )
    String findApiToken(String Id);

    @Query(
            value = "SELECT accounts_id FROM public.accounts where account_name= ?1",
            nativeQuery = true)
    String findExistingAccountByAccountName(String itemId);
    @Query(
            value = "SELECT * FROM vinted where item_id=?1 and status=?2",
            nativeQuery = true)
    List<Vinted>  findByItemIdAndStatus(String accountId,String status);

    @Query(
          value = " SELECT * FROM vinted  WHERE item_Closing_Action = 'Dispatched' and account_id in( select accounts_id from accounts where account_name =?1  and  inactive = 'false' ) and  TO_DATE(created_at,'DD/MM/YYYY') BETWEEN ?2 AND ?3"  ,
            nativeQuery = true)
    List<Vinted>  findAllDispatchListing(String Id, Date start, Date end);
    @Query(
            value = "select * from vinted where item_id=?1",
            nativeQuery = true
    )
    List<Vinted> findByItemId(String accountId);
    @Query(
            value = "select * from vinted where item_id=?1",
            nativeQuery = true
    )
    List<Vinted> findByIdAndStockId(Optional<Vinted> id, String stockId);

    @Query(
            value = "delete from vinted where item_id=?1 and id=?2",
            nativeQuery = true
    )
    List<Vinted> deleteStockById(String accountId,String id);

    @Query(
            value="select * from vinted where account_id=?1",
            nativeQuery = true
    )
    Vinted findByAccountId(String accountId);
    @Query(
            value="select * FROM vinted where account_id= ?1 and status=?2",
            nativeQuery = true
    )
    List<Vinted> findByAccountIdAndStatus(String accountId,String status);

    @Query(
            value="select accounts.accounts_id from accounts where account_name=?1",
            nativeQuery = true
    )
    String findIdByAccountId(String accountId);


    Optional<Vinted> findById(String s);


    @Query(
            value = "select * from vinted where item_id=?1",
            nativeQuery = true
    )
    String findByItemAccountId(String s);
    @Query(
            value = "select * from vinted where id=?1",
            nativeQuery = true
    )
    Vinted findByIds(String id);
    @Query(
            value = "select item_id from vinted where id=?1",
            nativeQuery = true
    )
    String findAccountIdById(String id);
    @Query(
            value = "select id from vinted where accounts_id=?1",
            nativeQuery = true
    )
    String findByIdAndAccountId(String id, String s);
}
