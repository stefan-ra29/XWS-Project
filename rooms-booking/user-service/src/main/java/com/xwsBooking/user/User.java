package com.xwsBooking.user;

import com.xwsBooking.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private Role role;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private boolean isDeleted;

    public User(RegisterUserDTO_grpc registerUserDTO) {
        this.firstName = registerUserDTO.getFirstName();
        this.lastName = registerUserDTO.getLastName();
        this.username = registerUserDTO.getUsername();
        this.password = registerUserDTO.getPassword();
        this.email = registerUserDTO.getEmail();
        if(registerUserDTO.getIsHost())
            this.role = Role.HOST;
        else
            this.role = Role.GUEST;
        this.address = Address.builder()
                .country(registerUserDTO.getAddress().getCountry())
                .city(registerUserDTO.getAddress().getCity())
                .street(registerUserDTO.getAddress().getStreet())
                .streetNumber(registerUserDTO.getAddress().getStreetNumber())
                .build();
        this.setDeleted(false);
    }

    public User(AccountInfoUser user) {
        this.id = Long.parseLong(user.getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        if(user.getRole().equals("HOST"))
            this.role = Role.HOST;
        else
            this.role = Role.GUEST;
        this.address = Address.builder()
                .country(user.getAddress().getCountry())
                .city(user.getAddress().getCity())
                .street(user.getAddress().getStreet())
                .streetNumber(user.getAddress().getStreetNumber())
                .build();
        this.setDeleted(false);
    }
}
