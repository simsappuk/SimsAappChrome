package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ImageRepository extends JpaRepository<ImageModel,String> {
    ImageModel save(Set<ImageModel> imageModels);

    Optional<ImageModel> findById(Long id);

}
