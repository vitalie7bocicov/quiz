package ro.uaic.fii.ImageService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.ImageService.exceptions.NotFoundException;
import ro.uaic.fii.ImageService.model.Image;
import ro.uaic.fii.ImageService.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public Image getById(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image with ID: " + id + " not found."));
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public void deleteById(Integer id) {
        imageRepository.deleteById(id);
    }

    public Image update(Integer id, Image image) {
        Image imageToUpdate = getById(id);
        imageToUpdate.setTopicId(image.getTopicId());
        imageToUpdate.setName(image.getName());
        imageToUpdate.setMimeType(image.getMimeType());
        imageToUpdate.setContent(image.getContent());
        imageToUpdate.setNotes(image.getNotes());
        imageToUpdate.setUpdateUid(image.getUpdateUid());
        return imageRepository.save(imageToUpdate);
    }
}
