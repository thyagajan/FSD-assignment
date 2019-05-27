package com.stackroute.authservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authservice.domain.User;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.service.SecurityTokenGenerator;
import com.stackroute.authservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.management.remote.JMXServerErrorException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SecurityTokenGenerator securityTokenGenerator;

    private User user;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setUserName("Dave");
        user.setPassword("Dave123");
    }

    @Test
    public void testSaveUserSuccess() throws Exception{
        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/userservice/user")
        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
        .andExpect(status().isCreated()).andDo(print());
        verify(userService,times(1)).saveUser(Mockito.any());
    }

    @Test
    public void testUserLoginSuccess() throws Exception {
        Mockito.when(userService.findByUserNameAndPassword(user.getUserName(),user.getPassword())).thenReturn(user);
        mockMvc.perform(post("/api/v1/userservice/login")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(status().isOk()).andDo(print());
        verify(userService,times(1)).findByUserNameAndPassword(user.getUserName(),user.getPassword());
    }

    private String jsonToString(User user)  {
        String result;
        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(user);
            result = jsonContent;
        }catch(JsonProcessingException jex){
            result = "Proceesing Error";
        }
        return result;
    }

}
