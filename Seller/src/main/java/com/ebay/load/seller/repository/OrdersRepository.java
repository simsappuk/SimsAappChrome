package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Channel;
import com.ebay.load.seller.model.Orders;
import org.apache.xpath.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface OrdersRepository extends JpaRepository<Orders,Long> {
    Orders save(Orders orders);

    @Query(value = "SELECT * from orders where sku=?1", nativeQuery = true)
    Orders findBySKU(String sku);

    @Query(value = "SELECT * from orders where order_type=?1", nativeQuery = true)
    List<Orders> findAllWithOrderType(String OrderType);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndPaidDateNotNullAndShippedDateAndOrderDropshipStatusAndOrderStatusAndCheckResetOrderAndCheckDropshipOrder(String ownerId, String AccountId, String orderType, Date date, String POrderRef, Date shippedDate,String DropshipStatus,String orderStatus,Boolean resetOrder,Boolean dropshipOrder, Sort sort);

    List<Orders> findByOwnerIdPkAndChannelIdAndAccountId(String ownerIdPk, String channelId,String accountId,Sort p);

    List<Orders> findByOwnerIdPkAndAccountIdAndSpreadsheetId(String ownerIdPk,String accountId, String SpreadsheetId, Sort p);

    Orders findByIdAndOwnerIdPk(String id, String ownerIdPk);

    List<Orders> save(List<Orders> orders);

    @Query(value = "SELECT * from orders where order_type in :ids", nativeQuery = true)
    List<Orders> findWithOrderType(@Param("ids") ArrayList<String> orderType);

    List<Orders> findByLastEffectiveDateAndOrderType(Date LastEffectiveDate, String OrderType);

    Page<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRef(String ownerId, String accountId, String orderType, Date lastEffectiveDate, String pOrderRef, Pageable p);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRef(String ownerId, String accountId, String orderType, Date lastEffectiveDate, String pOrderRef, Sort sort);

    Page<Orders> findByAccountIdAndPOrderRefAndLastEffectiveDate(String accountId,Integer orderRef,Date lastEffectiveDate,Pageable p);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndPaidDate(String ownerId, String accountId, String orderType, Date lastEffectiveDate, String pOrderRef, Date paidDate, Sort sort);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndShippedDateNotNullAndPaidDateNotNull(String ownerId, String accountId, String orderType, Date lastEffectiveDate, String pOrderRef, Sort sort);

    Orders findByOwnerIdPkAndAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(String ownerId,String accountId,Integer orderRef,Integer pOrderRef,Date lastEffectiveDate);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndPOrderRefAndPaidDateNotNullAndShippedDateAndOrderStatus(String ownerId,String accountId,String OrderType,String pOrderRef,Date shippedDate,String orderStatus);

    @Query(value = "select * from orders where owner_id_pk=?1 and spreadsheet_id=?2 and channel_id=?3 and account_id=?5 and sheet_number=?4 Limit ?6 ", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndSheetNumberAndAccountId(String id, String spreadsheetId, String channelId,String sheetNumber,String accountId,int limit);

    @Query(value = "select * from orders where owner_id_pk=?1 and spreadsheet_id=?2 and channel_id=?3 and account_id=?4 and sheet_number is null Limit ?5 ", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountId(String ownerIdPk, String spreadsheetId, Channel channelId, String accountId, int limit);

    Orders findOneById(String s);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderTypeAndLastEffectiveDateAndPOrderRefAndOrderDropshipStatusNotNull(String ownerId, String accountId, String orderType, Date lastEffectiveDate, String pOrderRef, Sort sort);

    List<Orders> findByOwnerIdPkAndOrderTypeAndPOrderRefAndPaidDateNotNull(String ownerIdPk, String ebay, Integer orderRef);

    @Query(value = "SELECT * from orders where owner_id_pk=?1 and account_id=?2 and last_effective_date is null and p_order_ref is null and paid_date is not null and shipped_date is null and order_ref > ?3 and  order_ref<?4 and order_status=?5 and check_reset_order is null", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndAccountIdAndRecordNumRangeAndOrderStatus(String ownerIdPk, String accountId, Integer fromRecordNo, Integer toRecordNo,String orderStatus);

    List<Orders> findByPOrderRef(Integer orderRef);

    List<Orders> findByOrderIdAndOwnerIdPkAndAccountIdAndSellingRecordNumberAndBuyerName(String orderId, String ownerIdPk, String id, String sellingRecordNumber, String buyerName);

    List<Orders> findByAccountIdAndOrderStatusAndBuyerNameAndBuyerStreet1AndBuyerStreet2AndBuyerCityAndBuyerPostalCodeAndBuyerStateAndBuyerCountry(String id, String orderStatus, String buyerName, String buyerStreet1, String buyerStreet2, String buyerCity, String buyerPostalCode, String buyerState, String buyerCountry);

    Orders findByOwnerIdPkAndAccountIdAndOrderIdAndChannelIdAndOrderRef(String ownerId,String accountId,String OrderId,String channelId,String orderRef);

    @Query(value = "SELECT * from orders where owner_id_pk=?1 and account_id=?2 and shipped_date is null and p_order_ref is null and order_dropship_status is null and transaction_array_length > ?3 and order_status=?4", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndAccountIdAndTransactionArrayLengthAndShippedDateNullAndOrderDropshipStatusNullAndPOrderRefNullAndOrderStatus(String ownerId, String accountId, int i, String orderStatus);

    List<Orders> findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndPOrderRefAndLastEffectiveDateAndOrderStatus(String id, String accountId, String text, Object o, Object o1, String orderStatus);

    @Query(value = "SELECT * from orders where order_ref=?1 and p_order_ref is null", nativeQuery = true)
    Orders findByOrderRef(Integer orderRef);

    @Query(value = "SELECT * from orders where buyer_user_id=?1 and paid_date between ?2 and ?3 and p_order_ref is null", nativeQuery = true)
    List<Orders> findByBuyerUserIdAndPaidDate(String buyerUserId, Date startDate,Date todayDate);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderIdAndChannelId(String ownerIdPk, String id, String orderNumber, String s);

    List<Orders> findByExtendedOrderIdAndPOrderRef(String orderId,String pOrderRef);

    List<Orders> findByBuyerUserIdAndOrderStatus(String userId, String awaitingDispatch);

    List<Orders> findByExtendedOrderId(String extendedOrderId);

    Orders findBySellerUserIdAndExtendedOrderIdAndItemId(String seller, String orderNumber, String itemId);


    Orders findByOwnerIdPkAndSellingRecordNumber(String ownerId,String recordNum);

    List<Orders> findByOwnerIdPkAndAccountIdAndOrderType(String ownerId,String accountId,String amazon);

    Page<Orders> findByOwnerIdPkAndAccountIdAndExtendedOrderIdAndChannelIdAndPOrderRef(String id, String accountId, String text, String channelId,String pOrderRef,Pageable p);

    List<Orders> findByOwnerIdPkAndAccountIdAndChannelIdAndPOrderRefAndShippedDateNotNullAndPaidDateNotNull(String id, String accountId, String channelId,Integer pOrderRef);

    Page<Orders> findByAccountIdAndOrderRefAndPOrderRefAndLastEffectiveDate(String accountId, Integer orderRef, Integer pOrderRef,Date date, Pageable p);

    List<Orders> findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndChannelIdAndCategory(String ownerIdPk, String id, String title, String channelId, String category);

    List<Orders> findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndChannelId(String ownerId, String accountId, String title, String channelId);

    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndTitleContainingIgnoreCase(String id, String spreadsheetId, String channelId, String accountId, String data, Sort createdDate);

    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndExtendedOrderIdAndAccountId(String id, String spreadsheetId, String channelId, String data, String accountId, Sort createdDate);

    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndSku(String id, String spreadsheetId, String channelId, String accountId, String data, Sort createdDate);

    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndCategoryContainingIgnoreCase(String id, String spreadStringsheetId, String channelId, String accountId, String data, Sort createdDate);

    @Query(value = "SELECT * from orders where owner_id_pk=?1 and spreadsheet_id=?2 and channel_id=?3 and account_id=?4 and created_date between ?5 and ?6", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndCreatedDate(String id, String spreadsheetId, String channelId, String accountId, Date stringToDate, Date nextDate);

    List<Orders> findByOwnerIdPkAndChannelIdAndAccountIdAndSpreadsheetIdAndSheetNumberNotNull(String ownerId, String channelId, String accountId, String spreadsheetId);

    Orders findByOwnerIdPkAndExtendedOrderIdAndAccountId(String ownerIdPk, String extendedOrderId, String accountId);

    Orders findByOwnerIdPkAndExtendedOrderIdAndAccountIdAndChannelId(String ownerIdPk, String extendedOrderId, String accountId, String s);

    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndSellerUserId(String id, String spreadsheetId, String channelId, String accountId, String data);

    List<Orders> findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndBuyerStreet1(String id, String spreadsheetId, String channelId, String accountId, String data);

    @Query(value="SELECT * from orders where owner_id_pk=?1 and account_id=?2 and check_reset_order=?3 and order_status=?4 and paid_date between ?5 and ?6 and p_order_ref is null", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndAccountIdAndCheckResetOrderAndOrderStatusAndPaidDate(String ownerId, String accountId, boolean resetOrderOrNot,String orderStatus,Date date, Date nextDate);

    List<Orders> findByOwnerIdPkAndAccountIdAndPOrderRefAndCheckResetOrderAndOrderStatus(String ownerId, String accountId, Integer pOrderRef, Boolean resetOrder, String orderStatus, Sort sort);

    List<Orders> findByOwnerIdPkAndAccountIdAndPOrderRefAndCheckDropshipOrderAndCheckResetOrderAndOrderStatus(String ownerId, String accountId, Integer pOrderRef, Boolean dropshipOrder,Boolean resetOrder, String orderStatus, Sort sort);

    @Query(value = "SELECT * from orders where owner_id_pk=?1 and spreadsheet_id=?4 and channel_id=?2 and account_id=?3 and created_date between ?5 and ?6", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndChannelIdAndAccountIdAndSpreadsheetIdAndCreatedDate(String ownerId, String channelId, String accountId, String spreadsheetId, Date stringToDate, Date nextDate);

    @Query(value="SELECT * from orders where owner_id_pk=?1 and account_id=?2 and order_status=?3 and shipped_date between ?4 and ?5 and p_order_ref is null", nativeQuery = true)
    List<Orders> findByOwnerIdPkAndAccountIdAndOrderStatusAndShippedDate(String ownerId, String accountId, String dropshipped, Date date, Date nextDate);



    /*@Query(value = "SELECT * from orders where order_ref=?1 OR p_order_ref=?1", nativeQuery = true)
    List<Orders> findAllByOrderRef(Integer orderRef);*/
}


