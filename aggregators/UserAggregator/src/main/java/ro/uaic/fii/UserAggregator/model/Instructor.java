package ro.uaic.fii.UserAggregator.model;


import java.util.Date;
import java.util.UUID;

public record Instructor (
    UUID id,
    Integer domainId,
    String account,
    String password,
    String name,
    String email,
    String notes,
    boolean active,
    Date insertTimestamp,
    Date updateTimestamp,
    UUID insertUid,
    UUID updateUid
){
}
