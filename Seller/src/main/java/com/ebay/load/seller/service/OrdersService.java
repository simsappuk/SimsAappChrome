package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Spreadsheet;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.util.List;

public interface OrdersService
{
   public List<Orders> save(List<Orders> orders,String spreadsheetId,String channelId,String accountId,String sheetNumber);

   public ResponseEntity<List<Orders>> findAllWithOwnerId(String ownerIdPk,String channelId,String accountId,Sort p);

   public ResponseEntity<List<Orders>> findAllWithSpreadsheetId(String ownerIdPk,String SpreadsheetId,String channelId,String sheetNumber,String accountId);

   public ResponseEntity<Orders>findSingleIdforEdit(String ownerIdPk,String id);

   public ResponseEntity<String>findSingleIdForDelete(String id,String ownerIdPk);

   public ResponseEntity<Spreadsheet> newSheetNumber(String ownerId, String spreadsheetId, String channelId,String accountId);

   ResponseEntity<List<Orders>> findAllSheetNumbers(String id, String spreadsheetId, String channelId,String accountId);

   ResponseEntity<List<Orders>> loadSheet(String id, String spreadsheetId, String channelId,String sheetNumber,String accountId);

   ResponseEntity<List<Orders>> getPurchasedSearchData(String id, String spreadsheetId, String channelId,String selection,String accountId,String data);

   ResponseEntity<String> refreshPurchaseOrders(PageRequest pageRequest, String accountId, String id) throws ParseException;

   ResponseEntity<List<Orders>> getChannelData(String channel, String accountId);

    ResponseEntity<Orders> getSavedData(String name, String value, String accountId, String channelId, String spreadsheetId, String id, String rowId);

   ResponseEntity<Orders> saveCopyPasteData(String accountId, String channelId, String spreadsheetId, String id, String rowId, Orders orders);

   ResponseEntity<List<Orders>> getInvoicePdfByDate(String id, String channelId, String accountId, String spreadsheetId, String data);
}
