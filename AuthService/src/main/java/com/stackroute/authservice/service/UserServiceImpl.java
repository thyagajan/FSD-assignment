package com.stackroute.authservice.service;

import com.stackroute.authservice.domain.User;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        User user = userRepository.findByUserNameAndPassword(userName,password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
