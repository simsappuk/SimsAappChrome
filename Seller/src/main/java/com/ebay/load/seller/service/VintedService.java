package com.ebay.load.seller.service;
import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.dto.EbayListing;
import com.ebay.load.seller.model.ImageModel;
import com.ebay.load.seller.model.Stock;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.util.ImageUtils;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VintedService {
    @Autowired
    VintedRepository vintedRepository;
    public Vinted postNewListing(String accountId, Vinted item) {
        Optional<Vinted> s = vintedRepository.findById(accountId);
        ApiContext apiContext = new ApiContext();
        ApiCredential cred = apiContext.getApiCredential();
        //cred.seteBayToken(s.getApiToken());
        //apiContext.setApiServerUrl(s.getUrl());
        apiContext.setSite(SiteCodeType.UK);
        return null;
    }
    public ResponseEntity <Vinted> findByAccountId(String id) {
        Vinted s2 = vintedRepository.findOneById(id);
        if (s2 != null)
            return new ResponseEntity < Vinted> ().withResults(s2);
        else
            return null;
    }
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
