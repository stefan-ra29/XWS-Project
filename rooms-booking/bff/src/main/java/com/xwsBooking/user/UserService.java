package com.xwsBooking.user;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    public String getUsernameById(String id) {
        GetUsernameRequest getUsernameRequest = GetUsernameRequest.newBuilder().setUserId(id).build();

        GetUsernameResponse getUsernameResponse = userStub.getUsernameById(getUsernameRequest);

        return  getUsernameResponse.getUsername();
    }

    public String registerUser(RegisterUserDTO registerUserDTO) {
        try{
            RegisterUserRequest registerUserRequest = RegisterUserRequest.newBuilder()
                                                        .setRegisterUserDTO
                                                                (RegisterUserDTO_grpc.newBuilder()
                                                                        .setFirstName(registerUserDTO.getFirstName())
                                                                        .setLastName(registerUserDTO.getLastName())
                                                                        .setUsername(registerUserDTO.getUsername())
                                                                        .setPassword(passwordEncoder.encode(registerUserDTO.getPassword()))
                                                                        .setEmail(registerUserDTO.getEmail())
                                                                        .setIsHost(registerUserDTO.getIsHost())
                                                                        .setAddress
                                                                                (AddressDTO_grpc.newBuilder()
                                                                                        .setCountry(registerUserDTO.getAddress().getCountry())
                                                                                        .setCity(registerUserDTO.getAddress().getCity())
                                                                                        .setStreet(registerUserDTO.getAddress().getStreet())
                                                                                        .setStreetNumber(registerUserDTO.getAddress().getStreetNumber())
                                                                                .build())
                                                                .build())
                                                        .build();

            RegisterUserResponse registerUserResponse = userStub.registerUser(registerUserRequest);

            return registerUserResponse.getUsername();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public User findUserByUsername(String username){
        try{
            UserRequestByUsername userRequestByUsername = UserRequestByUsername.newBuilder()
                    .setUsername(username)
                    .build();

            UserResponseByUsername userResponseByUsername = userStub.findUserByUsername(userRequestByUsername);

            var user  = userResponseByUsername.getUser();

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .id(user.getId())
                    .role(Role.valueOf(user.getRole()))
                    .build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
