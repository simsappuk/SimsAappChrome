package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<Pdf,Long> {
}
