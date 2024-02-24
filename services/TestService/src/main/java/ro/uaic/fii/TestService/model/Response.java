package ro.uaic.fii.TestService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "responses")
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

    public Response() {
    }

    public Response(Integer participantId, Integer orderNumber, Integer baseQuestionId, String question, String answer, Double points, String ipAddress, UUID insertUid, UUID updateUid) {
        this.participantId = participantId;
        this.orderNumber = orderNumber;
        this.baseQuestionId = baseQuestionId;
        this.question = question;
        this.answer = answer;
        this.points = points;
        this.ipAddress = ipAddress;
        this.insertUid = insertUid;
        this.updateUid = updateUid;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getBaseQuestionId() {
        return baseQuestionId;
    }

    public void setBaseQuestionId(Integer baseQuestionId) {
        this.baseQuestionId = baseQuestionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
