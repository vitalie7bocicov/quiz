package ro.uaic.fii.UserService.repository.model;


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
@Table(name = "student_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_groups_sequence")
    @SequenceGenerator(name = "student_groups_sequence", sequenceName = "student_groups_id_seq", allocationSize = 1)
    private Integer id;
    private Integer domainId;
    private Integer sessionId;
    private Integer parentGroupId;
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
}
