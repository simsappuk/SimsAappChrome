package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.ActiveListings;
import com.ebay.load.seller.model.Scheduler;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.SchedulerRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.ebay.load.seller.rest.AwaitingDispatchController.removeTime;

@Service
@EnableScheduling
public class ExtraSchedulerService {
    @Autowired
    OrdersServiceImpl ordersService;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    SchedulerRepository schedulerRepository;

    @Autowired
    EbayService ebayService;

    @Scheduled(cron = "0 50 */2 * * * ")
    public void Scheduler() throws ParseException {

        Scheduler sc = new Scheduler();
        sc.setName(System.getProperty("user.name"));
        sc.setStatus("Extra Scheduler Started");
        sc.setDate(new Date());
        Scheduler s2 = schedulerRepository.save(sc);
      List<Accounts> s = accountsRepository.findAll();
        if(s.size()==0){
            s2.setStatus("No Accounts Found");
            schedulerRepository.save(s2);
        }
        for (int i = 0; i < s.size(); i++) {
            Accounts s1 = s.get(i);
            if(!s1.getInactive() && s1.getApiToken()!=null && s1.getAccountType().equals("eBay") || s1.getInactive()==null) {
                try {
                    ordersService.refreshPurchaseOrders(new PageRequest(0, 200), s1.getId(), s1.getOwnerIdPk());
                    if(s1.getAccountName().equals("PoundMonkey"))
                        ordersService.updatePurchaseOrderStatusWithMessage(s1);
                    ebayService.updateDatabase(s1,new PageRequest(1,200));
                    ebayService.convertImageUrl();
                }catch (Exception e){
                    sc.setStatus(e.getMessage());
                    if(!e.getMessage().contains("401 Unauthorized"))
                       ebayService.send("simsapp2020@gmail.com","8333830600","shaiksazidh@gmail.com","Error in Scheduler",e.getMessage());
                    schedulerRepository.save(sc);
                }
            }
            else {
                sc.setStatus("Please activate the Account: "+s1.getAccountName());
                schedulerRepository.save(sc);
            }
        }}
}
