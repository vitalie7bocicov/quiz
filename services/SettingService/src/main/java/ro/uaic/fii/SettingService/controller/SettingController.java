package ro.uaic.fii.SettingService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.SettingService.dto.SettingDto;
import ro.uaic.fii.SettingService.model.Setting;
import ro.uaic.fii.SettingService.service.SettingService;

import java.util.List;

@RestController
@RequestMapping("/settings")
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public ResponseEntity<List<Setting>> getAllSettings() {
        List<Setting> settings = settingService.getAll();
        return ResponseEntity.ok(settings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setting> getSettingById(@PathVariable Integer id) {
        Setting setting = settingService.getById(id);
        return ResponseEntity.ok(setting);
    }

    @PostMapping
    public ResponseEntity<Setting> addSetting(@RequestBody SettingDto dto) {
        Setting setting = new Setting(dto.value(), dto.userUuid(), null);
        return ResponseEntity.ok(settingService.save(setting));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setting> updateSetting(@PathVariable Integer id, @RequestBody SettingDto dto) {
        Setting setting = new Setting(dto.value(), null, dto.userUuid());
        Setting updatedSetting = settingService.update(id, setting);
        return ResponseEntity.ok(updatedSetting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSetting(@PathVariable Integer id) {
        settingService.deleteById(id);
        return ResponseEntity.ok("Setting with id: " + id + " deleted.");
    }
}
