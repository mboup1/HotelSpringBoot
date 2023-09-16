package com.AppReservation.Hotels.service;

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
    public List< Image> getImages() {
        List<Image> images = new ArrayList<>();
        imageRepository.findAll().forEach(image -> {
            images.add(image);
        });
        return images;
    }
    public void deleteImage(long id) { imageRepository.deleteById(id);  }

}