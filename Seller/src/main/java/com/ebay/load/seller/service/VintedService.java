package com.ebay.load.seller.service;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.*;
import com.ebay.load.seller.repository.VintedListingRepository;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.Message;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.util.ImageUtils;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class VintedService {
    @Autowired
    VintedRepository vintedRepository;
    @Autowired
    VintedListingRepository vintedListingRepository;
    public Vinted postNewListing(String accountId, Vinted item) {
        Optional<Vinted> s = vintedRepository.findById(accountId);
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        //cred.seteBayToken(s.getApiToken());
        //apiContext.setApiServerUrl(s.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        return null;
    }
//    @Transactional
//    public ResponseEntity<String> pushToRelist(List<String> listingId, String ownerId, String accountId) {
//        for(int i=0;i<listingId.size();i++){
//            try {
//                VintedListing vintedListing = vintedListingRepository.findByIdAndOwnerIdAndAccountId(listingId.get(i), ownerId, accountId);
//                if(activeListings.getQuantityAvailable()!=0) {
//                    Relist relist = new Relist();
//                    relist.setItemId(activeListings.getItemId());
//                    relist.setTitle(activeListings.getTitle());
//                    relist.setLink("https://www.ebay.co.uk/itm/" + activeListings.getItemId());
//                    relist.setNewItemId(activeListings.getItemId());
//                    if (activeListings.getSku()!= null)
//                        relist.setSku(activeListings.getSku());
//                    if (activeListings.getQuantityAvailable() > 3)
//                        relist.setReListQuantity(3);
//                    else
//                        relist.setReListQuantity(activeListings.getQuantityAvailable());
//                    relist.setRemainingQuantity(activeListings.getQuantityAvailable());
//                    relist.setPrice(Double.valueOf(activeListings.getStartPriceValue()));
//                    relist.setLastEffectiveDate(new Date());
//                    relist.setAccountId(accountId);
//                    relist.setOwnerId(ownerId);
//                    relist.setCurrency(activeListings.getStartPriceCurrencyId());
//                    relistRepository.save(relist);
//                }
//                else
//                    return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText("Quantity UnAvailable"));
//            }catch (Exception e){
//                return new ResponseEntity<String>().withErrors(true).withMessages(new Message().withMessageText(e.getMessage()));
//            }
//        }
//        return new ResponseEntity<String>().withResults("success");
//    }

//    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
//        Set<ImageModel> imageModels =new HashSet<>();
//        for(MultipartFile files:multipartFiles){
//            ImageModel imageModel = new ImageModel(
//                    files.getOriginalFilename(),
//                    files.getContentType(),
//                    ImageUtils.compressImage(files.getBytes())
//            );
//            imageModels.add(imageModel);
//
//        }
//        return imageModels;
//    }
//    public byte[] downloadImage(String id){
//        Optional<Vinted> dbImage = vintedRepository.findById(id);
//        byte[] images = ImageUtils.decompressImage(dbImage.get().getVintedImages());
//        return images;
//    }
}
