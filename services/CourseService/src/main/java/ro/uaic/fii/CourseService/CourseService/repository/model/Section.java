package ro.uaic.fii.CourseService.CourseService.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_generator")
    @SequenceGenerator(name = "section_generator", sequenceName = "sections_id_seq", allocationSize = 1)
    private Integer id;
    private Integer courseId;
    private String name;
    private Integer orderNumber;
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
            name = "section_topics",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> topics = new HashSet<>();
}
