package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.UpdateOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UpdateOrdersRepository extends JpaRepository<UpdateOrders,Long> {
    UpdateOrders findByPassingDate(Date removeTime);
}
