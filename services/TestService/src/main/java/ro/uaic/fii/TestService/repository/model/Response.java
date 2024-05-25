package ro.uaic.fii.TestService.repository.model;

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
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responses_generator")
    @SequenceGenerator(name = "responses_generator", sequenceName = "responses_id_seq", allocationSize = 1)
    private Integer id;
    private Integer participantId;
    private Integer orderNumber;
    private Integer baseQuestionId;
    private String question;
    private String answer;
    private Double points;
    private String ipAddress;
    @CreationTimestamp
    @Column(name = "insert_ts", updatable = false)
    private Date insertTimestamp;
    @UpdateTimestamp
    @Column(name = "update_ts")
    private Date updateTimestamp;
    private UUID insertUid;
    private UUID updateUid;
}
