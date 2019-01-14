package com.example.repetitioneliminator.Picture;
import java.util.Map;

public interface PictureService {
    Map<String, Picture> getAll();
    void addPicture(Picture picture);
    void deletePictureById(String id);
    Picture getPictureById(String id);
}
