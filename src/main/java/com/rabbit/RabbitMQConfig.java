package com.rabbit;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

	public static final String queue2 = "queue2";
	
	 @Autowired
	  private ConnectionFactory cachingConnectionFactory;
	 


	@Bean
	public Queue queue1() {
		return new Queue(queue2, true);
	}




	@Bean
	public RabbitAdmin rabitMqAdmin() {
		RabbitAdmin admin = new RabbitAdmin(cachingConnectionFactory);
		admin.declareQueue(queue1());
		return admin;
	}

}
