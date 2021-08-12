package com.security.Booking;

import com.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/FMS/api")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/saveBooking")
    public @ResponseBody void storeBookingData(HttpServletRequest request, @RequestBody ArrayList<String> body) {
        String token = request.getHeader("token");
        try {
            if (token != null && !token.isEmpty()) {
                //boolean _isValid = tokenService.validateToken(token); // Nimbusds
                boolean _isValid = tokenService.validateAuthToken(token); // Auth0
                if (_isValid) {
                    System.out.println("TOKEN VALID");
                    System.out.println(body.toString());
                    // Process Data captured from MLSC
                } else {
                    System.out.println("INVALID TOKEN");
                }
            } else {
                System.out.println("TOKEN NOT PRESENT");
            }
        } catch (Exception ex) {
            System.out.println("EXCEPTION OCCURED :: "+ex.getMessage());
        }
    }
}
