package ro.uaic.fii.TestService.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "completed", columnDefinition = "boolean default false")
    private boolean completed;
    private boolean demo;
    private String notes;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
