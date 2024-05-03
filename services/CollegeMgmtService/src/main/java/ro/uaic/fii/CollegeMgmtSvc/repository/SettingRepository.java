package ro.uaic.fii.CollegeMgmtSvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Setting;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
}
