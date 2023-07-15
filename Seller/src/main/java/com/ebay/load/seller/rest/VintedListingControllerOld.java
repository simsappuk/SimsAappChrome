package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.ImageModel;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.repository.ImageRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.VintedService;
import com.ebay.load.seller.util.ImageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.core.io.Resource;

import javax.servlet.annotation.MultipartConfig;

public class VintedListingControllerOld {
    @Value("${upload.directory}")
    private String uploadDirectory;
    @Autowired
    VintedRepository vintedRepository;
    @Autowired
    ImageRepository imageRepository;

    VintedService vintedService;


    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex);
        }
        return "";
    }

    @RequestMapping(value = "/upload" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<List<String>> uploadImages(@RequestPart("images") MultipartFile[] files) {
        List<String> base64Images = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                // Read the file content as bytes
                byte[] fileContent = file.getBytes();
                // Encode the file content as base64
                String base64Image = Base64Utils.encodeToString(fileContent);
                // Add the base64 encoded image to the list
                base64Images.add(base64Image);
            } catch (IOException e) {
                // Handle any errors that occur during file processing
                e.printStackTrace();
                return null;
            }
        }
        // Return the list of base64 encoded images
        return null;
    }
    @RequestMapping(value = "/upload1/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<List<ImageModel>> handleFilesUpload(@PathVariable("accountId") String accountId, @RequestParam("images") MultipartFile[] files) {
        //String uploadDir = "/dev-data/Projects/irshad/";
        String timestamp = LocalDateTime.now().toString().replace(":", "-");
        try {
            Set<ImageModel> imageModels = new HashSet<>();
            Path uploadPath = Paths.get(uploadDirectory);
            for (MultipartFile file : files) {
                String fileName = timestamp + "_" + file.getOriginalFilename();
                ImageModel imageModel = new ImageModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());
                imageModel.setName(timestamp+"_"+file.getOriginalFilename());
                imageModel.setType(file.getContentType());
                ImageUtils.compressImage(imageModel.setPicByte(file.getBytes()));
                imageModel.setImageItemId(timestamp);
                imageModel.setItemId(vintedRepository.findIdByAccountId(accountId));
                imageModels.add(imageModel);
                try {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new IOException("Failed to save file: " + fileName, e);
                }
            }
            List<ImageModel> savedImageModels = new ArrayList<>(imageModels);
            List<ImageModel> savedImageModel = imageRepository.saveAll(savedImageModels);
            return new ResponseEntity(savedImageModel);
        } catch (IOException e) {
            return new ResponseEntity<String>().withResults("Failed to upload files: "+ e.getMessage());
        }

    }


    @RequestMapping(value = "/images/{imageName}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} , method = RequestMethod.GET)
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        //String uploadDir = "/dev-data/Projects/irshad/";
        Path imagePath = Paths.get(uploadDirectory + "/" + imageName);
        Resource imageResource = (Resource) new UrlResource(imagePath.toUri());
        if (imageResource.exists() && imageResource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<HttpHeaders>().withResults(headers);
        } else {
            return new ResponseEntity<String>().withResults("Files download successfully.");
        }
    }

    public ImageModel updateImageModel(ImageModel s) {
        //s.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
        Optional<ImageModel> s2 = imageRepository.findById(s.getId());
        imageRepository.save((Set<ImageModel>) s);
        return s;
    }
    @RequestMapping(value = "/post/{accountId}",method = RequestMethod.POST)
    public ResponseEntity<Vinted> postVintedListing(@PathVariable("accountId") String accountId,Vinted vinted) throws IOException {       //@RequestPart("images") MultipartFile[] imageUrlData,
//        Optional<Vinted> s = vintedRepository.findById(accountId);
        //String profilePictureFileName = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
        if (vinted.getId() == null) {
            String s = vintedRepository.findIdByAccountId(accountId);
            vinted.setItemId(s);
            vinted.setImageUrl(vinted.getImageUrl());
            Vinted s1 = vintedRepository.save(vinted);

            return new ResponseEntity < Vinted > ().withResults(s1);//.status(HttpStatus.OK)
            //String uploadDir = accountId+"/" +s+"/" + s1.getId();
//            try {
//                Set<ImageModel> imageModels =new HashSet<>();
//                for(MultipartFile files:file){
//                    ImageModel imageModel = new ImageModel(
//                            files.getOriginalFilename(),
//                            files.getContentType(),
//                            ImageUtils.compressImage(files.getBytes())
//                    );
//                    imageModels.add(imageModel);
//
//                }
//                //return imageModels;
//                //Set<ImageModel> images =vintedService.uploadImage(file);
//                vinted.setVintedImages(imageModels);
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

        } else if (vinted.getItemId() != null) {
            Vinted s1 = updateVinted(vinted);
            vintedRepository.save(s1);
        }
        return new ResponseEntity < Vinted > ().withResults(vinted);
    }


    public Vinted updateVinted(Vinted s) {
        //s.setModifiedBy(SessionUserInfo.getLoggedInUser().getUser().getId());
        s.setModifiedDate(new Date());
        Optional<Vinted> s2 = vintedRepository.findById(s.getId());
        vintedRepository.save(s);
        return s;
    }
    @RequestMapping(value = "/vintedStockData/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<Vinted> postVintedListing2(@PathVariable("accountId") String accountId){
        return new ResponseEntity<Vinted>().withResults(new Vinted());
    }
    @RequestMapping(value = "/vintedStock/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> postVintedListing1(@PathVariable("accountId") String accountId){
        String s = vintedRepository.findIdByAccountId(accountId);
        List<Vinted> vinted = vintedRepository.findByItemId(s);
        return new ResponseEntity<List<Vinted>>().withResults(vinted);

    }

    @RequestMapping(value = "/vintedItemDelete/{accountId}/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> deleteVintedItem(@PathVariable("accountId") String accountId,@PathVariable("id") String id){
        String s = vintedRepository.findIdByAccountId(accountId);
        List<Vinted> vinted = vintedRepository.deleteStockById(s,id);
        return new ResponseEntity<List<Vinted>>().withResults(vinted);

    }

}
