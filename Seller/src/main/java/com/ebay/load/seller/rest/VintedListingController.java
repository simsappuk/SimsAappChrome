package com.ebay.load.seller.rest;
import com.ebay.load.seller.model.Vinted;
import com.ebay.load.seller.model.ImageModel;
import com.ebay.load.seller.repository.VintedRepository;
import com.ebay.load.seller.repository.ImageRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@RestController
@RequestMapping(value = "/api/Vinted")
public class VintedListingController {
    @Value("${upload.directory}")
    private String uploadDirectory;
    @Autowired
    VintedRepository vintedRepository;
    @Autowired
    ImageRepository imageRepository;
//    @PostMapping("/uploadMultipleFiles/{accountId}")
//    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@PathVariable("accountId") String accountId,@RequestPart("vinted") Vinted vinted,@RequestPart("files") MultipartFile[] files) throws IOException {
//        //return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
//        String timestamp = LocalDateTime.now().toString().replace(":", "-");
//        if (vinted.getId() == null) {
//            String s = vintedRepository.findIdByAccountId(accountId);
//            vinted.setItemId(s);
//            Vinted s1 = vintedRepository.save(vinted);
//            try {
//                Set<ImageModel> imageModels = new HashSet<>();
//                Path uploadPath = Paths.get(uploadDirectory);
//                for (MultipartFile file : files) {
//                    String fileName = timestamp + "_" + file.getOriginalFilename();
//                    ImageModel imageModel = new ImageModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());
//                    imageModel.setName(timestamp+"_"+file.getOriginalFilename());
//                    imageModel.setType(file.getContentType());
//                    ImageUtils.compressImage(imageModel.setPicByte(file.getBytes()));
//                    imageModel.setImageItemId(timestamp);
//                    vinted.setImageUrl(imageModel.getPicByte());
//                    imageModel.setVinted(vinted);
//                    imageModel.setItemId(vintedRepository.findIdByAccountId(accountId));
//                    imageModels.add(imageModel);
//                    try {
//                        Path filePath = uploadPath.resolve(fileName);
//                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//                    } catch (IOException e) {
//                        throw new IOException("Failed to save file: " + e);
//                    }
//                }
//                List<ImageModel> savedImageModels = new ArrayList<>(imageModels);
//                List<ImageModel> savedImageModel = imageRepository.saveAll(savedImageModels);
//                return new ResponseEntity < Vinted > ().withResults(s1);//.status(HttpStatus.OK)
//            } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//        }else if (vinted.getItemId() != null) {
//            Vinted s1 = updateVinted(vinted);
//            vintedRepository.save(s1);
//        }
//        return new ResponseEntity < Vinted > ().withResults(vinted);
//
//    }
//    @RequestMapping(value = "/post/{accountId}", method = RequestMethod.POST)
//    public ResponseEntity<Vinted> postVintedListing(@PathVariable("accountId") String accountId, Vinted vinted ){       //@RequestPart("images") MultipartFile[] imageUrlData,
////        Optional<Vinted> s = vintedRepository.findById(accountId);
//        //String profilePictureFileName = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
//        if (vinted.getId() == null) {
//            String s = vintedRepository.findIdByAccountId(accountId);
//            vinted.setItemId(s);
//            vinted.setImageUrl(vinted.getImageUrl());
//            Vinted s1 = vintedRepository.save(vinted);
//            //uploadMultipleFiles(accountId,s1,file);
//            return new ResponseEntity < Vinted > ().withResults(s1);//.status(HttpStatus.OK)
//        } else if (vinted.getItemId() != null) {
//            Vinted s1 = updateVinted(vinted);
//            vintedRepository.save(s1);
//        }
//        return new ResponseEntity < Vinted > ().withResults(vinted);
//    }
//    @RequestMapping(value = "/vintedImages/{accountId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, method = RequestMethod.POST)
//    public ResponseEntity handleFilesUpload(@PathVariable("accountId") String accountId, @RequestParam("images") MultipartFile[] files) {
//        String timestamp = LocalDateTime.now().toString().replace(":", "-");
//        return null;
//    }

//    The code to convert Image Url to Byte Array is below.
    public static byte[] convertImageByte(URL url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = new BufferedInputStream(url.openStream());
            byte[] byteChunk = new byte[4096];
            int n;
            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
            return baos.toByteArray();
        }
        catch (IOException e) {e.printStackTrace ();}
        finally {
            if (is != null) { is.close(); }
        }
        return null;
    }

    //Get Image From Database
    public byte[] getImageForOrderItem(long itemId) {
        Optional<ImageModel> option = imageRepository.findById(itemId);
        if(option.isPresent()) {
            ImageModel itemDO = option.get();
            if(itemDO.getPicByte() != null) {
                byte[] image = itemDO.getPicByte();
                return image;
            }
        }
        return null;
    }
    //Calling Image Response through Rest API
//    @GetMapping(path="/orderItem/image/{itemId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void getImageForOrderItem(@PathVariable("itemId") long itemId, HttpServletResponse response) {
//        byte[] buffer = orderServiceImpl.getImageForOrderItem(itemId);
//        if (buffer != null) {
//            response.setContentType("image/jpeg");
//            try {
//                response.getOutputStream().write(buffer);
//                response.getOutputStream().flush();
//                response.getOutputStream().close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @RequestMapping(value = "/post/{accountId}",method = RequestMethod.POST)
    public ResponseEntity postVintedListing(@PathVariable("accountId") String accountId, @RequestBody Vinted vinted) throws IOException {       //@RequestPart("images") MultipartFile[] imageUrlData,
        if (vinted.getId() == null) {
            String s = vintedRepository.findIdByAccountId(accountId);
            vinted.setItemId(s);
            Vinted s1 = vintedRepository.save(vinted);
            ImageModel imageModel = new ImageModel();
            if(s1.getImageUrl() != null) {
                try {
                    URL imageUrl = new URL(s1.getImageUrl());
                    imageModel.setPicByte(convertImageByte(imageUrl));
                    imageModel.setId(s1.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imageModel = imageRepository.save(imageModel);
            return new ResponseEntity < Vinted > ().withResults(s1);//.status(HttpStatus.OK)
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

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Vinted> postVintedStock(@PathVariable("id") String id){
        Vinted s2 = vintedRepository.findOneById(id);
        if (s2 != null)
            return new ResponseEntity < Vinted> ().withResults(s2);
        else
            return null;
    }
}