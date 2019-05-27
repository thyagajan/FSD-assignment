package com.stackroute.authservice.service;

import com.stackroute.authservice.domain.User;
import com.stackroute.authservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    Optional optional;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserName("Dave");
        user.setPassword("Dave123");

    }

    @Test
    public void testSaveUserSuccess(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User fetchUser = userRepository.save(user);
        Assert.assertEquals(user,fetchUser);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void testUserLoginSuccess(){
        Mockito.when(userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword())).thenReturn(user);
        User fetchUser = userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
        Assert.assertEquals(user.getUserName(),fetchUser.getUserName());
        verify(userRepository,times(1)).findByUserNameAndPassword(user.getUserName(),user.getPassword());
    }

}
