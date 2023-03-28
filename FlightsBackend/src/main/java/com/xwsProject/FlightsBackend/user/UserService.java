package com.xwsProject.FlightsBackend.user;

import com.xwsProject.FlightsBackend.utils.CustomBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void register(User user) {
        if(!user.validate()) {
            throw new CustomBadRequestException("Invalidate email or password");
        }
        if(userRepository.findUserByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new CustomBadRequestException("User with that email already exists");
        }
    }

}
