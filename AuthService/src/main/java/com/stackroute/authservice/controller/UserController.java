package com.stackroute.authservice.controller;

import com.stackroute.authservice.domain.User;
import com.stackroute.authservice.exception.UserNotFoundException;
import com.stackroute.authservice.service.SecurityTokenGenerator;
import com.stackroute.authservice.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userservice")
public class UserController {
    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        ResponseEntity responseEntity = null;
        try{
            User userObj = userService.findByUserNameAndPassword(user.getUserName(),user.getPassword());
            if(userObj.getUserName().equals(user.getUserName())){
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity =  new ResponseEntity(map,HttpStatus.OK);

        }catch(UserNotFoundException unfe){
            throw unfe;
        }catch(Exception e){
            responseEntity =  new ResponseEntity("Try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
