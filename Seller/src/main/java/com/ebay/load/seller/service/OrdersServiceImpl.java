package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.converter.EbayPurchaseOrdersConverter;
import com.ebay.load.seller.model.*;
import com.ebay.load.seller.repository.*;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetApiAccessRulesCall;
import com.ebay.sdk.call.GetMyeBayBuyingCall;
import com.ebay.soap.eBLBaseComponents.*;
//import org.apache.commons.lang3.StringUtils;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrdersServiceImpl  implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private VendorsRepository vendorsRepository;

    @Autowired
    private FoldersRepository foldersRepository;

    @Autowired
    private SpreadSheetRepository spreadSheetRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private EbayService ebayService;

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private EbayPurchaseOrdersConverter ebayPurchaseOrdersConverter;

    @Autowired
    private DropshipService dropshipService;

    @Autowired
    private ChannelRepository channelRepository;

    public Vendors getVendor(String name,String channelId){
        Vendors c = new Vendors();
        c.setChannelId(channelId);
        c.setName(name);
        return c;
    }

    public Channel getChannel(String id){
        Channel c = new Channel();
        c.setId(id);
        return c;
    }

    public boolean isExistingOrder(Orders orig,String ownerId){
        Orders s=null;
        if(orig.getId()!=null)
            s  = ordersRepository.findByIdAndOwnerIdPk(orig.getId(),ownerId);
        if(s!=null)
            return true;

        return false;
    }

    @Override
    public List<Orders> save(List<Orders> orders, String spreadsheetId,String channelId,String accountId,String sheetNumber) {
        Vendors vendors = null;
        for(int i=0;i<orders.size();i++) {
            Orders ord = orders.get(i);
            if (!isExistingOrder(ord,SessionUserInfo.getLoggedInUser().getUser().getId())) {
                if (ord.getVendors() != null)
                    vendors = vendorsRepository.findByNameAndChannelId(ord.getVendors().getName(), channelId);
                if (vendors == null && ord.getVendors() != null) {
                    vendors = vendorsRepository.saveAndFlush(getVendor(ord.getVendors().getName(), channelId));
                }
                ord.setVendors(vendors);
                ord.setChannel(getChannel(channelId));
            }
            if (ord.getCreatedDate() == null)
                ord.setCreatedDate(new Date());
            ord.setOwnerIdPk(SessionUserInfo.getLoggedInUser().getUser().getId());
            if(orders.get(0).getSpreadsheetId()==null) {
                ord.setSpreadsheetId(spreadsheetId);
                ord.setDuplicateId(spreadsheetId);
            }
            else {
                ord.setSpreadsheetId(orders.get(0).getSpreadsheetId());
                ord.setDuplicateId(orders.get(0).getDuplicateId());
            }
            if(sheetNumber!=null)
                ord.setSheetNumber(sheetNumber);
            ord.setAccountId(accountId);
            Orders s3=saveStock(ord,accountId);
            orders.set(i,s3);
        }
        return orders;
    }

    private Stock updateStockWithOrder(Orders ord) {
        Stock st=null;

        List<Stock> lstDb2 = stockService.getStockAndOrderId(ord.getSku(),ord.getId());
        if(lstDb2==null||lstDb2.size()==0) {
            Stock stDb = stockService.getStock(ord.getSku(),ord.getAccountId());

            if (stDb != null) {
                try {
                    st = (Stock) stDb.clone();
                    int origQuantity = st.getQuantityAvailable();
                    st.setOnlineQuantity(origQuantity + Integer.parseInt(ord.getQuantity()));
                    st.setQuantityAvailable(origQuantity + Integer.parseInt(ord.getQuantity()));
                    double origAvgPRice = st.getAvgPrice() != null ? st.getAvgPrice() : st.getBuyItNowPrice();
                    double newAverage = ((origQuantity * origAvgPRice) + (Integer.parseInt(ord.getQuantity()) * Integer.parseInt(ord.getPrice()))) / (origQuantity + Integer.parseInt(ord.getQuantity()));
                    st.setAvgPrice(newAverage);
                    st.setOrderRefId(ord.getId());
                    return st;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return st;
    }

    //Save the Purchase Orders and update the Stock with respect to it
    public Orders saveStock(Orders ord,String accountId){
        Orders o=ordersRepository.saveAndFlush(ord);
        if(ord.getSku()!=null) {
            Stock sk = updateStockWithOrder(ord);
            if (sk != null)
                stockService.save(sk,accountId);
        }
        return o;
    }

    @Override
    public ResponseEntity<List<Orders>> findAllWithOwnerId(String ownerIdPk,String channelId,String accountId,Sort p)
    {

        List<Orders> orders =ordersRepository.findByOwnerIdPkAndChannelIdAndAccountId(ownerIdPk,channelId,accountId,p );
        return new ResponseEntity<List<Orders>>().withResults(orders);//.withTotalPages(orders.getTotalPages()).withTotalElements(orders.getTotalElements());

    }

    @Override
    public ResponseEntity<List<Orders>> findAllWithSpreadsheetId(String ownerIdPk,String SpreadsheetId,String channelId,String sheetNumber,String accountId)
    {
        Channel channel=new Channel();
        channel.setId(channelId);
        /*List<Orders> check=ordersRepository.findAll();
        if(check.size()==0) {
            Orders o = new Orders();
            o.setAccountId(accountId);
            o.setSpreadsheetId(SpreadsheetId);
            o.setDuplicateId(SpreadsheetId);
            o.setOwnerIdPk(SessionUserInfo.getLoggedInUser().getUser().getId());
            ordersRepository.save(o);
        }*/
        if(sheetNumber.equals("undefined")) {
            sheetNumber = null;
            List<Orders> orders = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountId(ownerIdPk, SpreadsheetId, channel, accountId,20);
            return new ResponseEntity<List<Orders>>().withResults(orders);
        }
        else if(sheetNumber.contains("Sheet")){
            List<Orders> orders = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndSheetNumberAndAccountId(ownerIdPk, SpreadsheetId, channelId, sheetNumber,accountId,20);
            return new ResponseEntity<List<Orders>>().withResults(orders);
        }
        else{
            channel.setId(sheetNumber);
            List<Orders> orders=ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountId(ownerIdPk,SpreadsheetId,channel,accountId,20);
            return new ResponseEntity<List<Orders>>().withResults(orders);

        }
    }

    @Override
    public ResponseEntity<Orders>findSingleIdforEdit(String ownerIdPk,String id)
    {
        Orders orders1=ordersRepository.findByIdAndOwnerIdPk((id),ownerIdPk);
        return new ResponseEntity<Orders>().withResults(orders1);

    }

    public ResponseEntity<List<Orders>> getChannelData(String channelName,String accountId)
    { String channelId="22";
        if(channelName.equals("Game"))
            channelId="2";
        else if(channelName.equals("MusicMagpie"))
            channelId="3";
        else if(channelName.equals("Cex"))
            channelId="4";
        List<Orders> orders1=ordersRepository.findByOwnerIdPkAndChannelIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(),channelId,accountId,new Sort("createdDate"));
        return new ResponseEntity<List<Orders>>().withResults(orders1);

    }


    public ResponseEntity<String>findSingleIdForDelete(String id,String ownerIdPk)
    {

        Orders orders=ordersRepository.findByIdAndOwnerIdPk((id),ownerIdPk);
        if(orders!=null)
        {
            ordersRepository.delete(orders);
            return  new ResponseEntity<String>().withErrors(false);
        }
        return new ResponseEntity<String>().withErrors(true);
    }

    public Orders getOrders(String sku){
        try{
            Orders o=ordersRepository.findBySKU(sku);
            return o;
        }catch (Exception e){System.out.println(e.getMessage());}
        return null;
    }

    public ResponseEntity<List<Orders>> findAll(PageRequest createdDate) {
        List<Orders> o=ordersRepository.findAllWithOrderType("s");
        return new ResponseEntity<>().withResults(o);
    }

    public ResponseEntity<List<Orders>> findAllWithOrderType(PageRequest createdDate) {
        ArrayList<String> orderType=new ArrayList<String>();
        orderType.add("QNDB");
        orderType.add("UDB");
        orderType.add("Q");
        orderType.add("UV");
        List<Orders> o=ordersRepository.findWithOrderType(orderType);
        return new ResponseEntity<>().withResults(o);
    }

    public ResponseEntity<Spreadsheet> newSheetNumber(String ownerId, String spreadsheetId, String channelId,String accountId){
        List<Orders> list=ordersRepository.findByOwnerIdPkAndChannelIdAndAccountIdAndSpreadsheetIdAndSheetNumberNotNull(ownerId,channelId,accountId,spreadsheetId);
        return  generateSheet(spreadsheetId,ownerId,channelId,accountId,list);

    }

    @Override
    public ResponseEntity<List<Orders>> findAllSheetNumbers(String ownerId, String spreadsheetId, String channelId,String accountId) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndChannelIdAndAccountIdAndSpreadsheetIdAndSheetNumberNotNull(ownerId,channelId,accountId,spreadsheetId);
        List o=new ArrayList();
        for(int i=0;i<list.size();i++){
            String s=list.get(i).getSheetNumber();
            o.add(s);
        }
        List removeDuplicates=new ArrayList();
        for(int k=0;k<o.size();k++){
            if(!removeDuplicates.contains(o.get(k)))
                removeDuplicates.add(o.get(k));
        }
        return new ResponseEntity<List<Orders>>().withResults(removeDuplicates);
    }

    public ResponseEntity<List<Orders>> loadSheet(String id, String spreadsheetId, String channelId,String sheetNumber,String accountId) {
        Channel channel=new Channel();
        channel.setId(channelId);
        if(sheetNumber.contains("Sheet")) {
            channel.setId(sheetNumber);
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndSheetNumberAndAccountId(id, spreadsheetId, channelId, sheetNumber, accountId, 20);
            return new ResponseEntity<>().withResults(o);
        }
        else {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountId(id, spreadsheetId, channel, accountId,20);
            return new ResponseEntity<>().withResults(o);
        }


    }

    public ResponseEntity<List<Orders>> getPurchasedSearchData(String id, String spreadsheetId, String channelId,String selection,String accountId,String data) {
        if(selection.contains("orderNumber")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndExtendedOrderIdAndAccountId(id, spreadsheetId, channelId, data, accountId, new Sort(Sort.Direction.DESC, "createdDate"));
            return new ResponseEntity<>().withResults(o);
        }
        else if(selection.contains("title")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndTitleContainingIgnoreCase(id, spreadsheetId, channelId, accountId, data,new Sort(Sort.Direction.DESC, "createdDate"));

            return new ResponseEntity<>().withResults(o);
        }
        else if(selection.contains("sku")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndSku(id, spreadsheetId, channelId, accountId, data,new Sort(Sort.Direction.DESC, "createdDate"));
            return new ResponseEntity<>().withResults(o);
        }
        else if(selection.contains("category")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndCategoryContainingIgnoreCase(id, spreadsheetId, channelId, accountId, data,new Sort(Sort.Direction.DESC, "createdDate"));
            return new ResponseEntity<>().withResults(o);
        }
        else if(selection.contains("date")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndCreatedDate(id, spreadsheetId, channelId, accountId, stringToDate(data),ebayService.getNextDate(stringToDate(data)));
            return new ResponseEntity<>().withResults(o);
        }
        else if(selection.contains("user")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndSellerUserId(id, spreadsheetId, channelId, accountId,data);
            return new ResponseEntity<>().withResults(o);
        }
        else if(selection.contains("address")) {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndBuyerStreet1(id, spreadsheetId, channelId, accountId,data);
            return new ResponseEntity<>().withResults(o);
        }
        else  {
            List<Orders> o = ordersRepository.findByOwnerIdPkAndSpreadsheetIdAndChannelIdAndAccountIdAndCategoryContainingIgnoreCase(id, spreadsheetId, channelId, accountId, data,new Sort(Sort.Direction.DESC, "createdDate"));
            return new ResponseEntity<>().withResults(o);
        }

    }

    public ResponseEntity<List<Orders>> getInvoicePdfByDate(String ownerId, String channelId,String accountId,String spreadsheetId,String data) {
        List<Orders> list=ordersRepository.findByOwnerIdPkAndChannelIdAndAccountIdAndSpreadsheetIdAndCreatedDate(ownerId,channelId,accountId,spreadsheetId, stringToDate(data),ebayService.getNextDate(stringToDate(data)));
        return new ResponseEntity<>().withResults(list);
    }

    public Date stringToDate(String date)  {
        try {
            Date  sd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return sd;
        } catch (Exception e) {
            System.out.print("Error"+e.getMessage());
        }
        return null;
    }


    private ResponseEntity<Spreadsheet> generateSheet(String spreadsheetId, String ownerId, String channelId,String accountId, List<Orders> list) {
        Orders o = new Orders();
        o.setChannel(getChannel(channelId));
        o.setOwnerIdPk(ownerId);
        o.setAccountId(accountId);
        o.setDuplicateId(spreadsheetId);
        if(list==null||list.size()==0)
        o.setSheetNumber("Sheet1");
        else
            o.setSheetNumber("Sheet"+String.valueOf(Integer.parseInt(getSheetNumber(list))+1));
        Spreadsheet s=new Spreadsheet();
        s.setOwnerId(ownerId);
        s.setAccountId(accountId);
        s.setDuplicateId(spreadsheetId);
        s.setChannelId(channelId);
        s.setChannelName(getChannelName(channelId));
        s.setCreatedDate(new Date());
        s.setSheetNumber(o.getSheetNumber());
        spreadSheetRepository.save(s);
        o.setSpreadsheetId(s.getId());
        ordersRepository.save(o);

        return s;
    }

    private String getChannelName(String channelId) {
        Optional<Channel> channel=channelRepository.findById(channelId);
        return channel.get().getName();
    }

    private String getSheetNumber(List<Orders> list) {
        List o=new ArrayList();
        for(int i=0;i<list.size();i++){
            String s=list.get(i).getSheetNumber().replaceAll("[^0-9]+", "");;
            o.add(Integer.parseInt(s));
        }
        Collections.sort(o);
        return String.valueOf(o.get(o.size()-1));
    }

    public ResponseEntity<String> refreshPurchaseOrders(PageRequest pageRequest, String accountId, String ownerId) throws ParseException {
        Accounts s=accountsRepository.findByAccountsId(accountId);
        ResponseEntity<String> checkErrorExistence=getEbayPurchaseOrders(pageRequest, s, ownerId);
        if(checkErrorExistence.isErrors()==true)
            return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText(checkErrorExistence.getMessages().get(0).getMessageText()));
        else
        return new ResponseEntity<String>().withErrors(false);
    }

    public ResponseEntity<String> getEbayPurchaseOrders(Pageable p, Accounts s, String ownerId) throws ParseException {
        GetMyeBayBuyingCall list=new GetMyeBayBuyingCall();
        List<OrderTransactionArrayType> totalList=new ArrayList<OrderTransactionArrayType>();

        int pageNumber=p.getPageNumber();
        do{
            pageNumber++;
            try {
                list = getPurchasedOrdersFromEbay(pageNumber,p.getPageSize(), s);
                totalList.add(list.getReturnedWonList().getOrderTransactionArray());
            }catch (Exception e){return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText(e.getMessage()));
            }
        } while (pageNumber==0/*<=op==null || op.getTotalOrders()==nulllist.getReturnedWonList().getPaginationResult().getTotalNumberOfPages()*/);
        try {
            List<Orders> result = totalPurchasedOrders(totalList, ownerId, s);
            saveEbayPurchaseOrders(result, s.getSpreadSheetId(), "1", s);
        }catch(Exception e){    return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText(e.getMessage()));}
        return new ResponseEntity<String>().withErrors(false);
    }

    public List<Orders> totalPurchasedOrders(List<OrderTransactionArrayType> totalList,String ownerId,Accounts s) {
        OrderTransactionArrayType tempActiveItems = new OrderTransactionArrayType();
        List<OrderType[]> purchaseList = new ArrayList<OrderType[]>();
        List<Orders> result = new ArrayList<Orders>();
        for(int j=0;j<totalList.size();j++){
        tempActiveItems = totalList.get(j);
        for (int i = 0; i < tempActiveItems.getOrderTransactionLength(); i++) {
            if (tempActiveItems.getOrderTransaction(i).getTransaction() != null && tempActiveItems.getOrderTransaction(i).getTransaction().getPaidTime() != null) {
                Orders checkExistence = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderIdAndChannelIdAndOrderRef(s.getOwnerIdPk(), s.getId(), tempActiveItems.getOrderTransaction(i).getTransaction().getOrderLineItemID(), "1",null);
                if (checkExistence == null || checkExistence.getShippedDate() == null) {
                    OrderType[] transactionTypes = ebayService.getOrdersByOrderId(ownerId, s.getId(), tempActiveItems.getOrderTransaction(i).getTransaction().getOrderLineItemID());
                    purchaseList.add(transactionTypes);
                }
            } else if (tempActiveItems.getOrderTransaction(i).getOrder() != null && tempActiveItems.getOrderTransaction(i).getOrder().getPaidTime() != null) {
                Orders checkExistence = ordersRepository.findByOwnerIdPkAndAccountIdAndOrderIdAndChannelIdAndOrderRef(s.getOwnerIdPk(), s.getId(), tempActiveItems.getOrderTransaction(i).getOrder().getExtendedOrderID(), "1",null);
                if (checkExistence == null || checkExistence.getShippedDate() == null) {
                    OrderType[] orderTypes = ebayService.getOrdersByOrderId(ownerId, s.getId(), tempActiveItems.getOrderTransaction(i).getOrder().getOrderID());
                    purchaseList.add(orderTypes);
                }
            }
        }
    }
        for(int j=0;j<purchaseList.size();j++) {
            try {
                List<Orders> purchaseOrdersLog = ebayPurchaseOrdersConverter.convertToOrders(purchaseList.get(j)[0], s.getId(), ownerId);
                result.addAll(purchaseOrdersLog);
            }catch (Exception e){e.printStackTrace();}

        }
        return result;
    }

    public void saveEbayPurchaseOrders(List<Orders> orders, String spreadsheetId,String channelId,Accounts s) throws ParseException, DocumentException {
        String accountId="";
        List<Accounts> list=accountsRepository.findAll();
        for(int j=0;j<list.size();j++) {
            if (list.get(j).getAccountName().equals("PoundMonkey"))
                accountId=list.get(j).getId();
        }
        for(int i=0;i<orders.size();i++){
            Orders ord = orders.get(i);String category="";
            if (!isExistingOrder(ord,s)){
                ord.setSpreadsheetId(spreadsheetId);
                ord.setAccountId(s.getId());
                ord.setDuplicateId(spreadsheetId);
                ord.setChannel(getChannel(channelId));
                category=ebayService.getOrderCateory(ord.getTitle(),ord);
                if(category!=null && !category.equals(""))
                ord.setCategory(category);
                ordersRepository.save(ord);
                if(ord.getStatus().equals("AWAITING SHIPMENT"))
                ebayService.sendMessageToAndFromBuyer(s.getId(),ord.getBuyerUserId(),ord.getSellerUserId(),ord,null);
            }
            if(ord.getBuyerUserId().equals("goodley") && !ord.getBuyerName().equals("ZAEEM FAROOQUI") ){
                Orders exist=ordersRepository.findByOwnerIdPkAndExtendedOrderIdAndAccountId(ord.getOwnerIdPk(),ord.getExtendedOrderId(),accountId);
                if(exist==null){
                    ord.setId(null);Orders afterDelete= ord;
                    Orders order=ord;
                    ordersRepository.delete(ord);
                    order.setAccountId(accountId);
                    order.setDuplicateId(null);order.setSpreadsheetId(null);
                    order.setChannel(null);order.setCategory(null);
                    order.setOrderDropshipStatus("DROPSHIPPED");
                    order.setOrderStatus("DROPSHIPPED");
                    order.setOrderType("EBAY");order.setOrderRef(1000);
                    if(order.getShippedDate()==null)
                        order.setShippedDate(new Date());
                    ordersRepository.saveAndFlush(order);
                    ordersRepository.save(afterDelete);
                }
            }
            if(ord.getSellerUserId().equals("musicmagpie")){
                Orders exist=ordersRepository.findByOwnerIdPkAndExtendedOrderIdAndAccountIdAndChannelId(ord.getOwnerIdPk(),ord.getExtendedOrderId(),accountId,"3");
                if(exist==null){
                    ord.setId(null);Orders afterDelete= ord;
                    Orders order=ord;
                    ordersRepository.delete(ord);
                    ord.setChannel(getChannel("3"));
                    ordersRepository.saveAndFlush(order);
                    ordersRepository.save(afterDelete);
                }
            }
            if(!ord.getBuyerName().equals("ZAEEM FAROOQUI")) {
                ActivityLog exist = activityLogRepository.findByAccountIdAndSellerOrderId(s.getId(), ord.getExtendedOrderId());
                if (exist == null) {
                    ActivityLog logDropship = new ActivityLog();
                    logDropship.setSellerOrderId(ord.getExtendedOrderId());
                    logDropship.setTitle(ord.getTitle());
                    logDropship.setQuantity(Integer.valueOf(ord.getQuantityPurchased()));
                    logDropship.setAccountId(s.getId());
                    logDropship.setOwnerId(s.getOwnerIdPk());
                    logDropship.setSpreadsheetId(spreadsheetId);
                    logDropship.setConditionDisplayName(ord.getConditionDisplayName());
                    logDropship.setTrackingNumber(ord.getOrderTrackingId());
                    logDropship.setDuplicateId(spreadsheetId);
                    logDropship.setChannel(getChannel("15"));
                    if(ord.getCreatedDate()!=null)
                        logDropship.setCreatedDate(ord.getCreatedDate());
                    if(ord.getTotalAmount()!=null && !ord.getTotalAmount().equals("0.0"))
                        logDropship.setPrice(ord.getTotalAmount());
                    else if(ord.getAdjustmentAmount()!=null && !ord.getAdjustmentAmount().equals("0.0"))
                        logDropship.setPrice(ord.getAdjustmentAmount().substring(1));
                    logDropship.setSellerId(ord.getSellerUserId());
                    logDropship.setBuyerId(ord.getBuyerName());
                    activityLogRepository.save(logDropship);
                    List<Orders> orderExist = ordersRepository.findByAccountIdAndOrderStatusAndBuyerNameAndBuyerStreet1AndBuyerStreet2AndBuyerCityAndBuyerPostalCodeAndBuyerStateAndBuyerCountry(s.getId(), "AwaitingDispatch", ord.getBuyerName(), ord.getBuyerStreet1(), ord.getBuyerStreet2(), ord.getBuyerCity(), ord.getBuyerPostalCode(), ord.getBuyerState(), ord.getBuyerCountry());
                    if (orderExist.size()!=0){
                            Orders awaitingDropship =orderExist.get(0);
                            try {
                                calculations(awaitingDropship,logDropship);
                            }catch (Exception e){e.printStackTrace();}
                            if(awaitingDropship.getTransactionArrayLength()!=1)
                                logDropship.setTitle("<a href='https://simsapp.co.uk/#!/log/dropshipPurchaseOrder/"+logDropship.getId()+"/"+awaitingDropship.getId()+"' "+"target='_blank'>"+ord.getTitle()+"</a>");
                            else
                                logDropship.setTitle(ord.getTitle());
                            if(awaitingDropship.getTransactionArrayLength()==1 && orderExist.size()!=1)
                                logDropship.setBuyerId("<a href='https://simsapp.co.uk/#!/log/dropshipPurchaseOrderByUserId/"+logDropship.getId()+"/"+awaitingDropship.getBuyerUserId()+"' "+"target='_blank'>"+awaitingDropship.getBuyerUserId()+"</a>");
                            else
                                logDropship.setBuyerId(awaitingDropship.getBuyerUserId());
                            if(awaitingDropship.getOrderRef()!=null) {
                                logDropship.setSalesRecordNumber(String.valueOf(awaitingDropship.getOrderRef()));
                                logDropship.setOrderNumber(awaitingDropship.getExtendedOrderId());
                            }
                            activityLogRepository.saveAndFlush(logDropship);
                            if(awaitingDropship.getOrderRef()!=null && awaitingDropship.getTransactionArrayLength()==1 && orderExist.size()==1)
                                dropshipService.MarkOrderAsDropship(Collections.singletonList(awaitingDropship.getId()), new PageRequest(0, 200));
                        }
                }
                if(ord.getShippedDate()!=null) {
                    ord.setStatus("DROPSHIPPED");
                    ordersRepository.saveAndFlush(ord);
                }
            }
            else if(ord.getShippedDate()!=null) {
                if(!ord.getBuyerName().equals("ZAEEM FAROOQUI"))
                   ord.setStatus("DISPATCHED TO WAREHOUSE");
                else
                    ord.setStatus("DROPSHIPPED");
                ordersRepository.saveAndFlush(ord);
            }
        }
    }

    public GetMyeBayBuyingCall getPurchasedOrdersFromEbay(int pageNumber,int pageSize, Accounts s) throws Exception {
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(s.getApiToken());
        apiContext.setApiServerUrl(s.getUrl());
            GetApiAccessRulesCall limit = new GetApiAccessRulesCall(apiContext);
            limit.setApiContext(apiContext);
            GetMyeBayBuyingCall api = new GetMyeBayBuyingCall(apiContext);
            ItemListCustomizationType itemListType = new ItemListCustomizationType();
            itemListType.setInclude(Boolean.TRUE);
            itemListType.setIncludeNotes(Boolean.TRUE);
            itemListType.setOrderStatusFilter(OrderStatusFilterCodeType.ALL);
            PaginationType page = new PaginationType();
            page.setPageNumber(pageNumber);
            page.setEntriesPerPage(pageSize);
            itemListType.setPagination(page);
            api.setBuyingSummary(itemListType);
            api.setWonList(itemListType);
            DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[]{
                    DetailLevelCodeType.RETURN_ALL,
                    DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
                    DetailLevelCodeType.ITEM_RETURN_DESCRIPTION,
                    DetailLevelCodeType.RETURN_SUMMARY
            };
            api.setDetailLevel(detailLevels);
            api.getMyeBayBuying();
            return api;
    }

    private boolean isExistingOrder(Orders ord, Accounts accounts) {
        List<Orders> exist=ordersRepository.findByOrderIdAndOwnerIdPkAndAccountIdAndSellingRecordNumberAndBuyerName(ord.getOrderId(),accounts.getOwnerIdPk(),accounts.getId(),ord.getSellingRecordNumber(),ord.getBuyerName());
        if(exist.size()==0)
            return false;
        else
            return true;
    }

    private String ConvertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        return formatter.format(date);
    }

    public void calculations(Orders awaitingDropship,ActivityLog logDropship){
        if (awaitingDropship.getTotalAmount() != null && !awaitingDropship.getTotalAmount().equals("0.0"))
            logDropship.setSellingPrice(awaitingDropship.getTotalAmount());
        else if (awaitingDropship.getAdjustmentAmount() != null && !awaitingDropship.getAdjustmentAmount().equals("0.0"))
            logDropship.setSellingPrice(awaitingDropship.getAdjustmentAmount().substring(1));
        Double sellingPrice= Double.valueOf(logDropship.getSellingPrice());
        Double soldPrice= Double.valueOf(logDropship.getPrice());
        Double ebayPaypalValue=roundOff((11.5*sellingPrice/100)+0.2);
        logDropship.setEbayPaypalPercent(String.valueOf(ebayPaypalValue));
        Double diff=roundOff(sellingPrice-soldPrice);
        logDropship.setDifference(String.valueOf(diff));
        Double vat=roundOff(diff*0.1666);
        logDropship.setVat(String.valueOf(vat));
        Double profit=roundOff(diff-ebayPaypalValue-vat);
        logDropship.setProfit(String.valueOf(profit));
        Double profitPercent=roundOff((profit/sellingPrice)*100);
        logDropship.setProfitPercentage(String.valueOf(profitPercent)+"%");
    }

    private Double roundOff(Double value){
       return Math.round(value * 100D) / 100D;
    }

    public void updatePurchaseOrderStatusWithMessage(Accounts s){
        List<Orders> orders=ordersRepository.findByBuyerUserIdAndPaidDate("poundmonkey",ebayService.getStartingDate(3),new Date());
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getStatus().equals("AWAITING SHIPMENT")){
               OrderType[] order= ebayService.geOrder(orders.get(i).getExtendedOrderId(),s);
                if(order!=null && order[0].getShippedTime()!=null){
                    orders.get(i).setShippedDate(order[0].getShippedTime().getTime());
                    orders.get(i).setStatus("DISPATCHED");
                    ordersRepository.save(orders.get(i));
                }
                else if(order!=null && order[0].getOrderStatus().value().equals("Cancelled")){
                    if(order[0].getCancelStatus()!=null)
                    orders.get(i).setCancelStatus(order[0].getCancelStatus().value());
                    orders.get(i).setStatus("CANCELLED");
                    ordersRepository.save(orders.get(i));
                }
                else if(order!=null && orders.get(i).getSentTextMessage()==null && !order[0].getOrderStatus().value().equals("Cancelled") && order[0].getShippedTime()==null){
                    ebayService.sendMessageToAndFromBuyer(s.getId(),"poundmonkey",orders.get(i).getSellerUserId(),orders.get(i),3);
                    orders.get(i).setSentTextMessage("Yes");
                    ordersRepository.save(orders.get(i));
                }
            }
        }

    }

    public ResponseEntity<Orders> getSavedData(String name, String value, String accountId, String channelId, String spreadsheetId, String ownerId, String rowId){
        try {
            if (rowId.equals("undefined")) {
                Orders orders = new Orders();
                setRowData(name, value, orders);
                orders.setAccountId(accountId);
                orders.setChannel(getChannel(channelId));
                orders.setSpreadsheetId(spreadsheetId);
                orders.setOwnerIdPk(ownerId);
                Orders newOrder = ordersRepository.save(orders);
                return new ResponseEntity<>().withResults(newOrder);
            }
            else if(name.equals("sku")){
                Orders order = ordersRepository.findByIdAndOwnerIdPk(rowId, ownerId);
                List<Orders> list=ordersRepository.findByOwnerIdPkAndAccountIdAndTitleContainingIgnoreCaseAndChannelId(ownerId,accountId,order.getTitle(),channelId);
                for(int i=0;i<list.size();i++){
                    list.get(i).setSku(value);
                    ordersRepository.save(list.get(i));
                }
                return new ResponseEntity<>().withResults(order);
            }
            else{
                Orders orderExisting = ordersRepository.findByIdAndOwnerIdPk(rowId, ownerId);
                setRowData(name, value, orderExisting);
                Orders existOrder = ordersRepository.save(orderExisting);
                return new ResponseEntity<>().withResults(existOrder);
            }
        }catch (Exception e){e.printStackTrace();
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
    }

    public void setRowData(String name,String value,Orders orders) {
        if (name.equals("title"))
            orders.setTitle(value);
        else if(name.equals("extendedOrderId"))
            orders.setExtendedOrderId(value);
        else if(name.equals("category"))
            orders.setCategory(value);
        else if(name.equals("conditionDisplayName"))
            orders.setConditionDisplayName(value);
        else if(name.equals("totalAmount"))
            orders.setTotalAmount(value);
        else if(name.equals("quantityPurchased"))
            orders.setQuantityPurchased(value);
        else if(name.equals("sellerUserId"))
            orders.setSellerUserId(value);
        else if(name.equals("orderTrackingId"))
            orders.setOrderTrackingId(value);
        else if(name.equals("status"))
            orders.setStatus(value);
    }

    public ResponseEntity<Orders> saveCopyPasteData(String accountId, String channelId, String spreadsheetId, String ownerId, String rowId, Orders orders) {
        try {
            if (rowId.equals("undefined")) {
                orders.setAccountId(accountId);
                orders.setChannel(getChannel(channelId));
                orders.setSpreadsheetId(spreadsheetId);
                orders.setOwnerIdPk(ownerId);
                Orders newOrderData = ordersRepository.save(orders);
                return new ResponseEntity<>().withResults(newOrderData);
            } else {
                Orders orderExisting = ordersRepository.findByIdAndOwnerIdPk(rowId, ownerId);
                modifyOrder(orderExisting,orders);
                Orders existOrder = ordersRepository.saveAndFlush(orderExisting);
                return new ResponseEntity<>().withResults(existOrder);
            }
        }catch (Exception e){e.printStackTrace();
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
    }

    private void modifyOrder(Orders orderExisting, Orders orders) {
        if (orders.getCreatedDate()!= null)
            orderExisting.setCreatedDate(orders.getCreatedDate());
        if (orders.getExtendedOrderId()!= null)
            orderExisting.setExtendedOrderId(orders.getExtendedOrderId());
        if (orders.getTitle()!= null)
            orderExisting.setTitle(orders.getTitle());
        if (orders.getCategory()!= null)
            orderExisting.setCategory(orders.getCategory());
        if (orders.getSku()!= null)
            orderExisting.setSku(orders.getSku());
        if (orders.getConditionDisplayName()!= null)
            orderExisting.setConditionDisplayName(orders.getConditionDisplayName());
        if (orders.getTotalAmount()!= null)
            orderExisting.setTotalAmount(orders.getTotalAmount());
        if (orders.getQuantityPurchased()!= null)
            orderExisting.setQuantityPurchased(orders.getQuantityPurchased());
        if (orders.getSellerUserId()!= null)
            orderExisting.setSellerUserId(orders.getSellerUserId());
        if (orders.getOrderTrackingId()!= null)
            orderExisting.setOrderTrackingId(orders.getOrderTrackingId());
        if (orders.getStatus()!= null)
            orderExisting.setStatus(orders.getStatus());
    }
}