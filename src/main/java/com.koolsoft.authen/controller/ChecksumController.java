package com.koolsoft.authen.controller;

import com.koolsoft.authen.service.GRPCClientService;
import com.koolsoft.authen.RabbitMq.Client;
import com.koolsoft.authen.service.CheckSumService;
import com.koolsoft.authen.service.DTO.REQ.LoginReqDTO;
import com.koolsoft.grpcserver.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class ChecksumController {
    @Autowired
    private CheckSumService checkSumService;

//    @Autowired
//    private LoginUserInfoService loginUserInfoService;

    @GetMapping("/checksum/{sum}")
    public String checksum(@RequestBody LoginReqDTO loginReqDTO , @PathVariable(value = "sum") String checksumString) throws IOException, NoSuchAlgorithmException {
        return checkSumService.checkInfo(loginReqDTO,checksumString).toString();
    }

    @GetMapping("/getChecksum")
    public String getChecksum(@RequestBody LoginReqDTO loginReqDTO) throws IOException, NoSuchAlgorithmException {
        return checkSumService.getChecksum(loginReqDTO);
    }

//    @GetMapping("/getuser/{token}")
//    public LoginUserInfo getUser(@PathVariable(value = "token") String token){
//        return loginUserInfoService.findToken(token);
//        }

    @Autowired
    private Client client;


//        @GetMapping("/checkRpc")
//    public void checkRpc(){
////            for (int i = 1; i < 10; i++) {
////                client.send(i);
////            }
//            client.sendad("ÁDADadsaa");
//        }

    @GetMapping("/checkRpc1")
    public void checkRpc1(){
            for (int i = 1; i < 10; i++) {
                client.send(i);
            }
    }

    @Autowired
    private GRPCClientService grpcClientService;

    @GetMapping("/checkGrpcLogin")
    public ResponseEntity<?> loginWithGrpc(){
        return ResponseEntity.ok(grpcClientService.LoginWithGrpc());
    }

    @GetMapping("/checkGrpcRegister")
    public ResponseEntity<?> registerWithGrpc(){
        return ResponseEntity.ok(grpcClientService.RegisterWithGrpc());
    }
}
