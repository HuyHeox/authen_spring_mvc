package com.koolsoft.authen.service;

import com.koolsoft.grpcserver.APIResponse;
import com.koolsoft.grpcserver.LoginRequest;
import com.koolsoft.grpcserver.RegisterRequest;
import com.koolsoft.grpcserver.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GRPCClientService {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();


    public APIResponse LoginWithGrpc(){

        userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);

        LoginRequest loginRequest = LoginRequest.newBuilder().setUsername("loco").setPassword("hello").build();

        APIResponse response = userStub.login(loginRequest);

        System.out.println(response.getResponseMessage());
        return response;
    }

    public String RegisterWithGrpc(){

        userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);

        RegisterRequest registerRequest = RegisterRequest.newBuilder().setUsername("loco").setPassword("hello").setRole("admin").build();

        APIResponse response = userStub.register(registerRequest);

        System.out.println(response.getResponseMessage());
        return response.getResponseMessage();
    }
}
