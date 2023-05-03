package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.SchedulerItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchedulerItemRepository extends JpaRepository<SchedulerItem,String> {

    @Query(value = "SELECT * FROM scheduler_Item where scheduler_Id=?1", nativeQuery = true)
    Page<SchedulerItem> findAll(Pageable p,String schedulerId);
}
