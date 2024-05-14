package ro.uaic.fii.CourseService.CourseService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.ImageReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.ImageResDto;
import ro.uaic.fii.CourseService.CourseService.repository.model.Image;
import ro.uaic.fii.CourseService.CourseService.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<ImageResDto>> getAllImages() {
        return ResponseEntity.ok(imageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResDto> getImageById(@PathVariable int id) {
        return ResponseEntity.ok(imageService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("content") MultipartFile file,
                                              @RequestParam("topicId") Integer topicId,
                                              @RequestParam("name") String name,
                                              @RequestParam("mimeType") String mimeType,
                                              @RequestParam("notes") String notes,
                                              @RequestParam("userUid") UUID userUid) {
        try {
            ImageReqDto dto = new ImageReqDto(topicId, name, mimeType, file.getBytes(), notes, userUid);
            ImageResDto savedImage = imageService.save(dto);
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
            ImageReqDto dto = new ImageReqDto(topicId, name, mimeType, file.getBytes(), notes, userUid);
            ImageResDto updatedImage = imageService.update(id, dto);
            return ResponseEntity.ok("Image with ID: " + updatedImage.getId() + " has been updated.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error while reading file content.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
