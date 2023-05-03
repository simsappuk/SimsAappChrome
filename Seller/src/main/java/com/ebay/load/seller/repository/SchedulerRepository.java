package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Scheduler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchedulerRepository extends JpaRepository<Scheduler,String> {

    @Query(value = "SELECT * FROM scheduler", nativeQuery = true)
    Page<Scheduler> findAll(Pageable p);

}
