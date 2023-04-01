package com.xwsProject.FlightsBackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterUserDTO dto) {
        userService.register(dto.map());
    }
}
