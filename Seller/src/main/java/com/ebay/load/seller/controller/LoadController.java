package com.ebay.load.seller.controller;

import com.ebay.load.seller.model.Cart;
import com.ebay.load.seller.model.CartWrapper;
import com.ebay.load.seller.model.FormatCart;
import com.ebay.load.seller.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class LoadController {

   @Autowired
    CartService cartService;

    @RequestMapping(value = "/item", method = RequestMethod.POST, headers = "Accept=application/json")
    public  FormatCart loadCurrentRequest(@RequestBody CartWrapper emp) {
        System.out.println("Working....2 :" + emp.getCart().size());
        for (int i = 0; i < emp.getCart().size(); i++) {
            Cart ct = emp.getCart().get(i);

             cartService.createCart(convertCart(ct));
        }
        return null;
    }

   public FormatCart convertCart(Cart c) {
        FormatCart fm = new FormatCart();
       fm.setSellerPaidStatus(c.getSellerPaidStatus());
        fm.setPosition(c.getPosition());
        fm.setCustomLabel(c.getCustomLabel());
        fm.setParentRecordNumber(c.getParentRecordNumber());
        fm.setShippedDate(stringToDate(c.getShippedDate()));
        fm.setItemId(c.getItemId());
        fm.setDetailHref(c.getDetailHref());
        fm.setUrlStack(c.getUrlStack());
        fm.setRecord(c.getRecord());
        fm.setTitle(c.getTitle());
        fm.setFeedbackReceivedByBuyer(c.getFeedbackReceivedByBuyer());
        fm.setShippedStatus(c.getShippedStatus());
        fm.setName(c.getName());
        fm.setSoldOn(c.getSoldOn());
        fm.setSalePrice(CurrencyFormatToValue(c.getSalePrice()));
        fm.setSaleDate(stringToDate(c.getSaleDate()));
        fm.setTranId(c.getTranId());
        fm.setRecords(c.getRecords());
        fm.setEmailSent(c.getEmailSent());
        fm.setPurchasedQty(c.getPurchasedQty());
        fm.setPaidDate(stringToDate(c.getPaidDate()));
        fm.setCheckoutStatus(c.getCheckoutStatus());
        fm.setImageURL(c.getImageURL());
        fm.setOrderId(c.getOrderId());
        fm.setTotalPrice(CurrencyFormatToValue(c.getTotalPrice()));
        fm.setSalePriceCurrency(ValueToCurrency(c.getSalePrice()));
        fm.setTotalPriceCurrency(ValueToCurrency(c.getTotalPrice()));

        return fm;


    }
    public Date stringToDate(String date)  {
        Date sd = null;
        try {
            sd = new SimpleDateFormat("dd-MMM-yy").parse(date);
        } catch (Exception e) {
            System.out.print("Error"+e.getMessage());
        }
        return sd;
    }

public Double CurrencyFormatToValue(String text){
        if(text!=null) {
            String a = text.split("<br>")[0];
            String m =a.replaceAll("[^\\d.]+", "");
            double value = Double.parseDouble(m);
            return value;
        }
        else
            return null;
}
public String ValueToCurrency(String text){
        if(text!=null){
                String a = text.split("<br>")[0];
                String format=a.replaceAll("[^\\p{Sc}A-Za-z]+","");
            return format;
        }
        else
            return null;
}
}
