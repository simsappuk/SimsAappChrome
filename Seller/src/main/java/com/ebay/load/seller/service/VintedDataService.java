package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.repository.SpreadSheetRepository;
import com.ebay.load.seller.repository.VintedDataRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VintedDataService {

        @Autowired
        private static VintedDataRepository vintedDataRepository;
        @Autowired
        private SpreadSheetRepository spreadSheetRepository;

        @Transactional
        public static VintedData save(VintedData vintedData) {

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                vintedData.setAction(bCryptPasswordEncoder.encode(vintedData.getAction()));
                VintedData vintedData1 = vintedDataRepository.save(vintedData);
                return vintedData1;
            }

        public ResponseEntity<List<VintedData>> findAllByAccountId(String accountId, Pageable p) {
            Page<VintedData> vintedData = vintedDataRepository.findByAccountId(accountId, p);
            return new ResponseEntity<List<VintedData>>().withResults(vintedData.getContent()).withTotalPages(vintedData.getTotalPages()).withTotalElements(vintedData.getTotalElements());




        }
}