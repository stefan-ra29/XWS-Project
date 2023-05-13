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
                    throw new Exception("Can't fin user with that email!");

                builder.setUser(User_grpc.newBuilder()
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

    @Transactional
    public User saveRegisteredUser(User user){
        try{
            userRepository.save(user);
            return user;
        }
        catch (Exception e){
            throw new UnsupportedOperationException("Can't save user!");
        }
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
        User user = userRepository.findById(request.getUserId()).get();

        if(user.getRole() == Role.GUEST) {
            // provjera da li ima aktivnih rezervacija taj guest

            //
            userRepository.deleteById(request.getUserId());
            DeleteUserResponse response = DeleteUserResponse.newBuilder().setResponseMessage("Account deleted successfully").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        else if(user.getRole() == Role.HOST) {
            // provjera da li ima aktivnih rezervacija taj host

            //

            // brisanje svih smjestaja tog host-a

            //

            userRepository.deleteById(request.getUserId());
            DeleteUserResponse response = DeleteUserResponse.newBuilder().setResponseMessage("Account deleted successfully").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }
}
