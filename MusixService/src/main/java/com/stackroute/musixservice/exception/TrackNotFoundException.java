package com.stackroute.musixservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="Specified ID is not found")
public class TrackNotFoundException extends Exception {
}
