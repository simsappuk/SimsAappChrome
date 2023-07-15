package com.ebay.load.seller.rest;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.*;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.VintedListingRepository;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.repository.ImageRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.VintedService;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetMyeBaySellingCall;
import com.ebay.soap.eBLBaseComponents.ItemListCustomizationType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@RestController
@RequestMapping(value = "/api/Vinted")
public class VintedListingController {
    @Value("${upload.directory}")
    private String uploadDirectory;
    @Autowired
    VintedRepository vintedRepository;

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    VintedListingRepository vintedListingRepository;
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

//    The code to convert Image Url to Byte Array is below.
    public static byte[] convertImageByte(URL url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = new BufferedInputStream(url.openStream());
            byte[] byteChunk = new byte[4096];
            int n;
            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
            return baos.toByteArray();
        }
        catch (IOException e) {e.printStackTrace ();}
        finally {
            if (is != null) { is.close(); }
        }
        return null;
    }

    //Get Image From Database
    public byte[] getImageForOrderItem(long itemId) {
        Optional<ImageModel> option = imageRepository.findById(itemId);
        if(option.isPresent()) {
            ImageModel itemDO = option.get();
            if(itemDO.getPicByte() != null) {
                byte[] image = itemDO.getPicByte();
                return image;
            }
        }
        return null;
    }

    @RequestMapping(value = "/post/{accountId}",method = RequestMethod.POST)
    public ResponseEntity postVintedListing(@PathVariable("accountId") String accountId, @RequestBody Vinted vinted) throws IOException {       //@RequestPart("images") MultipartFile[] imageUrlData,
        if (vinted.getId() == null) {
            String s = vintedRepository.findIdByAccountId(accountId);
            vinted.setItemId(s);
            Vinted s1 = vintedRepository.save(vinted);
            ImageModel imageModel = new ImageModel();
            if(s1.getImageUrl() != null) {
                try {
                    URL imageUrl = new URL(s1.getImageUrl());
                    imageModel.setPicByte(convertImageByte(imageUrl));
                    imageModel.setId(s1.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imageModel = imageRepository.save(imageModel);
            return new ResponseEntity < Vinted > ().withResults(s1);//.status(HttpStatus.OK)
        } else if (vinted.getItemId() != null) {
            Vinted s1 = updateVinted(vinted);
            vintedRepository.save(s1);
        }
        return new ResponseEntity < Vinted > ().withResults(vinted);
    }
    @RequestMapping(value = "/pushVintedListing",method = RequestMethod.POST)
    public ResponseEntity<String> pushListing(@RequestParam(value = "accountId")String accountId,
                                              @RequestParam(value = "listingId")List<String> listingId){
        for(int i=0;i<listingId.size();i++){
            try {
                VintedListing vintedListing = vintedRepository.findByIdAndOwnerIdAndAccountId(listingId.get(i), SessionUserInfo.getLoggedInUser().getUser().getId(), accountId);
            }catch (Exception e){
                return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText(e.getMessage()));
            }
        }
        return new ResponseEntity<String>().withResults("success");
    }


    public ResponseEntity<VintedListing> findVintedByAccountId(String id) {
        Vinted s2 = vintedRepository.findByAccountId(id);
        if (s2 != null) {
            VintedListing vintedListing = new VintedListing();
            vintedListing.setItemId(s2.getId());
            vintedListing.setAccountId(s2.getItemId());
            VintedListing k = vintedListingRepository.save(vintedListing);
            return new ResponseEntity<VintedListing>().withResults(k);
        } else {
            return null;
        }
    }
    @RequestMapping(value="/vintedListingStatus",method=RequestMethod.POST)
    public ResponseEntity<List<Vinted>> postVintedListingStatus(@RequestParam("id") String id){
        Optional<VintedListing> v2 = vintedListingRepository.findByItemId(id);
        return new ResponseEntity < Optional<VintedListing>> ().withResults(v2);
    }
    @RequestMapping(value="/getVintedListing/{accountId}",method=RequestMethod.GET)
    public ResponseEntity<List<Vinted>> getVintedListing1(@PathVariable("accountId") String accountId,@RequestParam(value = "status", defaultValue = "", required = false) String status){
        String kk = vintedRepository.findItemIdByAccountId(accountId);
        List<Vinted> k = vintedRepository.findByItemIdAndStatus(kk,status);
        return new ResponseEntity < List<Vinted>>  ().withResults(k);
    }

    @RequestMapping(value="/postVintedListing/{accountId}",method=RequestMethod.POST)
    public ResponseEntity<List<Vinted>> postVintedListing(@PathVariable("accountId") String accountId,@RequestParam(value = "status", defaultValue = "", required = false) String status){
        List<Vinted> v2 = vintedRepository.findByAccountIdAndStatus(accountId,status);
        return new ResponseEntity < List<Vinted>> ().withResults(v2);
    }
    @RequestMapping(value="/getVintedListing/{itemId}/item",method=RequestMethod.GET)
    public ResponseEntity<Vinted> getVinteditems(@PathVariable("itemId") String itemId){
        //String stockId = vintedRepository.findByItemId(itemId);
        Optional<Vinted> k = vintedRepository.findById(itemId);
        return new ResponseEntity < Vinted> ().withResults(k.get());
    }

    public Vinted updateVinted(Vinted s) {
        //s.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
        s.setModifiedDate(new Date());
        Optional<Vinted> s2 = vintedRepository.findById(s.getId());
        vintedRepository.save(s);
        return s;
    }
    @RequestMapping(value = "/vintedStockData/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<Vinted> postVintedListing2(@PathVariable("accountId") String accountId){
        return new ResponseEntity<Vinted>().withResults(new Vinted());
    }

    @RequestMapping(value = "/vintedStock/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> postVintedListing1(@PathVariable("accountId") String accountId){
        String s = vintedRepository.findIdByAccountId(accountId);
        List<Vinted> vinted = vintedRepository.findByItemId(s);
        return new ResponseEntity<List<Vinted>>().withResults(vinted);

    }

    @RequestMapping(value = "/vintedItemDelete/{accountId}/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> deleteVintedItem(@PathVariable("accountId") String accountId,@PathVariable("id") String id){
        String s = vintedRepository.findIdByAccountId(accountId);
        List<Vinted> vinted = vintedRepository.deleteStockById(s,id);
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