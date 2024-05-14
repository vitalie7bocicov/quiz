package ro.uaic.fii.CourseService.CourseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.dto.mapper.ImageMapper;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.ImageReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.ImageResDto;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.repository.ImageRepository;
import ro.uaic.fii.CourseService.CourseService.repository.TopicRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Image;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final TopicRepository topicRepository;
    private final ImageMapper imageMapper;
    public List<ImageResDto> getAll() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(imageMapper::toDto).toList();
    }

    public ImageResDto getById(int id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image", id));
        return imageMapper.toDto(image);
    }

    public ImageResDto save(ImageReqDto dto) {
       if(!topicRepository.existsById(dto.getTopicId())) {
           throw new NotFoundException("Topic", dto.getTopicId());
       }
       Image image = imageMapper.toEntity(dto);
       Image savedImage = imageRepository.save(image);
       return imageMapper.toDto(savedImage);
    }

    public ImageResDto update(int id, ImageReqDto dto) {
        Image imageToUpdate = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image", id));
        if(!topicRepository.existsById(dto.getTopicId())) {
            throw new NotFoundException("Topic", dto.getTopicId());
        }
        imageToUpdate.setTopicId(dto.getTopicId());
        imageToUpdate.setName(dto.getName());
        imageToUpdate.setMimeType(dto.getMimeType());
        imageToUpdate.setContent(dto.getContent());
        imageToUpdate.setNotes(dto.getNotes());
        imageToUpdate.setUpdateUid(dto.getUserUid());
        Image updatedImage = imageRepository.save(imageToUpdate);
        return imageMapper.toDto(updatedImage);
    }

    public void deleteById(int id) {
        if (!imageRepository.existsById(id)) {
            throw new NotFoundException("Image", id);
        }
        imageRepository.deleteById(id);
    }
}
