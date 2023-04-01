package com.xwsProject.FlightsBackend.user;

public interface IUserService {
    User getUserByUsername(String username);
    void register(User user);
}
