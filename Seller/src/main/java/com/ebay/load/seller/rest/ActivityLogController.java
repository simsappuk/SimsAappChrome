package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.ActivityLog;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
public class ActivityLogController {
    @Autowired
    ActivityLogService activityLogService;

    @RequestMapping(value = "{accountId}/{channelId}/{spreadSheetId}", method = RequestMethod.POST)
    public List<ActivityLog> create(@PathVariable("accountId") String accountId,
                                    @PathVariable("channelId") String id,
                                    @PathVariable("spreadSheetId") String spreadsheetId,
                                    @RequestBody List<ActivityLog> activityLogList) {
        return activityLogService.save(activityLogList, spreadsheetId, id,accountId);

    }

    @RequestMapping(value = "load/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ActivityLog>> getAll(@RequestParam(value = "channelId", defaultValue = "1", required = false) String channelId,
                                                    @RequestParam(value = "sheetNumber", defaultValue = "", required = false) String sheetNumber,
                                                    @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId,
                                                    @PathVariable("id") String spreadsheetId) {
        return activityLogService.findAllWithSpreadsheetId(SessionUserInfo.getLoggedInUser().getUser().getId(), spreadsheetId,channelId, sheetNumber,accountId);
    }

    @RequestMapping(value = "/saveCellData", method = RequestMethod.GET)
    public ResponseEntity<ActivityLog> saveCellData(@RequestParam(value="name") String name,
                                                    @RequestParam("accountId") String accountId,
                                                    @RequestParam("channelId") String channelId,
                                                    @RequestParam("spreadSheetId") String spreadsheetId,
                                                    @RequestParam(value="value") String value,
                                                    @RequestParam(value="rowId") String rowId,
                                                    @RequestParam(value="title") String title){
        return activityLogService.getSavedData(name,value,accountId,channelId,spreadsheetId,SessionUserInfo.getLoggedInUser().getUser().getId(),rowId,title);
    }

    @RequestMapping(value = "/saveCopyPaste", method = RequestMethod.POST)
    public ResponseEntity<ActivityLog> saveData(    @RequestParam("accountId") String accountId,
                                                    @RequestParam("channelId") String channelId,
                                                    @RequestParam("spreadSheetId") String spreadsheetId,
                                                    @RequestParam(value="rowId") String rowId,
                                                    @RequestBody ActivityLog activityLog
                                                    ){
        return activityLogService.saveCopyPasteData(accountId,channelId,spreadsheetId,SessionUserInfo.getLoggedInUser().getUser().getId(),rowId,activityLog);
    }

    @RequestMapping(value = "/saveGdriveSheet", method = RequestMethod.POST)
    public ResponseEntity<ActivityLog> saveGDriveSheet(@RequestParam("title") String title,
                                                    @RequestParam("sheetId") String spreadsheetId){
        return activityLogService.saveSheetData(title,spreadsheetId);
    }

    @RequestMapping(value = "/getGdriveSheets", method = RequestMethod.GET)
    public ResponseEntity<ActivityLog> getGDriveSheet(){
        return activityLogService.getGdriveSheets();
    }

}
