package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.ExternalStockLinks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExternalStockLinksRepository extends JpaRepository<ExternalStockLinks,Long> {

    ExternalStockLinks findByCexIdAndAccountId(String cexId, String accountId);

    ExternalStockLinks findByLinkAndAccountId(String magpieLink, String accountId);

    List<ExternalStockLinks> findByOwnerIdAndAccountId(String id, String accountId);

    Page<ExternalStockLinks> findByOwnerIdAndAccountId(String id, String accountId, Pageable p);

    ExternalStockLinks findByIdAndAccountId(String s, String accountId);
}
