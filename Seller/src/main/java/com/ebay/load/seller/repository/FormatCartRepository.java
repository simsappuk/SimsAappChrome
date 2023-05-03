package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.FormatCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


        import java.util.List;

public interface FormatCartRepository<E> extends CrudRepository<FormatCart, Long> {
    List<FormatCart> findByName(String firstName);

    FormatCart findByRecords(String s);
    FormatCart findByParentRecordNumber(String s);

    FormatCart findOneById(Long id);
    List<FormatCart> findByIdOrParentRecordNumber(Long id,String parentRecordNumber);

    Page<FormatCart> findByRecord(Pageable p,String record);
}