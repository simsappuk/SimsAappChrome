package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ImageRepository extends JpaRepository<ImageModel,String> {
    ImageModel save(Set<ImageModel> imageModels);

    Optional<ImageModel> findById(Long id);

    @Query(value = "SELECT * FROM image where item_id=?1",nativeQuery = true)
    List<ImageModel> findByItemId(String s);
}
