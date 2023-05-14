package com.xwsProject.FlightsBackend.security;

import com.xwsProject.FlightsBackend.user.IUserService;
import com.xwsProject.FlightsBackend.user.User;
import com.xwsProject.FlightsBackend.utils.CustomBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class  AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final IUserService userService;

    @PostMapping()
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new CustomBadRequestException("Invalid email or password");
        }
        User user = userService.getUserByUsername(authRequest.getEmail());
        return jwtUtil.generateToken(authRequest.getEmail(), user.getRole().name(), user.getId());
    }
}
