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

            if(userResponseByUsername.getResponseMessage().length() > 0)
                throw new Exception(userResponseByUsername.getResponseMessage());
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
    public String deleteUser(long userId) {
        DeleteUserRequest request = DeleteUserRequest.newBuilder().setUserId(userId).build();

        DeleteUserResponse response = userStub.deleteUser(request);

        return  response.getResponseMessage();
    }

    public ChangeAccountResponse changeUserAccountInformation(UserDTO userDTO) {
        try{
            ChangeAccountRequest changeAccountRequest = ChangeAccountRequest.newBuilder()
                    .setUser(AccountInfoUser.newBuilder()
                                    .setFirstName(userDTO.getFirstName())
                                    .setLastName(userDTO.getLastName())
                                    .setUsername(userDTO.getUsername())
                                    .setPassword(userDTO.getPassword())
                                    .setEmail(userDTO.getEmail())
                                    .setRole(userDTO.getRole().toString())
                                    .setAddress
                                            (AddressDTO_grpc.newBuilder()
                                                    .setCountry(userDTO.getAddress().getCountry())
                                                    .setCity(userDTO.getAddress().getCity())
                                                    .setStreet(userDTO.getAddress().getStreet())
                                                    .setStreetNumber(userDTO.getAddress().getStreetNumber())
                                                    .build())
                                    .setId(userDTO.getId())
                                    .build())
                    .build();

            ChangeAccountResponse changeAccountResponse = userStub.changeAccountInformation(changeAccountRequest);
            return changeAccountResponse;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
