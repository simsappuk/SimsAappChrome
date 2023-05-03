package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Relist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelistRepository extends JpaRepository<Relist,Long> {
    List<Relist> findByOwnerIdAndAccountId(String id, String accountId);

    Relist findByAccountIdAndOwnerIdAndItemId(String accountId, String ownerId, String itemId);

    Relist findByIdAndAccountId(String s, String accountId);

    Relist findByOwnerIdAndAccountIdAndNewItemId(String ownerId, String accountId, String text);

    List<Relist> findByOwnerIdAndAccountIdAndTitleContainingIgnoreCase(String ownerId, String accountId, String text);
}
