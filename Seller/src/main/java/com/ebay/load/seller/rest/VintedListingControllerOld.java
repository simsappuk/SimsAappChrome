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
    @ResponseBody
    @RequestMapping(value = "/vinted/getJsonData", method = RequestMethod.GET)
    public void getJson(@RequestParam(value = "dat", defaultValue = "{}", required = false) String dat) throws IOException {
        System.out.println("working.....:" + dat);
        JSONObject json = null;
        try {
            json = new JSONObject(dat);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // System.out.println("ok");
        String k = "";
        String itemid;
        String itemClosingAction;
        String accountId;
        String accountName;
        Double price;
        try {
            itemid = json.getString("id");
            itemClosingAction = json.getString("item_closing_action");
            accountName = json.getString("account_name");
            price = json.getDouble("original_price_numeric");
            k = vintedRepository.findExistingByItemId(itemid);
            accountId = vintedRepository.findExistingAccountByAccountName(accountName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (k.equals("0")) {
            Vinted vin = new Vinted();
            try {
                vin.setItemId(json.getString("id"));
                vin.setTitle(json.getString("title"));
                vin.setUrl(json.getString("url"));
                vin.setCreatedAt(json.getString("created_at"));
                vin.setOriginalPriceNumeric(price);
                vin.setItemClosingAction(json.getString("item_closing_action"));
                //    vin. setModifiedDate (json.getString("ModifiedDate"));
                vin.setAccountId(accountId);
                vintedRepository.save(vin);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Optional<Vinted> s = vintedRepository.findById(k);
            Vinted v1 = s.get();
            v1.setItemClosingAction(itemClosingAction);
            v1.setOriginalPriceNumeric(price);
            v1.setModifiedDate(new Date());
            vintedRepository.save(v1);
        }

    }

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
//    @RequestMapping(value = "/{accountId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, method = RequestMethod.POST)
//    public ResponseEntity<Vinted> handleImageUpload(@PathVariable("accountId") String accountId, @RequestPart("vinted") Vinted vinted,@RequestPart("imageFiles") MultipartFile[] imageFiles) throws IOException {
//        String path = "/dev-data/Projects/java/SimsAappChrome/Seller/src/main/resources/static";
//        for (MultipartFile imageFile : imageFiles) {
//            // Get the file data
//            byte[] fileData = imageFile.getBytes();
//            // Get the file name
//            String fileName = imageFile.getOriginalFilename();
//            // Get the file URL
//            String fileUrl = "http://localhost:8080/vintedStock/71527462-excelstuff/" + fileName;
//            // Perform operations with the file data and URL
//            // For example, save the file to disk or store the URL in the database
//        }
//
//        Vinted savedVinted = vintedRepository.save(vinted);
//        String uploadDir = path+"/"+accountId+"/" + savedVinted.getId();
//        return new ResponseEntity<Vinted>().withResults(new Vinted());
//    }


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

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Vinted> postVintedStock(@PathVariable("id") String id){
        Vinted s2 = vintedRepository.findOneById(id);
        if (s2 != null)
            return new ResponseEntity < Vinted> ().withResults(s2);
        else
            return null;
    }

    @RequestMapping(value = "/{id}/list/vinted", method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> loadCurrent(@PathVariable("id") String id,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,

                                                    @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return new ResponseEntity<List<Vinted>>().withResults(vintedRepository.findAllActiveListing(id));
    }

    @RequestMapping(value = "/{id}/list/vintedSold", method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> loadSold(@PathVariable("id") String id,
                                                 @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,

                                                 @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        return new ResponseEntity<List<Vinted>>().withResults(vintedRepository.findAllSoldListing(id));
    }

    @RequestMapping(value = "/{id}/list/vintedUpdateDispatch", method = RequestMethod.GET)
    public void getIds(@PathVariable("id") String id, @RequestParam(value = "data", defaultValue = "", required = false) String dat) throws IOException {
        System.out.println("working.....:" + dat);
        String[] s = dat.split(",");
        for (int i = 0; i < s.length; i++) {
            Optional<Vinted> s2 = vintedRepository.findById(s[i]);
            if (s2.isPresent()) {
                Vinted v1 = s2.get();
                v1.setItemClosingAction("Dispatched");
                vintedRepository.save(v1);

            }
        }
    }


    @RequestMapping(value = "/{id}/list/vintedDispatch", method = RequestMethod.GET)
    public ResponseEntity<List<Vinted>> loadDispatch(@PathVariable("id") String id,
                                                     @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                     @RequestParam("startDate") Date startDate,
                                                     @RequestParam("endDate") Date endDate,
                                                     @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        System.out.println(startDate);
        return new ResponseEntity<List<Vinted>>().withResults(vintedRepository.findAllDispatchListing(id,startDate, endDate));
    }
}
