package com.ebay.load.seller.service;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.repository.VintedDataRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class VintedDataService {

    @Autowired
    private VintedDataRepository vintedDataRepository;

    @Transactional
    public VintedData save(VintedData vintedData, String name){
        String s = vintedDataRepository.findByAccountName(name);
        Long l = vintedDataRepository.findByIdAct(s,vintedData.getAction());
        vintedData.setAccountId(s);
        if (l!=null){
            VintedData vdt1 = vintedDataRepository.findById(l).get();
            vdt1.setAction(vintedData.getAction());
            vdt1.setDescription(vintedData.getDescription());
            vdt1.setUpdatedAt(new Date());
            vintedData = vdt1;
        }else{
        vintedData.setCreatedAt(new Date());
        vintedData.setUpdatedAt(new Date());
        }
        VintedData response = vintedDataRepository.save(vintedData);
        return response;
    }
    @Transactional
    public VintedData findAllById(String id,String action) {
        String accountId = vintedDataRepository.findByAccountName(id);
        Long pId = vintedDataRepository.findByIdAct(accountId,action);
        VintedData vintedData1 = vintedDataRepository.findById(pId).get();
        return vintedData1;
    }
}