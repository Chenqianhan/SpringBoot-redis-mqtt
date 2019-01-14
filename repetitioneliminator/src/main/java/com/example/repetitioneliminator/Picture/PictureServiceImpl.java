package com.example.repetitioneliminator.Picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;

import java.util.Map;

public class PictureServiceImpl implements PictureService {
    @Autowired
    private HashOperations hashOperations;

    @Override
    public Map<String, Picture> getAll() {
        Map<String, Picture> map=hashOperations.entries("PICTURES");
        return map;
    }

    @Override
    public void addPicture(Picture picture) {
        hashOperations.put("PCITURES",picture.getId(),picture);
    }

    @Override
    public void deletePictureById(String id) {
        hashOperations.delete("PICTURES",id);
    }

    @Override
    public Picture getPictureById(String id) {
        return (Picture) hashOperations.get("PICTURES",id);
    }

}
