package com.stackroute.authservice.config;

import com.stackroute.authservice.domain.User;
import com.stackroute.authservice.service.UserService;
import com.stackroute.authservice.service.UserServiceImpl;
import com.stackroute.rabbitmq.domain.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @Autowired
  private UserServiceImpl userService;

  @RabbitListener(queues = "user_queue")
  public void getUserfromMessage(UserDTO userDTO){
    User user = new User();
    user.setUserName(userDTO.getUserName());
    user.setPassword(userDTO.getPassword());
    userService.saveUser(user);

  }
}
