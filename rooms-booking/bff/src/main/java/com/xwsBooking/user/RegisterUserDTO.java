package com.xwsBooking.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp="([A-Z][a-z]+)(\\s[A-Z][a-z]+)*", message="Invalid first name input!")
    private String firstName;
    @NotNull
    @NotBlank
    @Pattern(regexp="([A-Z][a-z]+)(\\s[A-Z][a-z]+)*", message="Invalid last name input!")
    private String lastName;
    @NotNull
    @NotBlank
    @Pattern(regexp="([A-Za-z0-9]{3,})", message="Invalid username input!")
    private String username;
    @NotNull
    @NotBlank
    @Pattern(regexp="([A-Za-z0-9]{3,})", message="Invalid password input!")
    private String password;
    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message="Invalid email address!" )
    private String email;
    @NotNull
    private Boolean isHost;
    @NotNull
    @Valid
    private AddressDTO address;
}
