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

}
