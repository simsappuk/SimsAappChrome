package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>
{
    @Query(value = "SELECT a.* FROM stock a where account_id=?2 and a.id in( select stock_id from stock_sku where sku=?1) and last_effective_date is null and stock_delete is null and stock_code is null" , nativeQuery = true)
    Stock findBySKU(String sku,String accountId);

    @Query(value = "SELECT a.* FROM stock a where account_id=?2 and a.id in( select stock_id from stock_sku where sku=?1) and last_effective_date is null and stock_delete is null and stock_code is null" , nativeQuery = true)
    List<Stock> findBySkuList(String sku,String accountId);

    @Query(value = "SELECT a.* FROM stock a where  a.id in( select stock_id from stock_sku where sku in(select sku from stock_sku where stock_id=?1 )) and last_effective_date is null ", nativeQuery = true)
    Stock findByStockId(String id);

    @Query(value = "SELECT a.* FROM stock a where  a.id in( select stock_id from stock_sku where sku=?1) and order_ref_id =?2", nativeQuery = true)
    List<Stock> findBySKUAndOrder(String sku,String orderId);

    @Query(value = "SELECT a.* FROM stock a where  a.id in( select stock_id from stock_sku where sku in(select sku from stock_sku where stock_id=?1 )) order by last_effective_date desc nulls first and stock_code is null", nativeQuery = true)
    Page<Stock>  findHistoryByStockId(String id,Pageable p);

    @Query(value = "SELECT a.* FROM stock a where owner_id=?1 and account_id=?2 and last_effective_date is null and stock_delete is null and a.id in( select stock_id from stock_sku where sku=?3) and stock_code is null", nativeQuery = true)
    List<Stock> findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndSkuContainingIgnoreCase(String id,String accountId,String text);

    @Query(value = "SELECT a.* FROM stock a where owner_id=?1 and account_id=?2 and a.id in( select stock_id from stock_sku where sku=?3) and a.id in( select stock_id from stock_item_id where item_id=?4) and stock_delete is null and stock_code is null" , nativeQuery = true)
    Stock findByOwnerIdAndAccountIdAndStockDeleteNullAndSkuAndItemIdAndStockCodeNull(String ownerIdPk,String accountId, String sku,String itemID);

    @Query(value = "SELECT a.* FROM stock a where owner_id=?1 and account_id=?3 and a.id in( select stock_id from stock_item_id where item_id=?2) and stock_delete is null and stock_code is null" , nativeQuery = true)
    Stock findByOwnerIdAndItemIdAndAccountId(String ownerId, String itemId, String accountId);

    Page<Stock> findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndStockCodeNull(String ownerId,String accountId,Date d,String delete,Pageable p);
    List<Stock> findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndStockCodeNull(String ownerId,String accountId,Date d,String delete,Sort sort);

    List<Stock> findByOwnerIdAndAccountIdAndLastEffectiveDateAndStockDeleteAndTitleContainingIgnoreCaseAndStockCodeNull(String ownerId,String accountId,Date dt,String stockDelete,String text);

    Stock findOneById(String Id);

    Page<Stock> findByOwnerIdAndAccountIdAndStockDeleteAndStockLimitAndQuantityAvailableAfterAndQuantityAvailableBeforeAndStockCodeNull(String ownerId, String accountId, String stockDelete,Integer stockLimit,Integer quantity,Integer quantity2,Pageable p);

    List<Stock> findByOwnerIdAndAccountIdAndStockDeleteAndStockLimitNotNullAndStockCodeNull(String ownerId, String accountId, String stockDeleted);

    List<Stock> findByOwnerIdAndAccountIdAndTitleAndStockDeleteAndStockCodeNull(String id, String accountId, String name,String stockDelete);

    @Query(value = "SELECT a.* FROM stock a where account_id=?1 and a.id in( select stock_id from stock_ean where ean=?2) and stock_delete is null and stock_code is null" , nativeQuery = true)
    Stock findByAccountIdAndEanAndStockDeleteNullAndStockCodeNull(String accountId, String ean);

    Page<Stock> findByAccountIdAndStockCodeNotNullAndListingTypeNullAndStockDeleteNull(String accountId,Pageable p);

    List<Stock> findByAccountIdAndOrderRefIdAndStockCodeAndListingTypeNullAndStockDeleteNull(String accountId, String orderId, String facebook);

    List<Stock> findByListingTypeAndStockCodeAndStockDeleteNull(String listingType,String stockCode);

    Page<Stock> findByAccountIdAndStockCodeNotNullAndListingTypeNotNullAndStockDeleteNull(String accountId, Pageable p);

    List<Stock> findByOwnerIdAndAccountIdAndStockDeleteNullAndStockCodeNull(String id, String accountId);

    List<Stock> findByStockCodeAndListingTypeNotNullAndStockDeleteNull(String stockCode);
}
