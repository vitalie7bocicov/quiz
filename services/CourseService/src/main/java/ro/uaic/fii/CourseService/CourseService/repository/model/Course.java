package ro.uaic.fii.CourseService.CourseService.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}