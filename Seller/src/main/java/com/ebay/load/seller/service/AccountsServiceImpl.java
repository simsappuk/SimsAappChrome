package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Spreadsheet;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.SpreadSheetRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
@Service
public class AccountsServiceImpl implements AccountsService {
    @Value( "${ebay.api.key}" )
    String token ;

    @Value( "${ebay.api.wsdl}" )
    String wsdlURL ;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private SpreadSheetRepository spreadSheetRepository;

    @Override
    @Transactional
    public Accounts save(Accounts accounts)
    {
        if(accounts.getInactive()==null)
            accounts.setInactive(false);
        if(accounts.getUpdateIndicator()==null)
            accounts.setUpdateIndicator(false);
        accounts.setOwnerIdPk(SessionUserInfo.getLoggedInUser().getUser().getId());
accounts.setCreatedDate(new Date());
        accounts.setCreatedBy(SessionUserInfo.getLoggedInUser().getUser().getName());
        accountsRepository.save(accounts);
        if(accounts.getSpreadSheetId()==null) {
            Spreadsheet spreadsheet = new Spreadsheet();
            spreadsheet.setAccountId(accounts.getId());
            spreadSheetRepository.save(spreadsheet);
            accounts.setSpreadSheetId(spreadsheet.getId());
        }
        return accountsRepository.save(accounts);
    }

    @Override
    public ResponseEntity<List<Accounts>> findAllByOwnerIdPk(String ownerIdPk, Pageable p) {
        Page<Accounts> accounts = accountsRepository.findByOwnerIdPk(ownerIdPk, p);
        return new ResponseEntity<List<Accounts>>().withResults(accounts.getContent()).withTotalPages(accounts.getTotalPages()).withTotalElements(accounts.getTotalElements());
 /*  @Override
    public ResponseEntity<Accounts> findByIdAndOwnerId(String id, String ownerId){
       Accounts accounts = accountsRepository.findByIdAndOwnerIdPk(id,ownerId);
        return new ResponseEntity<Accounts>().withResults(accounts);

    }

*/

    }
    public ResponseEntity<String> delete(String id, String ownerId){
        Accounts accounts1 = accountsRepository.findByIdAndOwnerIdPk(id,ownerId);
        if(accounts1!=null) {
            accountsRepository.delete(accounts1);
              return new ResponseEntity<String>().withErrors(false);
        }
return  new ResponseEntity<String>().withErrors(true);
    }
 @Override
 public ResponseEntity<Accounts>findSingleId(String ownerIdPk,String accountName)
        {
            Accounts accounts1 = accountsRepository. findByIdAndOwnerIdPk(accountName,ownerIdPk);
            return new ResponseEntity<Accounts>().withResults(accounts1);

        }


}
