package com.ebay.load.seller.service;


import com.ebay.load.seller.model.FormatCart;
import com.ebay.load.seller.repository.FormatCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CartService {

    @Autowired
    FormatCartRepository<FormatCart> cartFormatCartRepository;

    @Transactional
    public List<FormatCart> getAllPersons() {
        return (List<FormatCart>) cartFormatCartRepository.findAll();
    }


    public FormatCart createCart(FormatCart c) {
        c.setCreatedDate(new Date());
        c.setCreatedBy("admin");
        FormatCart c1 = null;
if(c.getRecord().equals("detail"))
            c1 = cartFormatCartRepository.findByParentRecordNumber(c.getParentRecordNumber());
     else    c1 = cartFormatCartRepository.findByRecords(c.getRecords());
        FormatCart c2 = null;
        if (c1 == null) {
            try {
                c2 = cartFormatCartRepository.save(c);
            } catch (Exception e) {
                System.out.print("ERROR :" + e.toString());
            }
            return c2;
        } else if (c1 != null) {
        FormatCart merged=compareCartItem(c,c1);
if(merged!=null)
    cartFormatCartRepository.save(merged);
        }
        return c1;
    }
public FormatCart  compareCartItem(FormatCart c, FormatCart c1){
        boolean edit=false;
    if (c1.getSellerPaidStatus() != c.getSellerPaidStatus()) {
        c1.setSellerPaidStatus(c.getSellerPaidStatus());edit=true;
    }
    if (c1.getPosition() != (c.getPosition()))
    { c1.setPosition(c.getPosition());edit=true;}

    if (c1.getCustomLabel() != (c.getCustomLabel()))
    {  c1.setCustomLabel(c.getCustomLabel());edit=true;}

    if (c1.getParentRecordNumber() != (c.getParentRecordNumber()))
    {  c1.setParentRecordNumber(c.getParentRecordNumber());edit=true;}

    if (c1.getShippedDate() != (c.getShippedDate()))
    {  c1.setShippedDate(c.getShippedDate());edit=true;}

    if (c1.getItemId() != (c.getItemId()))
    {  c1.setItemId(c.getItemId());edit=true;}

    if (c1.getDetailHref() != (c.getDetailHref()))
    {   c1.setDetailHref(c.getDetailHref());edit=true;}

    if (c1.getUrlStack() != (c.getUrlStack()))
    {    c1.setUrlStack(c.getUrlStack());edit=true;}

    if (c1.getRecord() != (c.getRecord()))
    {    c1.setRecord(c.getRecord());edit=true;}

    if (c1.getTitle() != (c.getTitle()))
    {    c1.setTitle(c.getTitle());edit=true;}

    if (c1.getFeedbackReceivedByBuyer() != (c.getFeedbackReceivedByBuyer()))
    {    c1.setFeedbackReceivedByBuyer(c.getFeedbackReceivedByBuyer());edit=true;}

    if (c1.getShippedStatus() != (c.getShippedStatus()))
    {    c1.setShippedStatus(c.getShippedStatus());edit=true;}

    if (c1.getName() != (c.getName()))
    {   c1.setName(c.getName());edit=true;}

    if (c1.getSoldOn() != (c.getSoldOn()))
    {    c1.setSoldOn(c.getSoldOn());edit=true;}

    if (c1.getSalePrice() != (c.getSalePrice()))
    {    c1.setSalePrice(c.getSalePrice());edit=true;}

    if (c1.getSaleDate() != (c.getSaleDate()))
    {     c1.setSaleDate(c.getSaleDate());edit=true;}

    if (c1.getTranId() != (c.getTranId()))
    {    c1.setTranId(c.getTranId());edit=true;}

    if (c1.getRecords() != (c.getRecords()))
    {    c1.setRecords(c.getRecords());edit=true;}

    if (c1.getEmailSent() != (c.getEmailSent()))
    {     c1.setEmailSent(c.getEmailSent());edit=true;}

    if (c1.getPurchasedQty() != (c.getPurchasedQty()))
    {    c1.setPurchasedQty(c.getPurchasedQty());edit=true;}

    if (c1.getPaidDate() != (c.getPaidDate()))
    {    c1.setPaidDate(c.getPaidDate());edit=true;}

    if (c1.getCheckoutStatus() != (c.getCheckoutStatus()))
    {    c1.setCheckoutStatus(c.getCheckoutStatus());edit=true;}

    if (c1.getImageURL() != (c.getImageURL()))
    {    c1.setImageURL(c.getImageURL());edit=true;}

    if (c1.getOrderId() != (c.getOrderId()))
    {    c1.setOrderId(c.getOrderId());edit=true;}

    if (c1.getTotalPrice() != c.getTotalPrice())
    {    c1.setTotalPrice(c.getTotalPrice());edit=true;}

    if (c1.getSalePriceCurrency() != (c.getSalePriceCurrency()))
    {     c1.setSalePriceCurrency(c.getSalePriceCurrency());edit=true;}

    if (c1.getTotalPriceCurrency() != (c.getTotalPriceCurrency()))
    {   c1.setTotalPriceCurrency(c.getTotalPriceCurrency());edit=true;}
    if(edit) return c1;
    else return null;
}

}