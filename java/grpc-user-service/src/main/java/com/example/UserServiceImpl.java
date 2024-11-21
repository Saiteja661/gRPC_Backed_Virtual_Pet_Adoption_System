package com.example;

import io.grpc.stub.StreamObserver;
import java.util.HashMap;
import java.util.Map;

import static com.example.UserOuterClass.*;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private final Map<Integer, User> userStore = new HashMap<>();

    @Override
    public void saveUser(User request, StreamObserver<Response> responseObserver) {
        userStore.put(request.getId(), request);
        Response response = Response.newBuilder()
                .setMessage("User saved successfully")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserById(UserId request, StreamObserver<UserInfo> responseObserver) {
        User user = userStore.get(request.getId());
        UserInfo userInfo = UserInfo.newBuilder()
                .setUsername(user != null ? user.getUsername() : "User not found")
                .setFullname(user != null ? user.getFullname() : "")
                .build();
        responseObserver.onNext(userInfo);
        responseObserver.onCompleted();
    }
}
