package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.ActivityLog;
import com.ebay.load.seller.model.Channel;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Vendors;
import com.ebay.load.seller.repository.ActivityLogRepository;
import com.ebay.load.seller.repository.OrdersRepository;
import com.ebay.load.seller.repository.VendorsRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityLogService {


    @Autowired
    VendorsRepository vendorsRepository;

    @Autowired
    ActivityLogRepository activityLogRepository;

    @Autowired
    OrdersServiceImpl ordersService;

    @Autowired
    OrdersRepository ordersRepository;


    public Channel getChannel(String id){
        Channel c = new Channel();
        c.setId(id);
        return c;
    }
    public boolean isExistinglog(ActivityLog log){
        ActivityLog s=null;
        if(log.getId()!=null)
            s  =activityLogRepository.findByIdAndOwnerId(log.getId(),SessionUserInfo.getLoggedInUser().getUser().getId());
        if(s!=null)
            return true;

        return false;
    }

    
    public List<ActivityLog> save(List<ActivityLog> activityLogList, String spreadsheetId, String channelId,String accountId) {
        for(int i=0;i<activityLogList.size();i++) {
            ActivityLog log = activityLogList.get(i);
            if (!isExistinglog(log)) {
                log.setChannel(getChannel(channelId));
            }
            if (log.getCreatedDate() == null)
                log.setCreatedDate(new Date());
            log.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
            if(activityLogList.get(0).getSpreadsheetId()==null) {
                log.setSpreadsheetId(spreadsheetId);
                log.setDuplicateId(spreadsheetId);
            }
            else {
                log.setSpreadsheetId(activityLogList.get(0).getSpreadsheetId());
                log.setDuplicateId(activityLogList.get(0).getDuplicateId());
            }
            if(activityLogList.get(0).getSheetNumber()!=null)
                log.setSheetNumber(activityLogList.get(0).getSheetNumber());
            log.setAccountId(accountId);
            ActivityLog logs=activityLogRepository.save(log);
            activityLogList.set(i,logs);
        }
        return activityLogList;
    }

    public ResponseEntity<List<ActivityLog>> findAllWithSpreadsheetId(String ownerId, String spreadsheetId, String channelId, String sheetNumber,String accountId) {
        List<ActivityLog> check=activityLogRepository.findAll();
        if(check.size()==0) {
            ActivityLog log = new ActivityLog();
            log.setSpreadsheetId(spreadsheetId);
            log.setDuplicateId(spreadsheetId);
            log.setAccountId(accountId);
            log.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
            activityLogRepository.save(log);
        }
            List<ActivityLog> logs = activityLogRepository.findByOwnerIdAndAccountIdAndSpreadsheetIdAndChannelId(ownerId,accountId,spreadsheetId, channelId,new Sort(Sort.Direction.ASC,"createdDate"));
            return new ResponseEntity<List<ActivityLog>>().withResults(logs);

    }

    public void saveLog(String logId,Orders order) {
        ActivityLog log=activityLogRepository.findOneById(logId);
        log.setOrderNumber(order.getExtendedOrderId());
        if(order.getOrderRef()!=null)
        log.setSalesRecordNumber(String.valueOf(order.getOrderRef()));
        if(log.getTitle().contains("target"))
            log.setTitle(log.getTitle().substring(log.getTitle().indexOf(">")+1,log.getTitle().lastIndexOf("<")));
        if(log.getBuyerId().contains("target"))
            log.setBuyerId(log.getBuyerId().substring(log.getBuyerId().indexOf(">")+1,log.getBuyerId().lastIndexOf("<")));
        log.setSellingPrice(order.getTotalAmount());
        ordersService.calculations(order,log);
        activityLogRepository.saveAndFlush(log);

    }

    public ResponseEntity<ActivityLog> getSavedData(String name, String value,String accountId,String channelId,String spreadSheetId,String ownerId,String rowId,String title) {
        try {
            if (rowId.equals("undefined")) {
                ActivityLog activityLog = new ActivityLog();
                setRowData(name, value, activityLog);
                activityLog.setAccountId(accountId);
                activityLog.setChannel(getChannel(channelId));
                activityLog.setSpreadsheetId(spreadSheetId);
                activityLog.setOwnerId(ownerId);
                if (!title.equals("undefined"))
                    saveLogTitle(title, activityLog);
                ActivityLog newLog = activityLogRepository.save(activityLog);
                return new ResponseEntity<>().withResults(newLog);
            } else {
                ActivityLog logExisting = activityLogRepository.findByIdAndOwnerId(rowId, ownerId);
                setRowData(name, value, logExisting);
                if (!title.equals("undefined"))
                    saveLogTitle(title, logExisting);
                ActivityLog existLog = activityLogRepository.save(logExisting);
                return new ResponseEntity<>().withResults(existLog);
            }
        }catch (Exception e){e.printStackTrace();
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
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

    public void setRowData(String name,String value,ActivityLog activityLog){
        if(name.equals("date"))
            activityLog.setDate(value);
        else if(name.equals("buyerId"))
            activityLog.setBuyerId(value);
        else if(name.equals("salesRecordNumber"))
            activityLog.setSalesRecordNumber(value);
        else if(name.equals("orderNumber"))
            activityLog.setOrderNumber(value);
        else if(name.equals("confirm"))
            activityLog.setConfirm(value);
        else if(name.equals("reason"))
            activityLog.setReason(value);
        else if(name.equals("actionNeeded"))
            activityLog.setActionNeeded(value);
        else if(name.equals("solution"))
            activityLog.setSolution(value);
        else if(name.equals("createdDate"))
            activityLog.setCreatedDate(stringToDate(value));
        else if(name.equals("quantity"))
            activityLog.setQuantity(Integer.valueOf(value));
        else if(name.equals("conditionDisplayName"))
            activityLog.setConditionDisplayName(value);
        else if(name.equals("price"))
            activityLog.setPrice(value);
        else if(name.equals("sellingPrice"))
            activityLog.setSellingPrice(value);
        else if(name.equals("sellerId"))
            activityLog.setSellerId(value);
        else if(name.equals("sellerOrderId"))
            activityLog.setSellerOrderId(value);
        else if(name.equals("trackingNumber"))
            activityLog.setTrackingNumber(value);
        else if(name.equals("ebayPaypalPercent"))
            activityLog.setEbayPaypalPercent(value);
        else if(name.equals("difference"))
            activityLog.setDifference(value);
        else if(name.equals("vat"))
            activityLog.setVat(value);
        else if(name.equals("profit"))
            activityLog.setProfit(value);
        else if(name.equals("profitPercentage"))
            activityLog.setProfitPercentage(value);
        else if(name.equals("trackingNumber"))
            activityLog.setTrackingNumber(value);
        else if(name.equals("remarks"))
            activityLog.setRemarks(value);
        else if(name.equals("deliveredDate"))
            activityLog.setDeliveredDate(value);
        else if(name.equals("recordNumber"))
            activityLog.setRecordNumber(value);
        else if(name.equals("sentDate"))
            activityLog.setSentDate(value);
        else if(name.equals("notes"))
            activityLog.setNotes(value);
        else if(name.equals("returnedItem"))
            activityLog.setReturnedItem(value);
        else if(name.equals("itemNumber"))
            activityLog.setItemNumber(value);
        else if(name.equals("returnDate"))
            activityLog.setReturnDate(value);
        else if(name.equals("proof"))
            activityLog.setProof(value);
        else if(name.equals("reason"))
            activityLog.setReason(value);
        else if(name.equals("paypalEmailId"))
            activityLog.setPaypalEmailId(value);
        else if(name.equals("courierService"))
            activityLog.setCourierService(value);
        else if(name.equals("title")) {
            saveLogTitle(value,activityLog);
        }
        else if(name.equals("update"))
            activityLog.setUpdate(value);
        else if(name.equals("status"))
            activityLog.setStatus(value);
    }

    public void  saveLogTitle(String title,ActivityLog activityLog){
        title=title.replaceAll("'","[");
        title=title.replaceAll("/","]");
        activityLog.setTitle(title);

    }

    public ResponseEntity<ActivityLog> saveCopyPasteData(String accountId, String channelId, String spreadsheetId, String ownerId, String rowId, ActivityLog activityLog) {
        try {
            if (rowId.equals("undefined")) {
                activityLog.setAccountId(accountId);
                activityLog.setChannel(getChannel(channelId));
                activityLog.setSpreadsheetId(spreadsheetId);
                activityLog.setOwnerId(ownerId);
                ActivityLog newLog = activityLogRepository.save(activityLog);
                return new ResponseEntity<>().withResults(newLog);
            } else {
                ActivityLog logExisting = activityLogRepository.findByIdAndOwnerId(rowId, ownerId);
                modifyLog(logExisting,activityLog);
                ActivityLog existLog = activityLogRepository.saveAndFlush(logExisting);
                return new ResponseEntity<>().withResults(existLog);
            }
        }catch (Exception e){e.printStackTrace();
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));
        }
    }

    private void modifyLog(ActivityLog logExisting, ActivityLog activityLog) {
        if (activityLog.getDate()!= null)
            logExisting.setDate(activityLog.getDate());
        if (activityLog.getBuyerId()!=null)
            logExisting.setBuyerId(activityLog.getBuyerId());
        if (activityLog.getSalesRecordNumber()!=null)
            logExisting.setSalesRecordNumber(activityLog.getSalesRecordNumber());
        if (activityLog.getOrderNumber()!=null)
            logExisting.setOrderNumber(activityLog.getOrderNumber());
        if (activityLog.getConfirm()!=null)
            logExisting.setConfirm(activityLog.getConfirm());
        if (activityLog.getReason()!=null)
            logExisting.setReason(activityLog.getReason());
        if (activityLog.getActionNeeded()!=null)
            logExisting.setActionNeeded(activityLog.getActionNeeded());
        if (activityLog.getSolution()!=null)
            logExisting.setSolution(activityLog.getSolution());
        if (activityLog.getCreatedDate()!=null)
            logExisting.setCreatedDate(activityLog.getCreatedDate());
        if (activityLog.getQuantity()!=null)
            logExisting.setQuantity(activityLog.getQuantity());
        if (activityLog.getConditionDisplayName()!=null)
            logExisting.setConditionDisplayName(activityLog.getConditionDisplayName());
        if (activityLog.getPrice()!=null)
            logExisting.setPrice(activityLog.getPrice());
        if (activityLog.getSellingPrice()!=null)
            logExisting.setSellingPrice(activityLog.getSellingPrice());
        if (activityLog.getSellerId()!=null)
            logExisting.setSellerId(activityLog.getSellerId());
        if (activityLog.getSellerOrderId()!=null)
            logExisting.setSellerOrderId(activityLog.getSellerOrderId());
        if (activityLog.getTrackingNumber()!=null)
            logExisting.setTrackingNumber(activityLog.getTrackingNumber());
        if (activityLog.getEbayPaypalPercent()!=null)
            logExisting.setEbayPaypalPercent(activityLog.getEbayPaypalPercent());
        if (activityLog.getDifference()!=null)
            logExisting.setDifference(activityLog.getDifference());
        if (activityLog.getVat()!=null)
            logExisting.setVat(activityLog.getVat());
        if (activityLog.getProfit()!=null)
            logExisting.setProfit(activityLog.getProfit());
        if (activityLog.getProfitPercentage()!=null)
            logExisting.setProfitPercentage(activityLog.getProfitPercentage());
        if (activityLog.getRemarks()!=null)
            logExisting.setRemarks(activityLog.getRemarks());
        if (activityLog.getDeliveredDate()!=null)
            logExisting.setDeliveredDate(activityLog.getDeliveredDate());
        if (activityLog.getRecordNumber()!=null)
            logExisting.setRecordNumber(activityLog.getRecordNumber());
        if (activityLog.getSentDate()!=null)
            logExisting.setSentDate(activityLog.getSentDate());
        if (activityLog.getNotes()!=null)
            logExisting.setNotes(activityLog.getNotes());
        if (activityLog.getReturnedItem()!=null)
            logExisting.setReturnedItem(activityLog.getReturnedItem());
        if (activityLog.getItemNumber()!=null)
            logExisting.setItemNumber(activityLog.getItemNumber());
        if (activityLog.getReturnDate()!=null)
            logExisting.setReturnDate(activityLog.getReturnDate());
        if (activityLog.getProof()!=null)
            logExisting.setProof(activityLog.getProof());
        if (activityLog.getReason()!=null)
            logExisting.setReason(activityLog.getReason());
        if (activityLog.getPaypalEmailId()!=null)
            logExisting.setPaypalEmailId(activityLog.getPaypalEmailId());
        if (activityLog.getCourierService()!=null)
            logExisting.setCourierService(activityLog.getCourierService());
        if (activityLog.getTitle()!=null)
            logExisting.setTitle(activityLog.getTitle());
        if (activityLog.getUpdate()!=null)
            logExisting.setUpdate(activityLog.getUpdate());
        if (activityLog.getStatus()!=null)
            logExisting.setStatus(activityLog.getStatus());
    }

    public ResponseEntity<ActivityLog> saveSheetData(String title, String spreadsheetId) {
        ActivityLog exist=activityLogRepository.findBySpreadsheetIdAndSheetNumber(spreadsheetId,"Gdrive");
        if(exist==null) {
            ActivityLog activityLog = new ActivityLog();
            activityLog.setTitle(title);
            activityLog.setSpreadsheetId(spreadsheetId);
            activityLog.setSheetNumber("Gdrive");
            activityLogRepository.save(activityLog);
            return new ResponseEntity<ActivityLog>().withResults(activityLog);
        }
        else
            return null;
    }

    public ResponseEntity<ActivityLog> getGdriveSheets() {
        List<ActivityLog> list=activityLogRepository.findBySheetNumber("Gdrive");
        return new ResponseEntity<>().withResults(list);
    }

    public void saveSheets(String result){
        String[] sheetData=result.split("},");
        for(int i=0;i<sheetData.length;i++){
            String sheetId=sheetData[i].split(",")[0].split(":")[1].replaceAll("\"","");
            String spreadSheetName=sheetData[i].split(",")[1].split(":")[1].replaceAll("\"","");
            if(!spreadSheetName.contains("."))
            saveSheetData(spreadSheetName,sheetId);
        }
    }

    public String setTitles(String result){
        for(int j=19762;j<result.split("],").length;j++){
            try {
                String recordNum = result.split("],")[j].split(",")[1].replaceAll("\"", "");
                Orders orders = ordersRepository.findByOrderRef(Integer.valueOf(recordNum));
                if (orders != null && j!=result.split("],").length) {
                    String titleChange="";
                    titleChange = result.split("],")[j].replace("\"\"", "\""+orders.getTitle()+"\"");
                    result=result.replace(result.split("],")[j],titleChange);
                }
            }catch (Exception e){
                System.out.println( result.split("],")[j]);
            }
        }
        return result;
    }
}
