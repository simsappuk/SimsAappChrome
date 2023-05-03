package com.ebay.load.seller.service;

import com.ebay.load.seller.model.LabelUpdates;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Replacements;
import com.ebay.load.seller.repository.LabelUpdatesRepository;
import com.ebay.load.seller.repository.ReplacementsRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LabelUpdateService {

    @Autowired
    LabelUpdatesRepository labelUpdatesRepository;

    @Autowired
    ReplacementsRepository replacementsRepository;

    public ResponseEntity<List<Orders>> savePrintedData(String id, String accountId, String initialRecordNum, String finalRecordNum,String selected,List<String> range) {
        try {
            LabelUpdates labelUpdates = new LabelUpdates();
            labelUpdates.setAccountId(accountId);
            labelUpdates.setInitialRecordNumber(initialRecordNum);
            if (finalRecordNum != null)
                labelUpdates.setFinalRecordNumber(finalRecordNum);
            if(!range.get(0).equals("null"))
                labelUpdates.setRange(range);
            labelUpdates.setOwnerId(id);
            labelUpdates.setSelected(selected);
            labelUpdates.setLastEffectiveDate(removeTime(new Date()));
            labelUpdatesRepository.save(labelUpdates);
            return new ResponseEntity<>().withErrors(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>().withErrors(true);
    }

    public ResponseEntity<List<Orders>> getPrintedData(String id, String accountId,Date date,String label,String type) {
        if(date!=null && (type!=null && type.equals("date"))) {
            List<LabelUpdates> labelUpdatesList = labelUpdatesRepository.findByOwnerIdAndAccountIdAndLastEffectiveDate(id, accountId, removeTime(date));
            return new ResponseEntity<>().withResults(labelUpdatesList);
        }
        else if(date==null && (type!=null && type.equals("label"))){
            List<LabelUpdates> labelUpdatesList = labelUpdatesRepository.findByOwnerIdAndAccountIdAndRange(id, accountId,label);
            return new ResponseEntity<>().withResults(labelUpdatesList);
        }
        else if(date==null){
            List<LabelUpdates> labelUpdatesList = labelUpdatesRepository.findByOwnerIdAndAccountIdAndLastEffectiveDate(id, accountId, removeTime(new Date()));
            return new ResponseEntity<>().withResults(labelUpdatesList);
        }
        else{
            List<LabelUpdates> labelUpdatesList = labelUpdatesRepository.findByOwnerIdAndAccountIdAndLastEffectiveDate(id, accountId, removeTime(date));
            return new ResponseEntity<>().withResults(labelUpdatesList);
        }
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public ResponseEntity <Replacements> saveReplacements(Replacements replacements){
        Replacements exist=replacementsRepository.findByRecordNumber(replacements.getRecordNumber());
            try {
                if(exist==null) {
                    replacements.setUpdatedOn(new Date());
                    replacementsRepository.save(replacements);
                }
            }catch (Exception e){
                return new ResponseEntity <Replacements>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(e.getMessage()));

            }
        return null;
    }

    public ResponseEntity < List < Replacements >> getReplacements(){
     List<Replacements> list=replacementsRepository.findAll();
     return new ResponseEntity<>().withResults(list);
    }

}


