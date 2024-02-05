package ro.uaic.fii.CourseService.CourseService.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.exceptions.BadRequestException;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.model.Topic;
import ro.uaic.fii.CourseService.CourseService.repository.TopicRepository;

import java.util.List;

@Service
public class TopicService {
    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    public Topic save(Topic topic) {
        try {
            return topicRepository.save(topic);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Topic getById(int id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic with ID: " + id + " not found."));
    }

    public Topic update(int id, Topic topic) {
        Topic existingTopic = getById(id);
        existingTopic.setDomainId(topic.getDomainId());
        existingTopic.setName(topic.getName());
        existingTopic.setShortName(topic.getShortName());
        existingTopic.setNotes(topic.getNotes());
        existingTopic.setParentId(topic.getParentId());
        return topicRepository.save(existingTopic);
    }

    public void deleteById(int id) {
        topicRepository.deleteById(id);
    }
}
