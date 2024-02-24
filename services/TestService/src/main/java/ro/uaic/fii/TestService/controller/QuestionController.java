package ro.uaic.fii.TestService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.TestService.converter.QuestionDtoToModel;
import ro.uaic.fii.TestService.dto.QuestionDto;
import ro.uaic.fii.TestService.model.Question;
import ro.uaic.fii.TestService.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Question question = questionService.getById(id);
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDto dto) {
        Question question = QuestionDtoToModel.convert(dto, dto.userUuid(), null);
        if (dto.topicId() != null) {
            question.getTopicIds().add(dto.topicId());
        }
        return ResponseEntity.ok(questionService.save(question));
    }

    @PostMapping("/{id}/add-topic/{topicId}")
    public ResponseEntity<Question> addTopicToQuestion(@PathVariable Integer id,
                                                       @PathVariable Integer topicId) {
        Question question = questionService.getById(id);
        question.getTopicIds().add(topicId);
        return ResponseEntity.ok(questionService.save(question));
    }

    @DeleteMapping("/{id}/remove-topic/{topicId}")
    public ResponseEntity<Question> removeTopicFromQuestion(@PathVariable Integer id,
                                                            @PathVariable Integer topicId) {
        Question question = questionService.getById(id);
        question.getTopicIds().remove(topicId);
        return ResponseEntity.ok(questionService.save(question));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id,
                                                  @RequestBody QuestionDto dto) {
        Question question = QuestionDtoToModel.convert(dto, null, dto.userUuid());
        if (dto.topicId() != null) {
            question.getTopicIds().add(dto.topicId());
        }
        Question updatedQuestion = questionService.update(id, question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        questionService.delete(id);
        return ResponseEntity.ok("Question with ID: " + id + " deleted.");
    }
}
