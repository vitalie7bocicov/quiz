package ro.uaic.fii.UserAggregator.model;


import java.util.Date;
import java.util.UUID;
public record StudentGroup(
        Integer id,
        Integer domainId,
        Integer sessionId,
        StudentGroup parentGroup,
        String abbr,
        String name,
        String notes,
        Date insertTimestamp,
        Date updateTimestamp,
        UUID insertUid,
        UUID updateUid
) {
}
