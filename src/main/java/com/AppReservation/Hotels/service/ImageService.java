package com.AppReservation.Hotels.service;

import com.AppReservation.Hotels.model.Hotel;
import com.AppReservation.Hotels.model.Image;
import com.AppReservation.Hotels.repository.ImageRepository;
import com.AppReservation.Hotels.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {

        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (image != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(long id){
        Optional<Image> dbImageData = imageRepository.findById(id);
        byte[] imageShow= ImageUtils.decompressImage(dbImageData.get().getImageData());
        return imageShow;
    }

    //Mise à jour image
    public String updateImage(long id, MultipartFile file) throws IOException {
        Optional<Image> existingImageOpt = imageRepository.findById(id);
        if (existingImageOpt.isPresent()) {
            Image existingImage = existingImageOpt.get();
            existingImage.setName(file.getOriginalFilename());
            existingImage.setType(file.getContentType());
            existingImage.setImageData(ImageUtils.compressImage(file.getBytes()));
            imageRepository.save(existingImage);
            return "Image updated successfully : " + file.getOriginalFilename();
        }
        return "Image not found";
    }

    public void deleteImage(long id) { imageRepository.deleteById(id);  }


    //Liste de toutes les images
    public List< Image> getImages() {
        List<Image> images = new ArrayList<>();
        imageRepository.findAll().forEach(image -> {
            images.add(image);
        });
        return images;
    }

}