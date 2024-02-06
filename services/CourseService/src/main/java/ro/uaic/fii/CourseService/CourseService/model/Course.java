package ro.uaic.fii.CourseService.CourseService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @SequenceGenerator(name = "course_sequence", sequenceName = "courses_id_seq", allocationSize = 1)
    private Integer id;
    private Integer domainId;
    private Integer sessionId;
    private String abbr;
    private String name;
    private String notes;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;

    private UUID insertUid;
    private UUID updateUid;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "course_topics",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics = new HashSet<>();

    @ElementCollection
    @CollectionTable(
        name = "instructor_courses",
        joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "instructor_id")
    private Set<UUID> instructorIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "course_student_groups",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Column(name = "group_id")
    private Set<Integer> studentGroupIds = new HashSet<>();
    public Course() {
    }

    public Course(Integer domainId,
                  Integer sessionId,
                  String abbr,
                  String name,
                  String notes,
                  UUID insertUid,
                  UUID updateUid) {
        this.domainId = domainId;
        this.sessionId = sessionId;
        this.abbr = abbr;
        this.name = name;
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

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<UUID> getInstructorIds() {
        return instructorIds;
    }

    public void setInstructorIds(Set<UUID> instructorIds) {
        this.instructorIds = instructorIds;
    }

    public Set<Integer> getStudentGroupIds() {
        return studentGroupIds;
    }

    public void setStudentGroupIds(Set<Integer> studentGroupIds) {
        this.studentGroupIds = studentGroupIds;
    }
}
