package com.example.quiz_service.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Value("${queue.name}")
    private String queue;
    //Create queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    @Value("${exchange.name}")
    private String exchange;
    //create exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    @Value("${key.name}")
    private String key;
    //binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(key);
    }
    // json config
    @Value("${queue.json.name}")
    private String jqueue;
    @Value("${key.json.name}")
    private String jkey;
    @Bean
    public Queue jsonqueue()
    {
        return new Queue(jqueue);
    }
    @Bean
    public Binding Jsonbinding(){
        return BindingBuilder
                .bind(jsonqueue())
                .to(exchange())
                .with(jkey);
    }
    // this method convert message to Json
    @Bean
    public MessageConverter messageConverter(){
        // Uses Jackson library to convert Java objects to JSON and vice versa
            return new Jackson2JsonMessageConverter();
    }
    //now configure RabbitTemplate for json
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        // Create a new instance of RabbitTemplate with the provided connection factory
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        // Set the message converter to automatically handle JSON serialization/deserialization
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
