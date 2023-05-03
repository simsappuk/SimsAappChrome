package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Condition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Condition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

}
