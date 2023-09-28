package ro.uaic.fii.UserService.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginReq {

    @NotBlank(message = "Account cannot be blank")
    private String account;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
