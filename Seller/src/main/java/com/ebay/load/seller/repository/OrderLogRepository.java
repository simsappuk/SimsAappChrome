package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog,Long> {

    OrderLog findBySellingRecordNumber(String sellingRecordNumber);
}
