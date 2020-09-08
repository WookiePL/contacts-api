package com.webapi.contacts.controller;

import com.webapi.contacts.model.UserLogin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;


@RestController
@CrossOrigin
public class UserController {


    @RequestMapping("/login")
    public boolean login(@RequestBody UserLogin user) {
        //TODO: get rid of hardcoded values
        return user.getUserName().equals("user1") && user.getPassword().equals("pass1");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
