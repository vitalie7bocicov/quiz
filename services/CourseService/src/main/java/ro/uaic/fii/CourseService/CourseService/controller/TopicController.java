package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.CourseService.CourseService.converter.TopicDtoToModel;
import ro.uaic.fii.CourseService.CourseService.dto.TopicDto;
import ro.uaic.fii.CourseService.CourseService.model.Topic;
import ro.uaic.fii.CourseService.CourseService.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getTopics() {
        List<Topic> topics = topicService.getAll();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable int id) {
        Topic topic = topicService.getById(id);
        return ResponseEntity.ok(topic);
    }

    @PostMapping
    public ResponseEntity<Topic> addTopic(@Valid @RequestBody TopicDto dto) {
        Topic topic = TopicDtoToModel.convert(dto, dto.userUid(), null);
        Topic parentTopic = null;
        if (topic.getParentId() != null) {
            parentTopic = topicService.getById(topic.getParentId());
        }
        Topic savedTopic = topicService.save(topic);
        return ResponseEntity.ok(savedTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> addTopic(@PathVariable int id,
                            @Valid @RequestBody TopicDto dto) {
        Topic topic = TopicDtoToModel.convert(dto, null, dto.userUid());
        Topic parentTopic = null;
        if (topic.getParentId() != null) {
            parentTopic = topicService.getById(topic.getParentId());
        }
        Topic updatedTopic = topicService.update(id, topic);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable int id) {
        topicService.deleteById(id);
        return ResponseEntity.ok("Topic with ID: " + id + " deleted.");
    }
}
