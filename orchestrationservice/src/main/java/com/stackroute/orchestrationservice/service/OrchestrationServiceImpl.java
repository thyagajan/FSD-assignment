package com.stackroute.orchestrationservice.service;

import com.stackroute.orchestrationservice.domain.User;
import com.stackroute.orchestrationservice.exception.UserAlreadyFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrchestrationServiceImpl implements OrchestrationService {

  @Autowired
  private RestTemplate restTemplate;
  private String registerURL = "http://usertrackservice/api/v1/usertrackservice/register";
  private String userServiceUrl = "http://authenticationservice/api/v1/userservice/user";

  @Override
  public User registerUser(User user) throws UserAlreadyFoundException {

    User userResponse= null;
    try{
       userResponse= restTemplate.postForObject(registerURL,user,User.class);
      restTemplate.postForObject(userServiceUrl,user,User.class);
    }catch (Exception exception){
      throw new UserAlreadyFoundException();
    }
    return userResponse;
  }
}
