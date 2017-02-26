package com.rabbit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

	
	@Autowired
	private ConfigurableApplicationContext context;
	
	@Autowired
	private RabbitTemplate queue1Template;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 1; i++) {
			System.out.println("Sending message....");
			queue1Template.convertAndSend("Message..1");
		}
		
	}

}