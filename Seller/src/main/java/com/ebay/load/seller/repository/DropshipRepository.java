package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Dropship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DropshipRepository extends JpaRepository<Dropship,Long> {
    Dropship findByLink(String link);

    Dropship findOneById(String id);

    List<Dropship> findByOwnerIdAndAccountId(String id, String accountId);

    List<Dropship> findByItemId(String itemId);
}
