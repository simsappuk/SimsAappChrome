package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountsService
{
    public Accounts save(Accounts accounts);
    ResponseEntity<String> delete(String id, String ownerId);
    public ResponseEntity<List<Accounts>> findAllByOwnerIdPk(String ownerIdPk, Pageable p);
    //public ResponseEntity<Accounts>findByIdAndOwnerId(String id, String ownerId);
  public ResponseEntity<Accounts>findSingleId(String ownerIdPk,String accountName);
}
