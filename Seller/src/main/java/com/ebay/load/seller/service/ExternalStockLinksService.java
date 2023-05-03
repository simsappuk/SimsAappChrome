package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.ExternalStockLinks;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.ExternalStockLinksRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.soap.eBLBaseComponents.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExternalStockLinksService {

    @Autowired
    ExternalStockLinksRepository externalStockLinksRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    EbayService ebayService;


    public ResponseEntity<ExternalStockLinks> save(ExternalStockLinks externalStockLinks, String accountId) {
        ExternalStockLinks exist=new ExternalStockLinks();
        if(externalStockLinks.getCexId()!=null)
            exist = externalStockLinksRepository.findByCexIdAndAccountId(externalStockLinks.getCexId(), accountId);
        else
            exist = externalStockLinksRepository.findByLinkAndAccountId(externalStockLinks.getLink(), accountId);

        if(exist==null) {
            if(externalStockLinks.getPrice().equals("Out of Stock")) {
                externalStockLinks.setOutOfStock(true);
                externalStockLinks.setPrice("Unavailable");
            }
            else
                externalStockLinks.setOutOfStock(false);
            externalStockLinks.setAccountId(accountId);
            externalStockLinks.setCondition("Used");
            externalStockLinks.setLastEffectiveDate(new Date());
            externalStockLinks.setOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId());
            ExternalStockLinks save = externalStockLinksRepository.save(externalStockLinks);
            return new ResponseEntity<>().withResults(save);
        }
        else
            return new ResponseEntity<>().withErrors(true).withMessages(new Message().withMessageText("Details Exist Already"));
    }

    public ResponseEntity<ExternalStockLinks> getContent(String accountId) {
        List<ExternalStockLinks> d=externalStockLinksRepository.findByOwnerIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId);
        Accounts s =accountsRepository.findByAccountsId(accountId);
        s.setUpdateIndicator(false);
        accountsRepository.save(s);
        return new ResponseEntity<List<ExternalStockLinks>>().withResults(d);
    }

    public String getEbayPrice(String url, String accountId) {
        String output="";String ebayPrice="";
        Accounts s =accountsRepository.findByAccountsId(accountId);
        String itemId=url.replaceAll("[A-Za-z-+></.:]","");
        ItemType itemType =ebayService.geItem(itemId,s);
        if(itemType.getSellingStatus().getQuantitySold()-itemType.getQuantity()!=0)
            ebayPrice=itemType.getSellingStatus().getCurrentPrice().toString();
        else
            ebayPrice="Out of Stock";
        String title=itemType.getTitle();
        output=ebayPrice+"%"+title;
        return output;
    }

    public ResponseEntity<List<ExternalStockLinks>> updateStockLinks(String accountId,String ownerId){
        List<ExternalStockLinks> list=externalStockLinksRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        Accounts s =accountsRepository.findByAccountsId(accountId);
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                ExternalStockLinks externalStockLinks = list.get(i);
                String result;
                try {
                    if (externalStockLinks.getLink() != null) {
                        if(externalStockLinks.getLink().contains("ebay.co.uk"))
                            result = getEbayPrice(externalStockLinks.getLink(),accountId);
                        else
                            result = HttpClient.sendGet(externalStockLinks.getLink());
                    }
                    else
                        result = HttpClient.sendGet("https://wss2.cex.uk.webuy.io/v3/boxes/"+externalStockLinks.getCexId()+"/detail");
                    String[] data = result.split("%");
                    if((data[0].equals("Out of Stock") && !externalStockLinks.isOutOfStock())||(!data[0].equals("Out of Stock") && externalStockLinks.isOutOfStock())){
                        s.setUpdateIndicator(true);
                        accountsRepository.save(s);
                    }
                    if (data[0].equals("Out of Stock")) {
                        externalStockLinks.setPrice("Unavailable");
                        externalStockLinks.setOutOfStock(true);
                    } else {
                        externalStockLinks.setPrice(data[0]);
                        externalStockLinks.setOutOfStock(false);
                    }
                    externalStockLinks.setLastEffectiveDate(new Date());
                    externalStockLinksRepository.saveAndFlush(externalStockLinks);
                }catch (Exception e){e.printStackTrace();}
            }
        }
        return new ResponseEntity<List<ExternalStockLinks>>().withResults(list);
    }

    public ResponseEntity<List<ExternalStockLinks>> deleteContent(List<String> id, String accountId, Pageable p) {
        for(int i=0;i<id.size();i++) {
            ExternalStockLinks externalStockLinks = externalStockLinksRepository.findByIdAndAccountId(id.get(i),accountId);
            externalStockLinksRepository.delete(externalStockLinks);
        }
        Page<ExternalStockLinks> s=externalStockLinksRepository.findByOwnerIdAndAccountId(SessionUserInfo.getLoggedInUser().getUser().getId(),accountId,p);
        return new ResponseEntity<List<ExternalStockLinks>>().withResults(s.getContent()).withTotalElements(s.getTotalElements()).withTotalPages(s.getTotalPages());
    }

}
