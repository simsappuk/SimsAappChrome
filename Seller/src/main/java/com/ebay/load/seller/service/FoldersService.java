package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Folders;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Spreadsheet;
import com.ebay.load.seller.repository.FoldersRepository;
import com.ebay.load.seller.repository.OrdersRepository;
import com.ebay.load.seller.repository.SpreadSheetRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoldersService {
   @Autowired
    FoldersRepository foldersRepository;
   @Autowired
    SpreadSheetRepository spreadSheetRepository;
   @Autowired
    OrdersRepository ordersRepository;

    public List<Folders> allFolders(String id,String accountId) {
        List<Folders> f = foldersRepository.findByParentIdAndAccountId(id,accountId);
            return f;

    }

    public ResponseEntity<Folders> saveFolders(Folders f) {
        foldersRepository.save(f);
        return null;
    }

    public ResponseEntity<Spreadsheet> saveSheets(Spreadsheet s) {
        spreadSheetRepository.save(s);
        return null;
    }

    public  ResponseEntity<Spreadsheet> newSheet(String accountId){
        Spreadsheet s=new Spreadsheet();
        String name;
        List<Spreadsheet> list=spreadSheetRepository.findAll();
        int increase=list.size();
        if(increase==1){
            name="Untitled1";
            s.setName(name);
        }
        else {
            name = "Untitled"+increase;
            s.setName(name);
        }
        s.setFolderId("0");
        s.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
        s.setAccountId(accountId);
        spreadSheetRepository.save(s);

        Orders o= new Orders();
        o.setSpreadsheetId(s.getId());
        o.setDuplicateId(s.getId());
        o.setAccountId(accountId);
        o.setOwnerIdPk(SessionUserInfo.getLoggedInUser().getUser().getId());
        ordersRepository.save(o);
        return s;
    }
    public ResponseEntity<Orders> oldSheet(String ownerIdPk,String accountId,String spreadsheetId, Sort p ){
        List<Orders> s=ordersRepository.findByOwnerIdPkAndAccountIdAndSpreadsheetId(ownerIdPk,accountId,spreadsheetId,p);
        return new ResponseEntity<List<Orders>>().withResults(s);
    }
    public List<Spreadsheet> allSheets(String accountId) {
        List<Spreadsheet> s = spreadSheetRepository.findByAccountId(accountId);
        return s;

    }
    public ResponseEntity<Spreadsheet> getSheetById(String ownerId,String accountId,String sheetId) {
        Spreadsheet s = spreadSheetRepository.findByIdAndOwnerIdAndAccountId(sheetId,ownerId,accountId);
       return new ResponseEntity<Spreadsheet>().withResults(s);

    }
    public ResponseEntity<Spreadsheet> UpdateSheetById(String ownerId,String accountId,Spreadsheet sheet) {
        try {
            if(sheet.getId()==null)
                sheet.setId("0");
            Spreadsheet s = spreadSheetRepository.findByIdAndOwnerIdAndAccountId(sheet.getId(), ownerId, accountId);
            if (s != null)
                s = spreadSheetRepository.saveAndFlush(sheet);
            return new ResponseEntity<Spreadsheet>().withResults(s);
        }catch (Exception e){}
        return  null;
    }
}
