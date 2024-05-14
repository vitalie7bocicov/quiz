package ro.uaic.fii.CourseService.CourseService.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_generator")
    @SequenceGenerator(name = "images_generator", sequenceName = "images_id_seq", allocationSize = 1)
    private Integer id;
    private Integer topicId;
    private String name;
    private  String mimeType;
    private byte[] content;
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