package com.ebay.load.seller.service;


import com.ebay.load.seller.repository.*;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static com.ebay.load.seller.rest.AwaitingDispatchController.removeTime;

@Service
@EnableScheduling
public class SchedulerService {

    @Autowired
    EbayService ebayService;

    @Autowired
    SchedulerRepository schedulerRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    SchedulerItemRepository schedulerItemRepository;

    @Autowired
    DropshipService dropshipService;

    @Autowired
    CompeteService competeService;

    @Autowired
    ExternalStockLinksService externalStockLinksService;

    @Autowired
    RelistService relistService;

    @Autowired
    ResetService resetService;

    @Autowired
    ResetRepository resetRepository;

    @Autowired
    DropshipRepository dropshipRepository;

    @Autowired
    CompeteRepository competeRepository;

    @Scheduled(cron = "0 * */8 * * * ")
    //@Scheduled(cron = "0 */15 * * * * ")
   // @Scheduled(cron = "0 */1 * * * * ")
    public void scheduler() {
        Scheduler sc = new Scheduler();
        sc.setName(System.getProperty("user.name"));
        sc.setStatus("STARTED");
        sc.setDate(new Date());
        Scheduler s2 = schedulerRepository.save(sc); //Set Status to Started
        List<Accounts> s = accountsRepository.findAll(); //Finds Existing Accounts
        if(s.size()==0){
            s2.setStatus("No Accounts Found");
            schedulerRepository.save(s2);
        }
        for (int i = 0; i < s.size(); i++) {
            Accounts s1 = s.get(i);
            Date dup = new Date();
            Date d = removeTime(dup);
            if(!s1.getInactive() && s1.getApiToken()!=null && s1.getAccountType().equals("eBay") && !s1.getAccountName().equals("Goodley") || s1.getInactive()==null)
                updates(s1,s2,d,sc);
//            if(!s1.getInactive() && s1.getApiToken()!=null && s1.getAccountType().equals("vinted") && !s1.getAccountName().equals("PoundMonkey") ||s1.getInactive()==null)
//                updateVinted(s1,s2,d,sc);

        }
        System.out.println(new Date() + " This runs in a cron schedule");
    }

    public void updates(Accounts s1,Scheduler s2,Date d,Scheduler sc) {
        try {
                dropshipService.updateEbaySellingPrice(s1.getId(), s1.getOwnerIdPk());
                competeService.updateCompeteEbaySellingPrice(s1.getId(), s1.getOwnerIdPk());
                externalStockLinksService.updateStockLinks(s1.getId(), s1.getOwnerIdPk());
                relistService.schedulerReList(s1.getOwnerIdPk(),s1.getId());
                ebayService.updatePreviousOrders(s1.getOwnerIdPk(), s1.getId(), new PageRequest(0, 200));
                ResponseEntity<List<Orders>> e = ebayService.refreshAllOrdersByOwnerId(s1.getOwnerIdPk(), s1.getId(), new PageRequest(0, 200), d, new Date());//Gets Latest Orders Data
                resetService.reviseListingPrice(s1.getId(),s1.getOwnerIdPk());
                arrangeWithPriority(s1.getOwnerIdPk(),s1.getId());
                
            if ( e == null) {
                    s2.setStatus("IAF Token has Expired");
                    s1.setInactive(true);
                    accountsRepository.save(s1);
                    schedulerRepository.save(s2);
                } else {
                    s1.setInactive(false);
                    accountsRepository.save(s1);
                    }
                    s2.setStatus(s1.getAccountName()+" executed");
                    schedulerRepository.save(s2);
        }catch(Exception e){
            sc.setStatus(e.getMessage());
            ArrayList<String> emailIds=new ArrayList<>();
            emailIds.add("meetmajid83@gmail.com");
            emailIds.add("shaiksazidh@gmail.com");
            emailIds.add("ibasha36@gmail.com");
            if(!e.getMessage().contains("401 Unauthorized")) {
                try {
                    for (int i = 0; i < emailIds.size(); i++)
                        ebayService.send("simsapp2020@gmail.com", "8333830600", emailIds.get(i), "Error in Scheduler", e.getMessage());
                }catch (Exception m){}
            }
            schedulerRepository.save(sc);
        }
    }
    public void updateVinted(Accounts s1,Scheduler s2,Date d,Scheduler sc) {
        try {
            dropshipService.updateEbaySellingPrice(s1.getId(), s1.getOwnerIdPk());
            competeService.updateCompeteEbaySellingPrice(s1.getId(), s1.getOwnerIdPk());
            externalStockLinksService.updateStockLinks(s1.getId(), s1.getOwnerIdPk());
            relistService.schedulerReList(s1.getOwnerIdPk(),s1.getId());
            ebayService.updatePreviousOrders(s1.getOwnerIdPk(), s1.getId(), new PageRequest(0, 200));
            ResponseEntity<List<Orders>> e = ebayService.refreshAllOrdersByOwnerId(s1.getOwnerIdPk(), s1.getId(), new PageRequest(0, 200), d, new Date());//Gets Latest Orders Data
            resetService.reviseListingPrice(s1.getId(),s1.getOwnerIdPk());
            arrangeWithPriority(s1.getOwnerIdPk(),s1.getId());

            if ( e == null) {
                s2.setStatus("IAF Token has Expired");
                s1.setInactive(true);
                accountsRepository.save(s1);
                schedulerRepository.save(s2);
            } else {
                s1.setInactive(false);
                accountsRepository.save(s1);
            }
            s2.setStatus(s1.getAccountName()+" executed");
            schedulerRepository.save(s2);
        }catch(Exception e){
            sc.setStatus(e.getMessage());
            ArrayList<String> emailIds=new ArrayList<>();
            emailIds.add("meetmajid83@gmail.com");
            emailIds.add("shaiksazidh@gmail.com");
            emailIds.add("ibasha36@gmail.com");
            if(!e.getMessage().contains("401 Unauthorized")) {
                try {
                    for (int i = 0; i < emailIds.size(); i++)
                        ebayService.send("simsapp2020@gmail.com", "8333830600", emailIds.get(i), "Error in Scheduler", e.getMessage());
                }catch (Exception m){}
            }
            schedulerRepository.save(sc);
        }
    }


    public ResponseEntity<List<Scheduler>> findAll(Pageable p) {
        Page<Scheduler> a = schedulerRepository.findAll(p);
        return new ResponseEntity<List<Scheduler>>().withResults(a.getContent()).withTotalElements(a.getTotalElements()).withTotalPages(a.getTotalPages());
    }

    public ResponseEntity<List<SchedulerItem>> findAllChanges(Pageable p,String schedulerId){
        Page<SchedulerItem> a = schedulerItemRepository.findAll(p,schedulerId);
        return new ResponseEntity<List<SchedulerItem>>().withResults(a.getContent()).withTotalElements(a.getTotalElements()).withTotalPages(a.getTotalPages());
    }

    public void arrangeWithPriority(String ownerId,String accountId){
        List<Reset> resetList=resetRepository.findByOwnerIdAndAccountIdAndListingHistoryNull(ownerId,accountId);
        for(int i=0;i<resetList.size();i++){
            List<Dropship> dropship=dropshipRepository.findByItemId(resetList.get(i).getEbayItemId());
            List<Compete> compete=competeRepository.findByEbayItemId(resetList.get(i).getEbayItemId());
            if(dropship.size()!=0 && !resetList.get(i).getUpdated()) {
                dropship.get(0).setPause(true);
                dropshipRepository.save(dropship.get(0));
            }
            if(compete.size()!=0 && !resetList.get(i).getUpdated()){
                compete.get(0).setPause(true);
                competeRepository.save(compete.get(0));
            }
        }
        List<Dropship> dropshipList=dropshipRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        for(int j=0;j<dropshipList.size();j++){
            List<Reset> reset=resetRepository.findByOwnerIdAndAccountIdAndEbayItemIdAndListingHistory(ownerId,accountId,dropshipList.get(j).getItemId(),null);
            if(reset.size()==0 || reset.get(0).getUpdated()){
                dropshipList.get(j).setPause(false);
                dropshipRepository.save(dropshipList.get(j));
            }
        }
        List<Compete> competeList=competeRepository.findByOwnerIdAndAccountId(ownerId,accountId);
        for(int k=0;k<competeList.size();k++){
            List<Reset> reset=resetRepository.findByOwnerIdAndAccountIdAndEbayItemIdAndListingHistory(ownerId,accountId,competeList.get(k).getEbayItemId(),null);
            List<Dropship> dropship=dropshipRepository.findByItemId(competeList.get(k).getEbayItemId());
            if(reset.size()==0 && (dropship.size()==0 || dropship.get(k).getOutOfStockPrice().equals(dropship.get(k).getSellerPrice()))){
                competeList.get(k).setPause(false);
                competeRepository.save(competeList.get(k));
            }
        }
    }


}