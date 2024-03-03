package ro.uaic.fii.ImageService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.ImageService.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
