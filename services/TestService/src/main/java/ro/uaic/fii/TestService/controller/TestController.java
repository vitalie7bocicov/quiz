package ro.uaic.fii.TestService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.TestService.dto.reqDto.TestReqDto;
import ro.uaic.fii.TestService.dto.resDto.TestResDto;
import ro.uaic.fii.TestService.service.TestService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<TestResDto>> getAllTests() {
        return ResponseEntity.ok(testService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResDto> getTestById(@PathVariable int id) {
        return ResponseEntity.ok(testService.getById(id));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<TestResDto> getTestByGroupId(@PathVariable int groupId) {
        return ResponseEntity.ok(testService.getByGroupId(groupId));
    }

    @PostMapping
    public ResponseEntity<TestResDto> createTest(@Valid @RequestBody TestReqDto dto) {
        TestResDto savedTest = testService.save(dto);
        String uri = getUriString(savedTest);
        return ResponseEntity.created(URI.create(uri)).body(savedTest);
    }

    @PutMapping("/{id}")
        public ResponseEntity<TestResDto> updateTest(@PathVariable int id,
                                           @Valid @RequestBody TestReqDto dto) {
        return ResponseEntity.ok(testService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int id) {
        testService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private String getUriString(TestResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
