package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stock")
public class StockController {

    @Autowired
    StockService stockService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public  ResponseEntity<Stock> createStock(@RequestParam(value ="accountId",defaultValue = "",required = false)String accountId,
                                              @RequestBody Stock stock) {
        return stockService.save(stock,accountId);
    }

    @RequestMapping(value = "saveSku", method = RequestMethod.POST)
    public  ResponseEntity<Stock> saveSKU(@RequestParam(value="stockId",defaultValue = "",required = false) String id,
                                          @RequestParam(value="sku",defaultValue = "",required = false) String sku,
                                          @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.saveSku(id,sku,accountId);
    }

    @RequestMapping(value="/setLimit",method=RequestMethod.POST)
    public ResponseEntity<List<Stock>> setStockLimit(@RequestParam(value="stockId",defaultValue = "",required = false) List<String> stockId,
                                                     @RequestParam(value="limit",defaultValue = "",required = false) Integer limitValue,
                                                     @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.setStockLimit(stockId,limitValue,accountId);
    }

    @RequestMapping(value = "setThirdPartyData/save/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<Stock> setThirdPartyData(@RequestBody List<Stock> stock,
                                                       @PathVariable("accountId") String accountId) {
        return stockService.savePurchaseOrdersToStock(accountId,stock);
    }


    @RequestMapping(value = "showThirdPartyData/console", method = RequestMethod.GET)
    public ResponseEntity<Stock> thirdPartyData(@RequestParam("accountId") String accountId) {
        return stockService.showAddedSourceData(accountId,new PageRequest(0,200) );
    }

    @RequestMapping(value = "listThirdPartyStock/ebay", method = RequestMethod.GET)
    public ResponseEntity<Stock> listThirdPartyData(@RequestParam("accountId") String accountId) {
        return stockService.showThirdPartyListedStock(accountId,new PageRequest(0,200) );
    }

    @RequestMapping(value = "updateThirdPartyStock/added", method = RequestMethod.GET)
    public ResponseEntity<Stock> updateThirdPartyData(@RequestParam("accountId") String accountId,
                                                      @RequestParam("orderRefId") String orderRefId,
                                                      @RequestParam("source") String source) {
        return stockService.getAddedSourceData(accountId,orderRefId,source);
    }


    @RequestMapping(value = "viewStockAdded/Modal", method = RequestMethod.GET)
    public ResponseEntity<Stock> individualStock(@RequestParam("accountId") String accountId,
                                                 @RequestParam("orderId") String orderId) {
        return stockService.getIndividualThirdPartyStock(accountId,orderId);
    }

    @RequestMapping(value = "checkThirdPartyData/console", method = RequestMethod.GET)
    public ResponseEntity<Stock> checkThirdPartyData(@RequestParam("accountId") String accountId,
                                                @RequestParam("orderId") String orderId) {
        return stockService.checkData(accountId,orderId,new PageRequest(0,200) );
    }

    @RequestMapping(value = "removeThirdPartyData/stock", method = RequestMethod.GET)
    public ResponseEntity<Stock> removeThirdPartyData(@RequestParam("accountId") String accountId,
                                                @RequestParam("id") List<String> stockId) {
        return stockService.removeData(accountId,stockId);
    }

    @RequestMapping(value = "remove/ListIt/stock", method = RequestMethod.GET)
    public ResponseEntity<Stock> removeListIt(@RequestParam("id") List<String> stockId) {
        return stockService.deleteEntirelyFromStock(stockId);
    }

    @RequestMapping(value = "StockInLimit/{id}",method=RequestMethod.GET)
    public ResponseEntity<List<Stock>> stockWithinLimit(@PathVariable("id") String id,
                                                      @RequestParam(value = "quantityAvailable") Integer quantity,
                                                      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                      @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize){
        return stockService.getStockWithInLimits(SessionUserInfo.getLoggedInUser().getUser().getId(),id,quantity,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @RequestMapping(value = "limitedStock/{id}",method=RequestMethod.GET)
    public ResponseEntity<List<Stock>> loadOutOfStock(@PathVariable("id") String id){
        return stockService.getLimitedStock(SessionUserInfo.getLoggedInUser().getUser().getId(),id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public  ResponseEntity<List<Stock>> loadCurrentRequest(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                           @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                           @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.findAll(new PageRequest(page.intValue(), pageSize.intValue(),new Sort("createdDate")),accountId);

    }
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public ResponseEntity<List<Stock>> getSearchItem(@RequestParam(value="q",defaultValue = "",required = false) String text,
                                                     @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.findSearchText(text,accountId);
    }

    @RequestMapping(value="/updateStockQuantity",method=RequestMethod.GET)
    public ResponseEntity<Stock> updateStockQuantity(@RequestParam(value="ean",defaultValue = "",required = false) String ean,
                                                     @RequestParam(value="quantity",defaultValue = "1",required = false) Integer quantity,
                                                     @RequestParam(value="condition",defaultValue = "New",required = false) String condition,
                                                     @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {

        return stockService.updateStockWithEan(ean,accountId,quantity,condition);
    }


    @RequestMapping(value="/sku",method=RequestMethod.GET)
    public ResponseEntity<List<Stock>> getSku(@RequestParam(value="sku",defaultValue = "",required = false) String sku,
                                              @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.checkSkuAvailability(sku,accountId);
    }

    @RequestMapping(value="/itemId/existence",method=RequestMethod.GET)
    public ResponseEntity<Stock> getItemId(@RequestParam(value="itemId",defaultValue = "",required = false) String itemID,
                                           @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.checkItemIdExistence(itemID,accountId);
    }

    @RequestMapping(value="/ean/existence",method=RequestMethod.GET)
    public ResponseEntity<Stock> getEan(@RequestParam(value="ean",defaultValue = "",required = false) String ean,
                                              @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {
        return stockService.checkEanExistence(ean,accountId);
    }

    @RequestMapping(value="/listings/database",method=RequestMethod.GET)
    public ResponseEntity<List<Stock>> getStock(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                @RequestParam(value="accountId",defaultValue = "",required = false) String accountId){
        return stockService.getListingsAndCheckWithStock(accountId, new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @RequestMapping(value="/addStock/automatic",method=RequestMethod.GET)
    public ResponseEntity<Stock> addStockAutomatically(@RequestParam(value="accountId",defaultValue = "",required = false) String accountId,
                                                       @RequestParam(value="sku",defaultValue = "",required = false) String sku,
                                                       @RequestParam(value="itemList",defaultValue = "",required = false) List<String> itemList){
        return stockService.getListingsAndAddToStock(accountId,itemList,sku);
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ResponseEntity<Stock>  getSingleStock(@PathVariable("id") String id) {
        return stockService.findStockById(id);
    }

    @RequestMapping(value="/delete/item/{id}/{accountId}",method=RequestMethod.POST)
    public ResponseEntity<List<Stock>> deleteItemFromStock(@PathVariable("id") List<String> id,
                                                           @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                           @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize,
                                                           @PathVariable(value="accountId") String accountId){
        return stockService.deleteFromStock(id,accountId,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @RequestMapping(value="/attach/item/toAwaitingDispatch/{id}/{accountId}",method=RequestMethod.GET)
    public ResponseEntity<List<Stock>> attachItemToAwaitingDispatch(@PathVariable("id") List<String> id,
                                                           @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                           @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize,
                                                           @PathVariable(value="accountId") String accountId){
        return stockService.attachToAwaitingDispatch(id,accountId,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @ResponseBody
    @RequestMapping(value = "stock-history/{id}", method = RequestMethod.GET)
    ResponseEntity<List<Stock>>  getStockHistory(@PathVariable("id") String id,
                                                 @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                 @RequestParam(value = "filter", defaultValue = "*", required = false) String q) {
        return stockService.findStockHistoryById(id,new PageRequest(page.intValue(), pageSize.intValue()));
    }

    @RequestMapping(value = "/sorting",method = RequestMethod.GET)
    ResponseEntity<List<Stock>> getSortedList(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "quantity", required = false) String sortBy,
            @RequestParam(value="accountId",defaultValue = "",required = false) String accountId){
        return stockService.getSortedStockData(new PageRequest(page.intValue(),pageSize.intValue(),orderBy(sortBy)),accountId);
    }

    private Sort orderBy( String sortBy) {
        String[] s=sortBy.split("_");
        if(s[1].equals("asc"))
            return new Sort(Sort.Direction.ASC, s[0]);
        else
            return new Sort(Sort.Direction.DESC,s[0]);
    }

    @RequestMapping(value="/updateAddedStock/thirdParty",method=RequestMethod.POST)
    public ResponseEntity<Stock> updateAddedStock(@RequestBody Stock stock,
                                                  @RequestParam(value = "position", defaultValue = "", required = false) String position,
                                                  @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId) {

        return stockService.updateThirdPartyStock(stock,accountId,position);
    }



    @RequestMapping(value = "entireData/delete/{id}",method= RequestMethod.GET)
    ResponseEntity<List<Stock>> deleteEntireData(@PathVariable(value="id") String accountId){
        return stockService.deleteEntireStockDataByAccount(new PageRequest(0,200),accountId);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value="getFacebookList/number",method=RequestMethod.GET)
    public Integer getListNumber(){
        return stockService.getStockWaitingToListNumber();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value="getFacebookList/number/total",method=RequestMethod.GET)
    public Integer getTotalListNumber(){
        return stockService.getTotalStockListItNumber();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value="getEbayList/number/total",method=RequestMethod.GET)
    public Integer getEBayListNumber(){
        return stockService.getTotalEbayStockListItNumber();
    }

    @RequestMapping(value = "listFacebookItem/save", method = RequestMethod.POST)
    public ResponseEntity<Stock> listFacebookItem(@RequestParam("accountId") String accountId,
                                                  @RequestParam("id") List<String> stockId) {
        return stockService.listFacebookItem(accountId,stockId);
    }

        @RequestMapping(value = "thirdPartyItemToRevise/revise/", method = RequestMethod.POST)
    public ResponseEntity<Stock> thirdPartyItemToRevise(@RequestBody Stock stock) {
        if(stock.getId()!=null) stock.setId(null);
        return stockService.saveThirdPartyItemToRevise(stock);
    }



    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "revise/facebookItem", method = RequestMethod.GET)
    public String reviseFacebookItem(@RequestParam("itemId") String itemId) {
        return stockService.ListItFacebookItem(itemId);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "revise/eBayItem", method = RequestMethod.GET)
    public String reviseEbayItem(@RequestParam("itemId") String itemId) {
        return stockService.ListItemToEbay(itemId);
    }

    @RequestMapping(value = "remove/duplicates", method = RequestMethod.POST)
    public ResponseEntity<String> removeDuplicates(@RequestParam("accountId") String accountId) {
        return stockService.setItemIdToStock(accountId);
    }

    @RequestMapping(value = "revise/ListItem/manually/", method = RequestMethod.POST)
    public String reviseListItemManually(@RequestBody Stock stock) {
        return stockService.reviseNewListItem(stock, stock.getItemId().get(0));
    }


}
