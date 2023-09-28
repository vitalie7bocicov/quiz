package ro.uaic.fii.UserService.dto;

public class StudentResDto {

    private Integer domainId;
    private Integer sessionId;
    private Integer groupId;
    private String code;
    private String account;
    private String name;
    private String email;
    private String notes;
    private boolean active;

    public StudentResDto(Integer domainId,
                         Integer sessionId,
                         Integer groupId,
                         String code,
                         String account,
                         String name,
                         String email,
                         String notes,
                         boolean active) {
        this.domainId = domainId;
        this.sessionId = sessionId;
        this.groupId = groupId;
        this.code = code;
        this.account = account;
        this.name = name;
        this.email = email;
        this.notes = notes;
        this.active = active;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroup(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
