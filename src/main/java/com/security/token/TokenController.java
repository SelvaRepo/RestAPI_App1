package com.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/FMS/api")
public class TokenController {
    @Autowired
    TokenService tokenService;

    @GetMapping("/token")
    public @ResponseBody String generateToken(HttpServletRequest request) {
        String response = "INVALID";
        System.out.println("BEGIN : GENERATE TOKEN");
        String username = request.getHeader("username");
        System.out.println("USERNAME = "+username);
        if (username != null && !username.isEmpty()) {
            //response = tokenService.generateToken(username); // Nimbusds
            response = tokenService.generateAuthToken(username); // Auth0
            System.out.println("TOKEN = "+response);
        }
        return response;
    }
}
