package ro.uaic.fii.CourseService.CourseService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.TopicReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.TopicResDto;
import ro.uaic.fii.CourseService.CourseService.service.TopicService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<TopicResDto>> getTopics() {
        return ResponseEntity.ok(topicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResDto> getTopicById(@PathVariable int id) {
        return ResponseEntity.ok(topicService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TopicResDto> addTopic(@Valid @RequestBody TopicReqDto dto) {
        TopicResDto savedTopic = topicService.save(dto);
        String uri = getUriString(savedTopic);
        return ResponseEntity.created(URI.create(uri)).body(savedTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResDto> addTopic(@PathVariable int id,
                            @Valid @RequestBody TopicReqDto dto) {
        return ResponseEntity.ok(topicService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int id) {
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private String getUriString(TopicResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
