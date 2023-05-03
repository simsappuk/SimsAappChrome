package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Folders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoldersRepository extends JpaRepository<Folders,Long>{
List<Folders> findByParentIdAndAccountId(String id,String accountId);
List<Folders> findById(String id);
}