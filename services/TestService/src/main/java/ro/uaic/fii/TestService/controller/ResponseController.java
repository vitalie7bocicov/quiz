package ro.uaic.fii.TestService.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.TestService.converter.ResponseDtoToModel;
import ro.uaic.fii.TestService.dto.ResponseDto;
import ro.uaic.fii.TestService.model.Response;
import ro.uaic.fii.TestService.service.ParticipantService;
import ro.uaic.fii.TestService.service.QuestionService;
import ro.uaic.fii.TestService.service.ResponseService;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    private final HttpServletRequest request;
    private final ResponseService responseService;
    private final QuestionService questionService;

    private final ParticipantService participantService;
    public ResponseController(HttpServletRequest request, ResponseService responseService, QuestionService questionService, ParticipantService participantService) {
        this.request = request;
        this.responseService = responseService;
        this.questionService = questionService;
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<List<Response>> getAllResponses() {
        List<Response> responses = responseService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable Integer id) {
        Response response = responseService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> addResponse(@RequestBody ResponseDto dto) {
        Response response = ResponseDtoToModel.convert(dto,
                request.getRemoteAddr(),
                request.getLocale().getLanguage(),
                dto.userUuid(),
                null);
        questionService.getById(dto.baseQuestionId());
        participantService.getById(dto.participantId());
        return ResponseEntity.ok(responseService.save(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable Integer id,
                                                   @RequestBody ResponseDto dto) {
        Response response = ResponseDtoToModel.convert(dto,
                request.getRemoteAddr(),
                request.getLocale().getLanguage(),
                null,
                dto.userUuid());
        questionService.getById(dto.baseQuestionId());
        participantService.getById(dto.participantId());
        Response updatedResponse = responseService.update(id, response);
        return ResponseEntity.ok(updatedResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable Integer id) {
        responseService.delete(id);
        return ResponseEntity.ok("Response with ID: " + id + " deleted.");
    }
}
