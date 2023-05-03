package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Folders;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Spreadsheet;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.FoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/folders")
public class FoldersController {
    @Autowired
    FoldersService foldersService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public  ResponseEntity<Folders> createFolders(@RequestBody Folders folders) {
        return foldersService.saveFolders(folders);
    }

    @RequestMapping(value = "/saveSheet", method = RequestMethod.POST)
    public  ResponseEntity<Spreadsheet> createSheets(@RequestBody Spreadsheet spreadsheet) {
        return foldersService.saveSheets(spreadsheet);
    }

    @RequestMapping(value="getSheet/{id}/{accountId}",method=RequestMethod.GET)
    public ResponseEntity<Orders> getSheet(@PathVariable(value="id") String spreadsheetId,
                                           @PathVariable(value="accountId") String accountId){
        return foldersService.oldSheet(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,spreadsheetId,new Sort("createdDate"));
    }
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Spreadsheet> getAllSheets(@RequestParam(value ="accountId",defaultValue = "",required = false)String accountId){
       return foldersService.allSheets(accountId);
    }

    @RequestMapping(value="/{id}/{accountId}",method=RequestMethod.GET)
    public List<Folders> getFolders(@PathVariable("id") String id,
                                    @PathVariable("accountId")String accountId){
        return foldersService.allFolders(id,accountId);
    }

    @RequestMapping(value="/sheet/new",method=RequestMethod.GET)
    public ResponseEntity<Spreadsheet> createSheet(@RequestParam(value = "accountId",defaultValue ="0",required = false) String accountId){
        return foldersService.newSheet(accountId);
    }

    @RequestMapping(value="sheet/{id}/{accountId}",method=RequestMethod.GET)
    public ResponseEntity<Spreadsheet> getSheetById(@PathVariable(value="id") String spreadsheetId,
                                                    @PathVariable(value="accountId") String accountId){
        return foldersService.getSheetById(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,spreadsheetId);
    }
    @RequestMapping(value="sheet/{accountId}",method=RequestMethod.POST)
    public ResponseEntity<Spreadsheet> updateSheetById(@PathVariable(value="accountId") String accountId,
                                                       @RequestBody Spreadsheet spreadsheet){
        return foldersService.UpdateSheetById(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,spreadsheet);
    }
}
