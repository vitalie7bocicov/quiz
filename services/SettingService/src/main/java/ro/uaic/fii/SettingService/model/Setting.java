package ro.uaic.fii.SettingService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "settings_generator")
    @SequenceGenerator(name = "settings_generator", sequenceName = "settings_id_seq", allocationSize = 1)
    private Integer id;

    private String value;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;

    public Setting(String value, UUID insertUid, UUID updateUid) {
        this.value = value;
        this.insertUid = insertUid;
        this.updateUid = updateUid;
    }

    public Setting() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getInsertUid() {
        return insertUid;
    }

    public void setInsertUid(UUID insertUid) {
        this.insertUid = insertUid;
    }

    public UUID getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(UUID updateUid) {
        this.updateUid = updateUid;
    }

    public Integer getId() {
        return id;
    }

    public Date getInsertTimestamp() {
        return insertTimestamp;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }
}
