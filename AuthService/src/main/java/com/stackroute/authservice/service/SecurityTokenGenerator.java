package com.stackroute.authservice.service;

import com.stackroute.authservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
