package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Roles;
import com.ebay.load.seller.repository.RolesRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RolesRepository rolesRepository;

    public ResponseEntity<List<Roles>> findAll()
    {
        return new ResponseEntity<List<Roles>>().withResults(rolesRepository.findAll());
    }
}
