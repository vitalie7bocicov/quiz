package ro.uaic.fii.CourseService.CourseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.dto.SectionTopicDto;
import ro.uaic.fii.CourseService.CourseService.dto.mapper.SectionMapper;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.SectionReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.SectionResDto;
import ro.uaic.fii.CourseService.CourseService.exceptions.BadRequestException;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.repository.CourseRepository;
import ro.uaic.fii.CourseService.CourseService.repository.TopicRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Section;
import ro.uaic.fii.CourseService.CourseService.repository.SectionRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Topic;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;
    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final SectionMapper sectionMapper;

    public List<SectionResDto> getAll() {
        List<Section> sections = sectionRepository.findAll();
        return sections.stream().map(sectionMapper::toDto).toList();
    }

    public SectionResDto getById(int id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Section", id));
        return sectionMapper.toDto(section);
    }

    public SectionResDto save(SectionReqDto createDto) {
        if (!courseRepository.existsById(createDto.getCourseId())) {
            throw new BadRequestException("Course", createDto.getCourseId(), "not found.");
        }
        Section savedSection = sectionRepository.save(sectionMapper.toEntity(createDto));
        return sectionMapper.toDto(savedSection);
    }

    public SectionResDto update(int id, SectionReqDto updateDto) {
        Section existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Section", id));
        existingSection.setCourseId(updateDto.getCourseId());
        existingSection.setName(updateDto.getName());
        existingSection.setNotes(updateDto.getNotes());
        existingSection.setOrderNumber(updateDto.getOrderNumber());
        existingSection.setUpdateUid(updateDto.getUserUid());
        Section updatedSection = sectionRepository.save(existingSection);
        return sectionMapper.toDto(updatedSection);
    }

    public void deleteById(int id) {
        if (!sectionRepository.existsById(id)) {
            throw new NotFoundException("Section", id);
        }
        sectionRepository.deleteById(id);
    }

    public void addTopicToSection(SectionTopicDto sectionTopicDto) {
        Section section = sectionRepository.findById(sectionTopicDto.sectionId())
                .orElseThrow(() -> new NotFoundException("Section", sectionTopicDto.sectionId()));
        Topic topic = topicRepository.findById(sectionTopicDto.topicId())
                .orElseThrow(() -> new NotFoundException("Topic", sectionTopicDto.topicId()));
        section.getTopics().add(topic);
        sectionRepository.save(section);
    }
}
