package com.koolsoft.authen.service;

import com.google.appengine.repackaged.org.apache.commons.codec.binary.StringUtils;
import com.koolsoft.authen.config.Constant;
import com.koolsoft.authen.model.User;
import com.koolsoft.authen.repo.UserRepositoryImp;
import com.koolsoft.authen.service.DTO.JWTTokenClaim;
import com.koolsoft.authen.service.DTO.LoginInfo;
import com.koolsoft.authen.service.DTO.REQ.LoginReqDTO;
import com.koolsoft.authen.service.DTO.REQ.SignupReqDTO;
import com.koolsoft.authen.service.DTO.RES.LoginResDTO;
import com.koolsoft.authen.service.DTO.RES.SignupResDTO;
import com.koolsoft.authen.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepositoryImp userRepositoryImp;

    @Autowired
    JwtService jwtService;

    @Autowired
    private BlackListTokenService blackListTokenService;

    public static List<User> listUser = new ArrayList<User>();

    static {
        listUser.add(new User(1,"admin", "123",new String[] {Constant.Role.ADMIN}));
        listUser.add(new User(2,"staff1","123",new String[] {Constant.Role.CONTENT, Constant.Role.LEADER}));
        listUser.add(new User(3,"staff2","123",new String[] {Constant.Role.CONTENT}));
        listUser.add(new User(4,"staff3","123",new String[] {Constant.Role.LEADER}));
        listUser.add(new User(5,"user","123",new String[] {Constant.Role.USER}));
    }


    public  SignupResDTO signupUser(SignupReqDTO signupReqDTO) throws Exception {
//        AuthCredentials credentials = AuthCredentials.createFor(account, key);
//        DatastoreOptions options = DatastoreOptions.builder()
//                .authCredentials(credentials)
//                .projectId(projectId)
//                .namespace(NAMESPACE)
//                .build();
//        Datastore datastore = options.service();
        if ( checkExistedUser(signupReqDTO.getUsername()) ){
            throw new Exception("username already exist ");
        }
        User user = new User();
        user.setUsername(signupReqDTO.getUsername());
        user.setPassword(signupReqDTO.getPassword());
        user.setRoles(signupReqDTO.getRoles());
//        long id=  userRepositoryImp.save(user);
        long id = 1241513;
        user.setId(1241513);
        listUser.add(user);
        LoginInfo loginInfo = new LoginInfo(user.getUsername());
        return SignupResDTO.builder()
                .user(user)
                .token(jwtService.createToken(user))
                .build();
    }

    public boolean checkLogin(LoginReqDTO loginReqDTO) {
        for (User userExist : listUser) {
            if (StringUtils.equals(loginReqDTO.getUsername(), userExist.getUsername())
                    && StringUtils.equals(loginReqDTO.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public JWTTokenClaim checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        String remember=null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("remember")) {
                remember = cookie.getValue();
                if (remember.equals("false")) return null;
                break;
            }
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("token")) {
                token = cookie.getValue();
                if (blackListTokenService.findTokenInBlacklist(token)) return null;
                JWTTokenClaim tokenInfo = parseToken(token);
                return tokenInfo;
            }
        }
        return null;
    }

    public LoginResDTO loginUser(LoginReqDTO loginReqDTO){
        User user = getUserByUsername(loginReqDTO.getUsername());
        return LoginResDTO.builder()
                .user(user)
                .token(jwtService.createToken(user))
                .build();
    }

    public JWTTokenClaim parseToken(String token) {
        return jwtService.decodeToken(token);
    }

    public User getUserByUsername(String username){
        for (User user : listUser) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserNotFoundException(username);
    }

    public boolean checkExistedUser(String username ){
        for (User user : listUser) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User findById(long id) {
        for (User user : listUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public List<User> findAll() {
        return listUser;
    }




}
