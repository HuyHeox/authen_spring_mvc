//package com.koolsoft.authen.service;
//
//import com.koolsoft.authen.model.LoginUserInfo;
//import com.koolsoft.authen.repo.LoginUserInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginUserInfoService {
//
//
//    private LoginUserInfoRepository loginUserInfoRepository;
//
//
//    public LoginUserInfo findToken(String token){
//        LoginUserInfo loginUserInfo = new LoginUserInfo("1","admin");
//        loginUserInfoRepository.save(loginUserInfo);
//        loginUserInfo = new LoginUserInfo("2","admin");
//        loginUserInfoRepository.save(loginUserInfo);
//        return loginUserInfoRepository.findById(token).get();
//    }
//}
