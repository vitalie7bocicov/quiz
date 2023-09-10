package ro.uaic.fii.UserService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private Integer domainId;
    private String account;
    private String password;
    private String name;
    private String email;
    private String notes;
    private boolean active;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;

    private UUID insertUid;
    private UUID updateUid;

    public Instructor() {
    }

    public Instructor(Integer domainId,
                String account,
                String password,
                String name,
                String email,
                String notes,
                boolean active,
                UUID insertUid,
                UUID updateUid) {
        this.domainId = domainId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.notes = notes;
        this.active = active;
        this.insertUid = insertUid;
        this.updateUid = updateUid;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public Date getInsertTimestamp() {
        return insertTimestamp;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public UUID getInsertUid() {
        return insertUid;
    }

    public UUID getUpdateUid() {
        return updateUid;
    }
}

