package com.AppReservation.Hotels.controller;

import com.AppReservation.Hotels.model.Hotel;
import com.AppReservation.Hotels.model.Image;
import com.AppReservation.Hotels.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    //Obtenir une image avec son id
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id){
        byte[] imageData= imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    //Mise à jour d'une image
    @RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
    public ResponseEntity<?> updateImage(@PathVariable long id, @RequestParam("image") MultipartFile file) throws IOException {
        String updateMessage = imageService.updateImage(id, file);
        if (updateMessage.contains("successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(updateMessage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateMessage);
        }
    }

    //Obtenir toutes les images:
    @RequestMapping(method = RequestMethod.GET, value = "/images")
    public List<Image> getImages(){return imageService.getImages();}

    //Supprimer une image
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteHotel(@PathVariable long id){imageService.deleteImage(id);}
}