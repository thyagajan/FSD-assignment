package com.stackroute.orchestrationservice.controller;

import com.stackroute.orchestrationservice.domain.User;
import com.stackroute.orchestrationservice.exception.UserAlreadyFoundException;
import com.stackroute.orchestrationservice.service.OrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class OrchestrationController {

  private OrchestrationService orchestrationService;

  @Autowired
  public OrchestrationController(OrchestrationService orchestrationService) {
    this.orchestrationService = orchestrationService;
  }

  @PostMapping("/user")
  public ResponseEntity registerAndSave(@RequestBody User user) throws UserAlreadyFoundException{
    ResponseEntity responseEntity=null;
    try{
      User userObj = orchestrationService.registerUser(user);
      responseEntity=new ResponseEntity(user, HttpStatus.CREATED);

    }catch(UserAlreadyFoundException userFound){
      throw userFound;
    }
    return responseEntity;
  }
}
