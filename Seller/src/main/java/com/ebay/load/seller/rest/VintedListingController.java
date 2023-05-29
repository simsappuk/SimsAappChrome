package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/Vinted")
public class VintedListingController {
    @Autowired
    VintedRepository vintedRepository;
    @Autowired
    AccountsRepository accountsRepository;

    @ResponseBody
    @RequestMapping(value = "/vinted/getJsonData", method = RequestMethod.GET)
    public void getJson(@RequestParam(value = "dat", defaultValue = "{}", required = false) String dat) throws IOException {
        System.out.println("working.....:" + dat);
        JSONObject json = null;
        try {
            json = new JSONObject(dat);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // System.out.println("ok");
        String k = "";
        String itemid;
        String itemClosingAction;
        String accountId;
        String accountName;
        Double price;
        try {
            itemid = json.getString("id");
            itemClosingAction = json.getString("item_closing_action");
            accountName = json.getString("account_name");
            price = json.getDouble("original_price_numeric");
            k = vintedRepository.findExistingByItemId(itemid);
            accountId = vintedRepository.findExistingAccountByAccountName(accountName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (k.equals("0")) {
            Vinted vin = new Vinted();
            try {
                vin.setItemId(json.getString("id"));
                vin.setTitle(json.getString("title"));
                vin.setUrl(json.getString("url"));
                vin.setCreatedAt(json.getString("created_at"));
                vin.setOriginalPriceNumeric(price);
                vin.setItemClosingAction(json.getString("item_closing_action"));
                //    vin. setModifiedDate (json.getString("ModifiedDate"));
                vin.setAccountId(accountId);
                vintedRepository.save(vin);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Optional<Vinted> s = vintedRepository.findById(k);
            Vinted v1 = s.get();
            v1.setItemClosingAction(itemClosingAction);
            v1.setOriginalPriceNumeric(price);
            v1.setModifiedDate(new Date());
            vintedRepository.save(v1);
        }

    }

    @RequestMapping(value = "/post/{accountId}",method = RequestMethod.POST)
    public ResponseEntity postVintedListing(@PathVariable("accountId") String accountId, @RequestBody Vinted vinted){
//        Optional<Vinted> s = vintedRepository.findById(accountId);
        if (vinted.getItemId() == null) {
            String s = vintedRepository.findIdByAccountId(accountId);
            vinted.setItemId(s);
            Vinted s1 = vintedRepository.save(vinted);
            return new ResponseEntity < Vinted > ().withResults(s1);
        } else if (vinted.getItemId() != null) {
            Vinted s1 = updateVinted(vinted);
            vintedRepository.save(s1);
        }
        return new ResponseEntity < Vinted > ().withResults(vinted);
    }

    public Vinted updateVinted(Vinted s) {
        //s.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
        s.setModifiedDate(new Date());
        Optional<Vinted> s2 = vintedRepository.findById(s.getId());
        vintedRepository.save(s);
        return s;
    }
//        Vinted response = vintedRepository.save(vinted);
//        JSONObject k= new JSONObject(response);
//        return k;
    @RequestMapping(value = "/vintedStock/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> postVintedListing1(@PathVariable("accountId") String accountId){
        String s = vintedRepository.findIdByAccountId(accountId);
        List<Vinted> vinted = vintedRepository.findByItemId(s);
        return new ResponseEntity<List<Vinted>>().withResults(vinted);
        
    }

    @RequestMapping(value = "/{id}/list/vinted", method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> loadCurrent(@PathVariable("id") String id,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,

                                                    @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return new ResponseEntity<List<Vinted>>().withResults(vintedRepository.findAllActiveListing(id));
    }

    @RequestMapping(value = "/{id}/list/vintedSold", method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> loadSold(@PathVariable("id") String id,
                                                 @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,

                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return new ResponseEntity<List<Vinted>>().withResults(vintedRepository.findAllSoldListing(id));
    }

    @RequestMapping(value = "/{id}/list/vintedUpdateDispatch", method = RequestMethod.GET)
    public void getIds(@PathVariable("id") String id, @RequestParam(value = "data", defaultValue = "", required = false) String dat) throws IOException {
        System.out.println("working.....:" + dat);
        String[] s = dat.split(",");
        for (int i = 0; i < s.length; i++) {
            Optional<Vinted> s2 = vintedRepository.findById(s[i]);
            if (s2.isPresent()) {
                Vinted v1 = s2.get();
                v1.setItemClosingAction("Dispatched");
                vintedRepository.save(v1);

            }
        }
    }


@RequestMapping(value = "/{id}/list/vintedDispatch", method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> loadDispatch(@PathVariable("id") String id,
                                                 @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                     @RequestParam("startDate") Date startDate,
                                                     @RequestParam("endDate") Date endDate,
                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        System.out.println(startDate);
        return new ResponseEntity<List<Vinted>>().withResults(vintedRepository.findAllDispatchListing(id,startDate, endDate));
    }
}