package ro.uaic.fii.TestService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questions_generator")
    @SequenceGenerator(name = "questions_generator", sequenceName = "questions_id_seq", allocationSize = 1)
    private Integer id;
    private Integer domainId;
    private String content;
    private boolean active;
    private String notes;

    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;

    @ElementCollection
    @CollectionTable(
            name = "question_topics",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "topic_id")
    private Set<Integer> topicIds = new HashSet<>();

    public Set<Integer> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(Set<Integer> topicIds) {
        this.topicIds = topicIds;
    }

    public Question() {
    }

    public Question(Integer domainId, String content, boolean active, String notes, UUID insertUid, UUID updateUid) {
        this.domainId = domainId;
        this.content = content;
        this.active = active;
        this.notes = notes;
        this.insertUid = insertUid;
        this.updateUid = updateUid;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
