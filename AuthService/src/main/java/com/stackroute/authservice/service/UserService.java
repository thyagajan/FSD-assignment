package com.stackroute.authservice.service;

import com.stackroute.authservice.domain.User;
import com.stackroute.authservice.exception.UserNotFoundException;

public interface UserService {

    User saveUser(User user);
    User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException;
}
