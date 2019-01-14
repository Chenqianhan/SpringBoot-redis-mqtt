package com.example.repetitioneliminator.Picture;

import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class Picture implements Serializable{
    private String id;
    private String image_url;
    private boolean ifRepeat;

    public Picture() {}

    public Picture(String id,String image_url,boolean ifRepeat){
        this.id=id;
        this.image_url=image_url;
        this.ifRepeat=ifRepeat;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id=id;
    }

    public String getImage_url() { return image_url; }

    public void setImage_url(String image_url) {
        this.image_url=image_url;
    }

    public boolean getIfRepeat() { return ifRepeat; }

    public void setIfRepeat(boolean ifRepeat) {
        this.ifRepeat=ifRepeat;
    }
}
