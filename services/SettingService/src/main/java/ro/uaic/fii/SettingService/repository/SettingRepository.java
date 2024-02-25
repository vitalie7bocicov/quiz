package ro.uaic.fii.SettingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.SettingService.model.Setting;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
}
