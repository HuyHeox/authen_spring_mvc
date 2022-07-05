package com.koolsoft.authen.controller;

import com.koolsoft.authen.model.User;
import com.koolsoft.authen.service.BlackListTokenService;
import com.koolsoft.authen.service.DTO.JWTTokenClaim;
import com.koolsoft.authen.service.DTO.REQ.LoginReqDTO;
import com.koolsoft.authen.service.DTO.REQ.SignupReqDTO;
import com.koolsoft.authen.service.DTO.RES.LoginResDTO;
import com.koolsoft.authen.service.DTO.RES.SignupResDTO;
import com.koolsoft.authen.service.JwtService;
import com.koolsoft.authen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlackListTokenService blackListTokenService;
//    @ResponseBody
//    @PostMapping(value="/user/login")
//    public ResponseEntity<LoginResDTO> doLogin (@RequestBody LoginReqDTO loginReqDTO) {
//        if (userService.checkLogin(loginReqDTO)) {
//            return ResponseEntity.ok(userService.loginUser(loginReqDTO));
//        }
//        else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//
//    @PostMapping(value="/user/signup",consumes = MediaType.APPLICATION_JSON_VALUE,produces="application/json")
//    public ResponseEntity<SignupResDTO> doSignup (@RequestBody SignupReqDTO signupReqDTO) throws Exception {
//
//        SignupResDTO  signupResDTO  = userService.signupUser(signupReqDTO);
//        return ResponseEntity.ok(signupResDTO);
//    }
//
////    @PreAuthorize(value="hasAuthority('ROLE_ADMIN')")
//    @GetMapping(value = "/user/get-all")
//    public ResponseEntity<List<User>> getAllUser() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @Autowired
//    private JwtService jwtService;
//    @GetMapping(value="/checktoken/{token}")
//    public JWTTokenClaim checktoken(@PathVariable(value = "token") String token){
//        return jwtService.decodeToken(token);
//    }

    //    @PostMapping(value="/user/login")
//    public ResponseEntity<LoginResDTO> doLogin (@RequestBody LoginReqDTO loginReqDTO) {
//        if (userService.checkLogin(loginReqDTO)) {
//            return ResponseEntity.ok(userService.loginUser(loginReqDTO));
//        }
//        else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
    @GetMapping("/")
    public String homePage() {
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
        JWTTokenClaim tokenInfo = userService.checkCookie(request);
        if (tokenInfo == null) {
            return "index";
        } else {
            LoginReqDTO loginReqDTO = new LoginReqDTO();
            loginReqDTO.setUsername(tokenInfo.getUsername());
            loginReqDTO.setPassword(tokenInfo.getPassword());
            if (userService.checkLogin(loginReqDTO)) {
//                session.setAttribute("username", account.getUsername());
                return "checkRole";
            } else {
                modelMap.put("error", "Account's Invalid");
                return "index";
            }
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(value = "loginReqDTO") LoginReqDTO loginReqDTO, ModelMap modelMap, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        LoginResDTO loginResDTO = userService.loginUser(loginReqDTO);
        String token = loginResDTO.getToken();
        Cookie ckToken = new Cookie("token", token);
        ckToken.setMaxAge(3600);
        response.addCookie(ckToken);
        if (userService.checkLogin(loginReqDTO)) {
            if (request.getParameter("selected") != null) {
                Cookie ckRemember = new Cookie("remember", "true");
                ckRemember.setMaxAge(3600);
                response.addCookie(ckRemember);
            }
            else {
                Cookie ckRemember = new Cookie("remember", "false");
                ckRemember.setMaxAge(3600);
                response.addCookie(ckRemember);
            }
            modelMap.put("user",loginResDTO.getUser());
            return "checkRole";
        } else {
            modelMap.put("error", "Account's Invalid");
            return "index";
        }
    }

    @RequestMapping(value = "user/logout", method = RequestMethod.GET)
    public String logout( HttpServletRequest request, HttpServletResponse response) {
        // Remove cookie
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equalsIgnoreCase("token")) {
                blackListTokenService.addTokenToBlacklist(cookie.getValue());
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/";
    }

    @PostMapping(value="/user/signup")
    public String doSignup (@ModelAttribute(value = "signupReqDTO") SignupReqDTO signupReqDTO,HttpServletRequest request) throws Exception {
        if (request.getParameter("selected") != null){
            signupReqDTO.setRoles(request.getParameterValues("selected"));
        }
        else {
            return "signup";
        }
        userService.signupUser(signupReqDTO);
            return "index";
    }

    @GetMapping("/user/signup")
    public String redirectSignup() {
        return "signup";
    }

    @GetMapping("/user/check/role1")
    public String checkrole1() {
        return "success";
    }
    @GetMapping("/user/check/role2")
    public String checkrole2() {
        return "success";
    }
    @GetMapping("/user/check/role3")
    public String checkrole3() {
        return "success";
    }
}
