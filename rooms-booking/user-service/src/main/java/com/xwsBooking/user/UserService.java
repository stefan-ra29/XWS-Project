package com.xwsBooking.user;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
