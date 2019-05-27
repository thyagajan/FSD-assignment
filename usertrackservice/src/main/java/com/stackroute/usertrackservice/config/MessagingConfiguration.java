package com.stackroute.usertrackservice.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

  private String exchangeName = "user_exchange";
  private String queueName = "user_queue";
  private String trackQueueName=  "track_queue";

  @Bean
  DirectExchange directExchange(){
    return new DirectExchange(exchangeName);
  }

  @Bean
  Queue queue(){
    return new Queue(queueName,false);
  }

  @Bean
  Queue trackQueue(){
    return new Queue(trackQueueName,false);
  }

  @Bean
  public Jackson2JsonMessageConverter produceJackson2JsonMessageConverter(){
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(produceJackson2JsonMessageConverter());
    return rabbitTemplate;
  }

  @Bean
  Binding userBinding(Queue queue,DirectExchange directExchange){
    return BindingBuilder.bind(queue).to(directExchange).with("user_routing");
  }

  @Bean
  Binding trackBinding(Queue trackQueue,DirectExchange directExchange){
    return BindingBuilder.bind(trackQueue).to(directExchange).with("track_routing");
  }
}
