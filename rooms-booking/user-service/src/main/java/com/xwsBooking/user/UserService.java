package com.xwsBooking.user;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@GrpcService
@Service
public class UserService extends UserServiceGrpc.UserServiceImplBase{

    @Autowired
    UserRepository userRepository;

    @Override
    public void getUsernameById(GetUsernameRequest request, StreamObserver<GetUsernameResponse> responseObserver) {

        GetUsernameResponse.Builder builder = GetUsernameResponse.newBuilder();

        if(request.getUserId().equals("1")) {
            builder.setUsername("Roki");
        }
        else if(request.getUserId().equals("2")) {
            builder.setUsername("Koki");
        }
        else {
            builder.setUsername("");
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
    @Override
    public void registerUser(RegisterUserRequest request, StreamObserver<RegisterUserResponse> responseObserver) {

        RegisterUserResponse.Builder builder = RegisterUserResponse.newBuilder();
        try{

            if(request.getRegisterUserDTO() != null) {
                User user = saveRegisteredUser(new User(request.getRegisterUserDTO()));
                builder.setUsername(user.getUsername());
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
            }
            else
                throw new Exception("Nothing is received");

        }
        catch(UnsupportedOperationException e){
            //System.out.println(e.getMessage());
            builder.setUsername(e.getMessage());
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();

        }
        catch (Exception e){
            //System.out.println(e.getMessage());
            builder.setUsername(e.getMessage());
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        };


    }

    @Override
    public void findUserByUsername(UserRequestByUsername request, StreamObserver<UserResponseByUsername> responseObserver) {
        UserResponseByUsername.Builder builder = UserResponseByUsername.newBuilder();
        try{
            if(request.getUsername() != null) {
                Optional<User> user = userRepository.findByUsername(request.getUsername());
                if(user.isEmpty())
                    throw new Exception("Can't find user with that email!");

                builder.setUser(LoginUser.newBuilder()
                        .setUsername(user.get().getUsername())
                        .setPassword(user.get().getPassword())
                        .setEmail(user.get().getEmail())
                        .setFirstName(user.get().getFirstName())
                        .setLastName(user.get().getLastName())
                        .setId(user.get().getId().toString())
                        .setRole(user.get().getRole().toString())
                        .build());
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
            }
            else
                throw new Exception("Nothing is received");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void findUserById(UserRequestById request, StreamObserver<UserResponseById> responseObserver) {
        UserResponseById.Builder builder = UserResponseById.newBuilder();
        try{
            if(request.getId() != null) {
                Optional<User> user = userRepository.findById(Long.parseLong(request.getId()));
                if(user.isEmpty())
                    throw new Exception("Can't find user with that email!");

                builder.setUser(AccountInfoUser.newBuilder()
                        .setUsername(user.get().getUsername())
                        .setPassword(user.get().getPassword())
                        .setEmail(user.get().getEmail())
                        .setFirstName(user.get().getFirstName())
                        .setLastName(user.get().getLastName())
                        .setId(user.get().getId().toString())
                        .setRole(user.get().getRole().toString())
                        .setAddress(AddressDTO_grpc.newBuilder()
                                .setCountry(user.get().getAddress().getCountry())
                                .setCity(user.get().getAddress().getCity())
                                .setStreet(user.get().getAddress().getStreet())
                                .setStreetNumber(user.get().getAddress().getStreetNumber())
                                .build())
                        .build());
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
            }
            else
                throw new Exception("Nothing is received");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    private User saveRegisteredUser(User user){
        try{
            userRepository.save(user);
            return user;
        }
        catch (Exception e){
            throw new UnsupportedOperationException("Can't save user!");
        }
    }
}
