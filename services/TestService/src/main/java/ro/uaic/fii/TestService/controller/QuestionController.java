package ro.uaic.fii.TestService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.TestService.dto.reqDto.QuestionReqDto;
import ro.uaic.fii.TestService.dto.resDto.QuestionResDto;
import ro.uaic.fii.TestService.service.QuestionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<QuestionResDto>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResDto> getQuestionById(@PathVariable int id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<QuestionResDto> addQuestion(@Valid @RequestBody QuestionReqDto dto) {
        QuestionResDto savedQuestion = questionService.save(dto);
        String uri = getUriString(savedQuestion);
        return ResponseEntity.created(URI.create(uri)).body(savedQuestion);
    }

    @PostMapping("/{id}/add-topic/{topicId}")
    public ResponseEntity<String> addTopicToQuestion(@PathVariable int id,
                                                     @PathVariable int topicId) {
        questionService.addTopicToQuestion(id, topicId);
        return ResponseEntity.ok("Topic with ID: " + topicId + " added to question with ID: " + id);
    }

    @DeleteMapping("/{id}/remove-topic/{topicId}")
    public ResponseEntity<String> removeTopicFromQuestion(@PathVariable int id,
                                                          @PathVariable int topicId) {
        questionService.removeTopicFromQuestion(id, topicId);
        return ResponseEntity.ok("Topic with ID: " + topicId + " removed from question with ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResDto> updateQuestion(@PathVariable int id,
                                                         @Valid @RequestBody QuestionReqDto dto) {
        return ResponseEntity.ok(questionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private String getUriString(QuestionResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
