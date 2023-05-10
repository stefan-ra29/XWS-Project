package com.xwsBooking.security;

import com.xwsBooking.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthUserDetailService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findUserByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        user.getAuthorities()
                .forEach(role -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
                });

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
