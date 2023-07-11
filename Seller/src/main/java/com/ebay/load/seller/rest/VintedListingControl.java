package com.ebay.load.seller.rest;

import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.Accounts;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.model.VintedListing;
import com.ebay.load.seller.repository.AccountsRepository;
import com.ebay.load.seller.repository.VintedListingRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.MessageType;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.VintedListingService;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetMyeBaySellingCall;
import com.ebay.soap.eBLBaseComponents.ItemListCustomizationType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vintedListing")
public class VintedListingControl {

}








