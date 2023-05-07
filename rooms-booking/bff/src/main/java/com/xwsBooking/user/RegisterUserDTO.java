package com.xwsBooking.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Boolean isHost;
    private AddressDTO address;
}
