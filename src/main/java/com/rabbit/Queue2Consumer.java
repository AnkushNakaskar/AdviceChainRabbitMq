package com.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class Queue2Consumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("In queue2 consumer....");
		throw new RuntimeException();
	}

}
