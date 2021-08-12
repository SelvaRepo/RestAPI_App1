package com.security.Booking;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestCall {

    @PostMapping("/api/vega/sendpod")
    public String graphlires(@RequestBody String body,
                             @RequestParam("username") String username, @RequestParam("password") String password)
    {
        System.out.println(body);
        System.out.println(username);
        System.out.println(password);
        return "Hello welcome";
    }
}
