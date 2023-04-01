package com.xwsProject.FlightsBackend.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDTO {
    String email;
    String password;

    public User map() {
        return User.builder()
                .role(UserRole.BASIC_USER)
                .username(email)
                .password(password)
                .build();
    }
}
