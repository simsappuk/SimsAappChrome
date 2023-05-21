package com.ebay.load.seller.service;

import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.repository.VintedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VintedDataService {

    @Autowired
    private VintedDataRepository vintedDataRepository;

    public VintedData save(VintedData vintedData, String name){
        String s = vintedDataRepository.findByAccountName(name);
        Long l = vintedDataRepository.findById(s,vintedData.getAction());
        vintedData.setAccountId(s);
        if (l!=null){
            VintedData vdt1 = vintedDataRepository.findOneById(l);
            vdt1.setText(vintedData.getText());
            vdt1.setUpdatedAt(new Date());
            vintedData = vdt1;
        }
        VintedData response = vintedDataRepository.save(vintedData);
        return response;
    }

}