package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Roles;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;

import java.util.List;

public interface RoleService
{
    public ResponseEntity<List<Roles>> findAll();

}
