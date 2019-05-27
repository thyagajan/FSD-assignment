package com.stackroute.orchestrationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User does not found")
public class UserNotFoundException extends Exception{
}
