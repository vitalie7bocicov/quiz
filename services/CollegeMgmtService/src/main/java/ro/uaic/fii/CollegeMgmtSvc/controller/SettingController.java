package ro.uaic.fii.CollegeMgmtSvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SettingCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SettingUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SettingDto;
import ro.uaic.fii.CollegeMgmtSvc.service.SettingService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;
    private final HttpServletRequest request;
    @GetMapping
    public ResponseEntity<List<SettingDto>> getAllSettings() {
        return ResponseEntity.ok(settingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettingDto> getSettingById(@PathVariable int id) {
        return ResponseEntity.ok(settingService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SettingDto> addSetting(@RequestBody SettingCreateDto dto) {
        SettingDto savedSetting = settingService.save(dto);
        String uri = getUriString(savedSetting);
        return ResponseEntity.created(URI.create(uri)).body(savedSetting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SettingDto> updateSetting(@PathVariable int id, @RequestBody SettingUpdateDto dto) {
        return ResponseEntity.ok(settingService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable int id) {
        settingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private String getUriString(SettingDto settingDto) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(settingDto.getId())
                .toUriString();
    }
}
