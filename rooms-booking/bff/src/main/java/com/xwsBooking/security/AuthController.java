package com.xwsBooking.security;

import com.xwsBooking.user.LoginUserDTO;
import com.xwsBooking.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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
            //throw new CustomBadRequestException("Invalid email or password");
            return new ResponseEntity<String>("Invalid username or password!", HttpStatus.BAD_REQUEST);
        }
        LoginUserDTO loginUserDTO = userService.findUserByUsername(authRequest.getUsername());
        return new ResponseEntity<>(jwtUtil.generateToken(authRequest.getUsername(), loginUserDTO.getRole().name(), loginUserDTO.getId(), loginUserDTO.getEmail()), HttpStatus.OK);
    }
}
