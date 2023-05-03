package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.NameEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NameEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NameEntityRepository extends JpaRepository<NameEntity, Long> {

}
