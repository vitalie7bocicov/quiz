package ro.uaic.fii.TestService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.TestService.converter.TestReqDtoToModel;
import ro.uaic.fii.TestService.dto.TestReqDto;
import ro.uaic.fii.TestService.model.Test;
import ro.uaic.fii.TestService.service.TestService;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAll();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Integer id) {
        Test test = testService.getById(id);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Test> getTestByGroupId(@PathVariable Integer groupId) {
        Test test = testService.getByGroupId(groupId);
        return ResponseEntity.ok(test);
    }

    @PostMapping
    public ResponseEntity<Test> createTest(@RequestBody TestReqDto testReqDto) {
        Test test = TestReqDtoToModel.convert(testReqDto, testReqDto.userUid(), null);
        Test savedTest = testService.save(test);
        return ResponseEntity.ok(savedTest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable Integer id,
                                           @RequestBody TestReqDto testReqDto) {
        Test test = TestReqDtoToModel.convert(testReqDto, null, testReqDto.userUid());
        Test updatedTest = testService.update(id, test);
        return ResponseEntity.ok(updatedTest);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTopic(@PathVariable Integer id) {
        testService.deleteById(id);
        return ResponseEntity.ok("Test with ID: " + id + " deleted.");
    }
}
