package com.koolsoft.authen.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlackListTokenService {

    public static List<String> blackList = new ArrayList<String>();

    public void addTokenToBlacklist(String token){
        blackList.add(token);
    }
     public boolean findTokenInBlacklist(String token){
        return blackList.contains(token);
     }
}
