package com.gorbatenko.rabbitmq.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }

    @Value("${rabbitmq.queueName}")
    private String queueName;

    @Autowired
    RabbitTemplate rabbitTemplate;


    static int i = 0;

    @GetMapping
    public void index() {
        rabbitTemplate.convertAndSend(queueName, "[ " + ++i + " ] Hello from WEB!");
    }
}
