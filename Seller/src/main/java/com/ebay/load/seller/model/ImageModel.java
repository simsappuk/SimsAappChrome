package com.ebay.load.seller.model;

import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name="image")
public class ImageModel extends ResponseEntity<ImageModel> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String type;
    private String itemId;
    @Column(length = 50000000)
    private byte[] picByte;
    @ManyToOne
    @JoinColumn(name = "vinted", referencedColumnName = "id")
    private Vinted vinted;

    public ImageModel(){}
    public ImageModel(String originalFilename, String contentType, byte[] bytes) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vinted getVinted() {
        return vinted;
    }

    public void setVinted(Vinted vinted) {
        this.vinted = vinted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public byte[] setPicByte(byte[] picByte) {
        this.picByte = picByte;
        return picByte;
    }
}
