package ro.uaic.fii.UserService.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "student_groups")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_groups_sequence")
    @SequenceGenerator(name = "student_groups_sequence", sequenceName = "student_groups_id_seq", allocationSize = 1)
    private Integer id;
    private Integer domainId;
    private Integer sessionId;
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private StudentGroup parentGroup;
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

    public StudentGroup() {
    }

    public StudentGroup(Integer domainId,
                        Integer sessionId,
                        StudentGroup parentGroup,
                        String abbr,
                        String name,
                        String notes,
                        UUID insertUid,
                        UUID updateUid) {
        this.domainId = domainId;
        this.sessionId = sessionId;
        this.parentGroup = parentGroup;
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

    public StudentGroup getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(StudentGroup parentGroup) {
        this.parentGroup = parentGroup;
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
}
