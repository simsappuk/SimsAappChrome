package com.ebay.load.seller.rest;

import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.soap.eBLBaseComponents.ItemType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

//    @GetMapping("/vintedCategorySearch/{id}")
//    public String getStudents1(@PathVariable(name="id") String id,@RequestParam(name = "query") String term){
//        RestTemplate restTemplate = new RestTemplate();
//        String pp= vintedRepository.findApiToken(id);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization","Token "+pp );
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        String url  = "https://cat.zipsale.co.uk/api/v1/categories/categories_full_readable_path_data/?term="+term+"&limitChoices=10&marketplace_slug=vinted";
//        org.springframework.http.ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        return responseEntity.getBody();
//    }
    @GetMapping("http://135.181.192.92:5000/categories")
    public String getStudents1(String term){
        return term;
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