package com.xwsBooking.user;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {
    @NotNull
    @NotBlank(message="Invalid country input!")
    private String country;
    @NotNull
    @NotBlank(message="Invalid city input!")
    private String city;
    @NotNull
    @NotBlank(message="Invalid street input!")
    private String street;
    @NotNull
    @NotBlank(message="Invalid street number input!")
    private String streetNumber;
}
