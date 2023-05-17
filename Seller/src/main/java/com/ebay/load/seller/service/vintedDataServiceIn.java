package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.VintedData;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface vintedDataServiceIn {
    public VintedData save(VintedData vintedData);

    ResponseEntity<String> delete(String accountId, String action);

    public ResponseEntity<List<VintedData>> findAllByAccountIdPk(String accountIdPk, Pageable p);
        //public ResponseEntity<Accounts>findByIdAndOwnerId(String id, String ownerId);
    public ResponseEntity<VintedData>findSingleId(String accountIdPk,String action);
    }
