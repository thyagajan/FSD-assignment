package com.stackroute.orchestrationservice.service;

import com.stackroute.orchestrationservice.domain.User;
import com.stackroute.orchestrationservice.exception.UserAlreadyFoundException;

public interface OrchestrationService {
  User registerUser(User user) throws UserAlreadyFoundException;
}
