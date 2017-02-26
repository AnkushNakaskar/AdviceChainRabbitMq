package com.rabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTemplates {

	@Autowired
	private ConnectionFactory rabitMQConnectionfactory;


	@Bean
	public RabbitTemplate queue2Template() {
		RabbitTemplate template = new RabbitTemplate();
		template.setConnectionFactory(rabitMQConnectionfactory);
		template.setQueue(RabbitMQConfig.queue2);
		template.setRoutingKey(RabbitMQConfig.queue2);
		return template;
	}

	
}
