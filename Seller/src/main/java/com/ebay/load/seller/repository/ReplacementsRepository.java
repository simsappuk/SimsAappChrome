package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Replacements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplacementsRepository extends JpaRepository<Replacements,String> {
    Replacements findByRecordNumber(Integer recordNumber);
}
