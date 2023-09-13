package com.AppReservation.Hotels.service;

import com.AppReservation.Hotels.model.Hotel;
import com.AppReservation.Hotels.model.Image;
import com.AppReservation.Hotels.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image createImage(byte[] imageData) {
        Image image = new Image();
        return imageRepository.save(image);
    }

    public List<Image> getImages() {
        List<Image> images = new ArrayList<>();
        imageRepository.findAll().forEach(image -> {
            images.add(image);
        });
        return images;
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    // Vous pouvez ajouter d'autres méthodes pour gérer les images (mise à jour, liste, etc.)
}
