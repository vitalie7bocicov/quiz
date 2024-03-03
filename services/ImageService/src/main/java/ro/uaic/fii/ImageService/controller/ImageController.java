package ro.uaic.fii.ImageService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.uaic.fii.ImageService.converter.ImageDtoToModel;
import ro.uaic.fii.ImageService.dto.ImageDto;
import ro.uaic.fii.ImageService.model.Image;
import ro.uaic.fii.ImageService.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAll();
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Integer id) {
        Image image = imageService.getById(id);
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("content") MultipartFile file,
                                              @RequestParam("topicId") Integer topicId,
                                              @RequestParam("name") String name,
                                              @RequestParam("mimeType") String mimeType,
                                              @RequestParam("notes") String notes,
                                              @RequestParam("userUid") UUID userUid) {
        try {
            ImageDto dto = new ImageDto(topicId, name, mimeType, file.getBytes(), notes, userUid);
            Image image = ImageDtoToModel.convert(dto, dto.userUid(), null);
            Image savedImage = imageService.save(image);
            return ResponseEntity.ok("Image with ID: " + savedImage.getId() + " has been uploaded.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error while reading file content.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> uploadImage(@PathVariable Integer id,
                                              @RequestParam("content") MultipartFile file,
                                              @RequestParam("topicId") Integer topicId,
                                              @RequestParam("name") String name,
                                              @RequestParam("mimeType") String mimeType,
                                              @RequestParam("notes") String notes,
                                              @RequestParam("userUid") UUID userUid) {
        try {
            ImageDto dto = new ImageDto(topicId, name, mimeType, file.getBytes(), notes, userUid);
            Image image = ImageDtoToModel.convert(dto, dto.userUid(), null);
            Image updatedImage = imageService.update(id, image);
            return ResponseEntity.ok("Image with ID: " + updatedImage.getId() + " has been updated.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error while reading file content.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
        imageService.deleteById(id);
        return ResponseEntity.ok("Image with ID: " + id + " has been deleted.");
    }
}
