package com.xwsBooking.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private AddressDTO address;
}
