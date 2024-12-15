package com.furidaweb.server.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassDto {

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = " New password is required")
    private String newPassword;
}
