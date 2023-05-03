package com.ebay.load.seller.service;


import com.ebay.load.seller.model.FormatCart;
import com.ebay.load.seller.repository.FormatCartRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AwaitingDispatchService {

    @Autowired
    FormatCartRepository<FormatCart> formatCartFormatCartRepository;

  // @Transactional
    public ResponseEntity<List<FormatCart>> findAll(Pageable p) {
       Page<FormatCart> s  = formatCartFormatCartRepository.findByRecord(p,"Parent");
       return new ResponseEntity<List<FormatCart>>().withResults(s.getContent()).withTotalElements(s.getTotalElements()).withTotalPages(p.getPageNumber());
   }
    public ResponseEntity<FormatCart> removeFormatCart(String id) {
        try {
            formatCartFormatCartRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<FormatCart>().withErrors(false).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText("FormatCart Removed...."));
        }catch(Exception e1){        }
        return new ResponseEntity<FormatCart>().withErrors(true).withMessages(new Message().withMessageType(MessageType.ERROR).withMessageText(" FormatCart not Removed...."));

    }
    public FormatCart createFormatCart(FormatCart c) {
        if(c.getId()==null)
       c.setCreatedDate(new Date());
        else c.setModifiedDate(new Date());
       return  formatCartFormatCartRepository.save(c);
    }
    public ResponseEntity<List<FormatCart>> findById(String c) {
       // c.setCreatedDate(new Date());
        FormatCart k=  formatCartFormatCartRepository.findOneById(Long.parseLong(c));
        List<FormatCart> k1  = formatCartFormatCartRepository.findByIdOrParentRecordNumber(k.getId(),k.getRecords());
        return new ResponseEntity<List<FormatCart>>().withResults(k1);

    }


}