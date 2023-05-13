package com.xwsBooking.user;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
                                                                        .setPassword(registerUserDTO.getPassword())
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

    public LoginUserDTO findUserByUsername(String username){
        try{
            UserRequestByUsername userRequestByUsername = UserRequestByUsername.newBuilder()
                    .setUsername(username)
                    .build();

            UserResponseByUsername userResponseByUsername = userStub.findUserByUsername(userRequestByUsername);

            var user  = userResponseByUsername.getUser();

            return LoginUserDTO.builder()
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

    public UserDTO findUserById(String id){
        try{
            UserRequestById userRequestById = UserRequestById.newBuilder()
                    .setId(id)
                    .build();

            UserResponseById userResponseById = userStub.findUserById(userRequestById);

            var user  = userResponseById.getUser();

            return UserDTO.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .id(user.getId())
                    .role(Role.valueOf(user.getRole()))
                    .address(AddressDTO.builder()
                            .country(user.getAddress().getCountry())
                            .city(user.getAddress().getCity())
                            .street(user.getAddress().getStreet())
                            .streetNumber(user.getAddress().getStreetNumber())
                            .build())
                    .build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
