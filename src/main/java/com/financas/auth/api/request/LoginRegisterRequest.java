package com.financas.auth.api.request;

import com.financas.auth.domain.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRegisterRequest {
    private String login;
    private String password;
    private UserRoles role;
}
