package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController
{
    @Autowired
    private AccountsService accountsService;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    @PostMapping("")
    Accounts create(@RequestBody Accounts accounts)
    {

        Accounts accounts1=accountsService.save(accounts);
        return accounts1;

    }



    @RequestMapping(value = "", method = RequestMethod.GET)
    public  ResponseEntity<List<Accounts>> loadCurrentRequest(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                           @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                           @RequestParam(value = "filter", defaultValue = "*", required = false) String q) {
        return accountsService.findAllByOwnerIdPk(SessionUserInfo.getLoggedInUser().getUser().getId(),new PageRequest(page.intValue(), pageSize.intValue(),new Sort("createdDate")));

    }




    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ResponseEntity<Accounts>  getSingleAccounts(@PathVariable("id") String id) {
        return accountsService.findSingleId(SessionUserInfo.getLoggedInUser().getUser().getId(),id);
    }
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    ResponseEntity<String>  deleteSingleAccounts(@PathVariable("id") String id) {
        return accountsService.delete(id,SessionUserInfo.getLoggedInUser().getUser().getId());
    }

}
