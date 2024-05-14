package ro.uaic.fii.CourseService.CourseService.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_sequence")
    @SequenceGenerator(name = "topic_sequence", sequenceName = "topics_id_seq", allocationSize = 1)
    private Integer id;
    private Integer domainId;
    private Integer parentId;
    private String shortName;
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

    @JsonBackReference
    @ManyToMany(mappedBy = "topics")
    private Set<Course> courses = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "topics")
    private Set<Section> sections = new HashSet<>();
}