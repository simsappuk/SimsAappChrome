package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayOrders;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Replacements;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.AmazonService;
import com.ebay.load.seller.service.EbayService;
import com.ebay.load.seller.service.LabelUpdateService;
import com.ebay.soap.eBLBaseComponents.OrderStatusCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/awd")
public class AwaitingDispatchController {

    @Autowired
    EbayService ebayService;

    @Autowired
    AmazonService amazonService;

    @Autowired
    LabelUpdateService labelUpdateService;


    @RequestMapping(value = "/{appId}/list", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> loadCurrentRequest(@PathVariable("appId") String appId, @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                           @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                           @RequestParam(value = "filter", defaultValue = "*", required = false) String q,
                                                           @RequestParam(value = "date", required = false) String d1,
                                                           @RequestParam(value = "dateTo", required = false) String d2,
                                                           @RequestParam(value = "type",defaultValue = "COMPLETED", required = false) OrderStatusCodeType statusType) throws ParseException {

        Date dp1 ;
        Date dp2;
        Date dn=new Date();

        if (!d1.equals("NaN-NaN-NaN")) dp1 = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
        else { dp1 = removeTime(dn);}
        if(!d2.equals("NaN-NaN-NaN")) dp2=new SimpleDateFormat("yyyy-MM-dd").parse(d2);
        else {
            if(dp1.equals(removeTime(dn)))
            dp2=dn;
        else
            dp2=getNextDate(dp1);}
        return ebayService.refreshAllOrdersByOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId(),appId,new PageRequest(page, pageSize),dp1,dp2);

    }

    @RequestMapping(value="/orders/list",method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> loadEbayOrdersFromDB(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                          @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize){
        return ebayService.ebayOrdersFromDB();
    }


    @ResponseBody
    @RequestMapping(value = "{appId}/{orderId}/{id}", method = RequestMethod.GET)
    ResponseEntity<EbayOrders> getSingleStock(@PathVariable("id") String id, @PathVariable("appId") String accountId, @PathVariable("orderId") String orderId) {
        return ebayService.findStockThroughId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,orderId,id);
    }
    @ResponseBody
    @RequestMapping(value = "/{appId}", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getOrders(@PathVariable("appId") String accountId,
                                             @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.findAllOrdersByOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/amazon/{appId}", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAmazonOrders(@PathVariable("appId") String accountId,
                                                 @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) throws InvalidKeyException, NoSuchAlgorithmException, JSONException, IOException {
        return amazonService.getAmazonOrders(accountId,new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/amazon/{appId}/refresh", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> refreshAmazonOrders(@PathVariable("appId") String accountId,
                                                 @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) throws InvalidKeyException, NoSuchAlgorithmException, JSONException, IOException {
        return amazonService.getOrderNumbers(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/{appId}/postage", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAllOrders(@PathVariable("appId") String accountId,
                                             @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.getAllIndividualOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/{appId}/label/Updates", method = RequestMethod.POST)
    ResponseEntity<List<Orders>> labelUpdate(@PathVariable("appId") String accountId,
                                              @RequestParam(value = "initialRecordNumber", defaultValue = "0", required = false) String initialRecordNum,
                                              @RequestParam(value = "finalRecordNumber", defaultValue = "20", required = false) String finalRecordNum,
                                              @RequestParam(value = "selection", defaultValue = "20", required = false) String selected,
                                             @RequestParam(value = "range", defaultValue = "20", required = false) List<String> range) {

        return labelUpdateService.savePrintedData(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,initialRecordNum,finalRecordNum,selected,range);

    }
    @RequestMapping(value = "/{appId}/label/print/data", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> printedData(@PathVariable("appId") String accountId){
        return labelUpdateService.getPrintedData(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,null,null,null);

    }

    @RequestMapping(value = "/label/printedData/date", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> printedDataOnDate(@RequestParam(value = "accountId", defaultValue = "0", required = false) String accountId,
                                                   @RequestParam(value = "date", defaultValue = "0", required = false) String date) throws ParseException {
        Date dp=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return labelUpdateService.getPrintedData(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,dp,null,"date");

    }

    @RequestMapping(value = "/label/printedData/record", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> printedDataByLabel(@RequestParam(value = "accountId", defaultValue = "0", required = false) String accountId,
                                                   @RequestParam(value = "recordNum", defaultValue = "0", required = false) String recordNumber){
        return labelUpdateService.getPrintedData(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,null,recordNumber,"label");

    }

    @RequestMapping(value = "/label/getReplacements", method = RequestMethod.GET)
    ResponseEntity<List<Replacements>> replacementsData(){
        return labelUpdateService.getReplacements();

    }
    @RequestMapping(value = "/label/saveReplacements", method = RequestMethod.POST)
    ResponseEntity<Replacements> saveReplacementsData(@RequestBody Replacements replacements){
        return labelUpdateService.saveReplacements(replacements);

    }

    @RequestMapping(value = "/{appId}/awaitingDispatch", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAllAwaitingDispatchOrders(@PathVariable("appId") String accountId,
                                             @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

            return ebayService.findAllAwaitingDispatchOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/{appId}/postage/pickingList",method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getOrdersIncludingBundlePurchase(@PathVariable("appId")String accountId,
            @RequestParam(value="from",required = false) Integer fromRecordNo,
                                                                  @RequestParam(value = "to",required = false)Integer toRecordNo){
        return ebayService.getOrdersIncludingBundles(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,fromRecordNo,toRecordNo);
    }

    @RequestMapping(value = "/{appId}/awaitingPayment", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAllAwaitingPaymentOrders(@PathVariable("appId") String accountId,
                                                              @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                              @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.findAllAwaitingPaymentOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));

    }


    @RequestMapping(value = "/{appId}/paidAndDispatched", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAllPaidAndDispatchedOrders(@PathVariable("appId") String accountId,
                                                              @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                              @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.findAllPaidAndDispatchedOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/{appId}/resetListing/orders", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAllResetListingOrders(@PathVariable("appId") String accountId,
                                                          @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                          @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.findAllResetListingOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/{appId}/resetListing/date/orders", method = RequestMethod.GET)
    ResponseEntity<List<Orders>> getAllResetListingOrdersByDate(@PathVariable("appId") String accountId,
                                                                @RequestParam(value = "date", required = false) String date,
                                                                @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.findAllResetListingOrdersByDate(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,getDate(date), getNextDate(getDate(date)),new PageRequest(page, pageSize));

    }

    @RequestMapping(value = "/orderContent/{obj}",method=RequestMethod.GET)
    ResponseEntity<List<Orders>> orderContent(@PathVariable(value = "obj") Integer orderRef,
                                              @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                              @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                              @RequestParam(value = "accountId", defaultValue = "*", required = false) String accountId){

        return ebayService.orderContent(orderRef,accountId,new PageRequest(page,pageSize));
    }

    @RequestMapping(value = "/extraLabel/{obj}",method=RequestMethod.GET)
    ResponseEntity<List<Orders>> extraLabel(@PathVariable(value = "obj") String orderRef,
                                              @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                              @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                              @RequestParam(value = "accountId", defaultValue = "*", required = false) String accountId){
        return ebayService.extraLabel(orderRef,accountId,new PageRequest(page,pageSize));
    }

    @RequestMapping(value = "/dropshipPurchaseOrder",method=RequestMethod.GET)
    ResponseEntity<List<Orders>> dropshipPurchaseOrder(@RequestParam(value = "orderId", defaultValue = "*", required = false) String orderId){
        return ebayService.purchaseOrderContent(orderId);
    }

    @RequestMapping(value = "/getOrderDetails",method=RequestMethod.GET)
    ResponseEntity<List<Orders>> getOrderDetails(@RequestParam(value = "orderId", defaultValue = "*", required = false) String orderId){
        return ebayService.getOrderDetails(orderId);
    }

    @RequestMapping(value = "/purchaseOrderContentByUserId",method=RequestMethod.GET)
    ResponseEntity<List<String>> purchaseOrderContentByUserId(@RequestParam(value = "userId", defaultValue = "*", required = false) String userId){
        return ebayService.purchaseOrderContentByUserId(userId);
    }

    @RequestMapping(value="/search",method=RequestMethod.GET)
    public ResponseEntity<List<Orders>> getSearchItem(@RequestParam(value="id",defaultValue = "",required = false)String accountId,
                                                      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                      @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                      @RequestParam(value="q",defaultValue = "",required = false) String text){
        return ebayService.findSearchText(text,accountId,new PageRequest(page,pageSize));
    }

    @RequestMapping(value="/search/paidAndDispatched",method=RequestMethod.GET)
    public ResponseEntity<List<Orders>> getSearch(@RequestParam(value="id",defaultValue = "",required = false)String accountId,
                                                      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                      @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                      @RequestParam(value="q",defaultValue = "",required = false) String text){
        return ebayService.findItemFromPaidAndDispatched(text,accountId,new PageRequest(page,pageSize));
    }

    @RequestMapping(value = "/sorting", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> loadCurrentRequest(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                   @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                                   @RequestParam(value = "accountId", defaultValue = "*", required = false) String accountId,
                                                                   @RequestParam(value = "sortBy",defaultValue = "quantity",required = false) String sortBy) {


        if(sortBy.startsWith("dispatch"))
            return ebayService.sortAwaitingDispatchOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId, new PageRequest(page, pageSize,orderVaryBy(sortBy)));

        else if(sortBy.startsWith("payment"))
            return ebayService.sortAwaitingPaymentOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId, new PageRequest(page, pageSize,orderVaryBy(sortBy)));

        else if(sortBy.startsWith("paid"))
            return ebayService.sortPaidAndDispatchedOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId, new PageRequest(page, pageSize,orderVaryBy(sortBy)));

        else
            return ebayService.sortOrders(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId, new PageRequest(page, pageSize,orderBy(sortBy)));
    }

    private Sort orderBy(String sortBy) {
        String[] s=sortBy.split("_");
        if(s[1].equals("asc"))
            return new Sort(Sort.Direction.ASC, s[0]);
        else
            return new Sort(Sort.Direction.DESC,s[0]);
    }

    private Sort orderVaryBy(String sortBy) {
        String[] s=sortBy.split("_");
        if(s[2].equals("asc"))
            return new Sort(Sort.Direction.ASC, s[1]);
        else
            return new Sort(Sort.Direction.DESC,s[1]);
    }
    @RequestMapping(value = "/{appId}/online/acct/refresh", method = RequestMethod.GET)
    ResponseEntity<List<Orders>>refreshStock(@PathVariable("appId") String accountId,
                                             @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        Date d1 =new Date();


        ResponseEntity<List<Orders>> s =  null;
       // s = ebayService.refreshAllOrdersByOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,new PageRequest(page, pageSize),removeTime(new Date()),new Date());
        Date d2 = new Date();
        long seconds = (d2.getTime()-d1.getTime())/1000;
        System.out.println(seconds);
return s;
    }
    @RequestMapping(value = "/{appId}/orders/{orderId}", method = RequestMethod.GET)
    OrderType[] getOrders(@PathVariable("appId") String accountId,@PathVariable("orderId") String orderId,
                                             @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return ebayService.getOrdersByOrderId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,orderId);

    }

    @RequestMapping(value="/toAwaitingDispatch/order/{id}",method=RequestMethod.POST)
    public ResponseEntity<List<Orders>> dropshipItem(@PathVariable("id") List<String> id,
                                                     @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                     @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize) throws ParseException {
        return ebayService.MarkOrderAsAwaitingDispath(id,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    @RequestMapping(value="/orders/resetListing/revert/{id}",method=RequestMethod.POST)
    public ResponseEntity<List<Orders>> undoResetOrder(@PathVariable("id") List<String> id,
                                                     @RequestParam(value="page",defaultValue ="0",required = false) Integer page,
                                                     @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize) throws ParseException {
        return ebayService.MarkResetOrderAsAwaitingDispatch(id,new PageRequest(page.intValue(),pageSize.intValue()));
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getNextDate(Date passingDate) {
        if(removeTime(new Date()).equals(passingDate))
            return new Date();
        else {
            Calendar c = Calendar.getInstance();
            c.setTime(passingDate);
            c.add(Calendar.DAY_OF_YEAR, 1);
            return removeTime(c.getTime());
        }
    }


    public static Date getDate(String myDate){
try {
    Date  sd = new SimpleDateFormat("yyyy-MM-dd").parse(myDate);
    return  sd;
}catch(Exception e){}
return new Date();
    }
}
