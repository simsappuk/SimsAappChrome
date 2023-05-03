package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Channel;
import com.ebay.load.seller.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,String>
{
    Page<Channel>findAll(Pageable p);

    Page<Channel> findByLogNameNotNull(Pageable p);

    Page<Channel> findByLogNameNull(Pageable p);
}