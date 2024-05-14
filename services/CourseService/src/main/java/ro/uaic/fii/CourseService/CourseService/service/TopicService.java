package ro.uaic.fii.CourseService.CourseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.dto.mapper.TopicMapper;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.TopicReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.TopicResDto;
import ro.uaic.fii.CourseService.CourseService.exceptions.BadRequestException;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.repository.model.Topic;
import ro.uaic.fii.CourseService.CourseService.repository.TopicRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<TopicResDto> getAll() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(topicMapper::toDto).toList();
    }

    public TopicResDto save(TopicReqDto createDto) {
        if (!topicRepository.existsById(createDto.getDomainId())) {
            throw new BadRequestException("Domain", createDto.getDomainId(), "not found.");
        }
        Topic savedTopic = topicRepository.save(topicMapper.dtoToEntity(createDto));
        return topicMapper.toDto(savedTopic);
    }

    public TopicResDto getById(int id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic", id));
        return topicMapper.toDto(topic);
    }

    public TopicResDto update(int id, TopicReqDto updateDto) {
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic", id));
        existingTopic.setDomainId(updateDto.getDomainId());
        existingTopic.setName(updateDto.getName());
        existingTopic.setShortName(updateDto.getShortName());
        existingTopic.setNotes(updateDto.getNotes());
        existingTopic.setParentId(updateDto.getParentId());
        existingTopic.setUpdateUid(updateDto.getUserUid());
        Topic updatedTopic = topicRepository.save(existingTopic);
        return topicMapper.toDto(updatedTopic);
    }

    public void deleteById(int id) {
        if (!topicRepository.existsById(id)) {
            throw new NotFoundException("Topic", id);
        }
        topicRepository.deleteById(id);
    }
}
