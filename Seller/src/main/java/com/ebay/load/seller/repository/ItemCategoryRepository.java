package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {


    List<ItemCategory> findBySelection(String selectedOn);

    @Transactional
    Long deleteByCategoryAndSelectionAndConsole(String modelName, String stockLog,String console);

    ItemCategory findByCategoryAndSelectionAndConsole(String content, String selectedOn, String console);

    List<ItemCategory> findBySelectionAndConsole(String selectedOn, String console);
}
