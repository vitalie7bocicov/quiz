package ro.uaic.fii.TestService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tests_generator")
    @SequenceGenerator(name = "tests_generator", sequenceName = "tests_id_seq", allocationSize = 1)
    private Integer id;
    private Integer courseId;
    private String name;
    private Integer groupId;
    private Timestamp startTime;
    private Integer duration;
    private Integer optNumber;
    private String accessCode;
    private boolean active;
    private boolean completed;
    private  boolean demo;
    private String notes;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;

    public Test() {
    }

    public Test(Integer courseId,
                String name,
                Integer groupId,
                Timestamp startTime,
                Integer duration,
                Integer optNumber,
                String accessCode,
                boolean active,
                boolean completed,
                boolean demo,
                String notes,
                UUID insertUid,
                UUID updateUid) {
        this.courseId = courseId;
        this.name = name;
        this.groupId = groupId;
        this.startTime = startTime;
        this.duration = duration;
        this.optNumber = optNumber;
        this.accessCode = accessCode;
        this.active = active;
        this.completed = completed;
        this.demo = demo;
        this.notes = notes;
        this.insertUid = insertUid;
        this.updateUid = updateUid;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getOptNumber() {
        return optNumber;
    }

    public void setOptNumber(Integer optNumber) {
        this.optNumber = optNumber;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
