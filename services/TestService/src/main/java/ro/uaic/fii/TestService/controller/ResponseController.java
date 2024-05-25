package ro.uaic.fii.TestService.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.TestService.dto.reqDto.ResponseReqDto;
import ro.uaic.fii.TestService.dto.resDto.ResponseResDto;
import ro.uaic.fii.TestService.service.ResponseService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<ResponseResDto>> getAllResponses() {
        return ResponseEntity.ok(responseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResDto> getResponseById(@PathVariable int id) {
        return ResponseEntity.ok(responseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseResDto> addResponse(@Valid @RequestBody ResponseReqDto dto) {
        dto.setIpAddress(request.getRemoteAddr());
        ResponseResDto responseResDto = responseService.save(dto);
        String uri = getUriString(responseResDto);
        return ResponseEntity.created(URI.create(uri)).body(responseResDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseResDto> updateResponse(@PathVariable int id,
                                                  @Valid @RequestBody ResponseReqDto dto) {
        dto.setIpAddress(request.getRemoteAddr());
        return ResponseEntity.ok(responseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable int id) {
        responseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private String getUriString(ResponseResDto dto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUriString();
    }
}
