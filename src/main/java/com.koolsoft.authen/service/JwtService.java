package com.koolsoft.authen.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.koolsoft.authen.model.User;
import com.koolsoft.authen.service.DTO.JWTTokenClaim;
import com.koolsoft.authen.service.DTO.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private String secret =  "secret" ;
    private long expirationTime = 86400000;

    public String createToken(User user) {
        try {
            Date expiredDate = new Date(new Date().getTime() + expirationTime);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withExpiresAt(expiredDate)
                    .withClaim("username", user.getUsername())
                    .withClaim("password", user.getPassword())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            return null;
        }
    }

    public JWTTokenClaim decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiredDate = decodedJWT.getExpiresAt();
            String username = decodedJWT.getClaim("username").asString();
            String password = decodedJWT.getClaim("password").asString();

            return new JWTTokenClaim(username,password, expiredDate);
        } catch (JWTVerificationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    @Autowired
    private BlackListTokenService blackListTokenService;

    public Boolean validateLoginToken(JWTTokenClaim jwtTokenClaim){
        String username = jwtTokenClaim.getUsername();

        if (username==null || username.isEmpty()){
            return false;
        }
        if (jwtTokenClaim.getExpiredDate().before(new Date())){
            return false;
        }
        return true;
    }
}