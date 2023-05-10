package com.xwsBooking.security;

import com.xwsBooking.user.User;
import com.xwsBooking.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }
        catch (Exception e){
            return new ResponseEntity<String>("Invalid username or password!", HttpStatus.BAD_REQUEST);
        }
        User user = userService.findUserByUsername(authRequest.getUsername());
        return new ResponseEntity<>(jwtUtil.generateToken(authRequest.getUsername(), user.getRole().name(), user.getId(), user.getEmail()), HttpStatus.OK);
    }
}
