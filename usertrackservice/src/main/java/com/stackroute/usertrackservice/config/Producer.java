package com.stackroute.usertrackservice.config;

import com.stackroute.rabbitmq.domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  private DirectExchange directExchange;
  private RabbitTemplate rabbitTemplate;

  @Autowired
  public Producer(DirectExchange directExchange, RabbitTemplate rabbitTemplate) {
    this.directExchange = directExchange;
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMsdToRabbitMQ(UserDTO userDTO){
    rabbitTemplate.convertAndSend(directExchange.getName(),"user_routing",userDTO);
  }

  public void sendTrackToRabbitMQ(UserDTO userDTO){
    rabbitTemplate.convertAndSend(directExchange.getName(),"track_routing",userDTO);
  }
}
