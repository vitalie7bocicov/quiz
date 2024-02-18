package ro.uaic.fii.TestService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjects_generator")
    @SequenceGenerator(name = "subjects_generator", sequenceName = "subjects_id_seq", allocationSize = 1)
    private Integer id;
    private Integer testId;
    private Integer sectionId;
    private Integer topicId;
    private Character qsType;
    private Integer qsNumber;
    private Integer orderNumber;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;

    public Subject() {
    }

    public Subject(Integer testId, Integer sectionId, Integer topicId, Character qsType, Integer qsNumber, Integer orderNumber, UUID insertUid, UUID updateUid) {
        this.testId = testId;
        this.sectionId = sectionId;
        this.topicId = topicId;
        this.qsType = qsType;
        this.qsNumber = qsNumber;
        this.orderNumber = orderNumber;
        this.insertUid = insertUid;
        this.updateUid = updateUid;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Character getQsType() {
        return qsType;
    }

    public void setQsType(Character qsType) {
        this.qsType = qsType;
    }

    public Integer getQsNumber() {
        return qsNumber;
    }

    public void setQsNumber(Integer qsNumber) {
        this.qsNumber = qsNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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
