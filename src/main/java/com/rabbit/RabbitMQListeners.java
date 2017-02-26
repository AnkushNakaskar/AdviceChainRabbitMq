package com.rabbit;

import java.util.LinkedList;
import java.util.List;

import org.aopalliance.aop.Advice;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;

@Configuration
public class RabbitMQListeners {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private Queue2Consumer queue2Consumer;


	@Bean
	public Advice[] advices() {
		ExponentialBackOffPolicy backoffPolicy = new ExponentialBackOffPolicy();
		backoffPolicy.setInitialInterval(10);
		backoffPolicy.setMaxInterval(1000);
		backoffPolicy.setMultiplier(2);
		Advice[] adviceChain = new Advice[1];
		List<Advice> listOfAdvices = new LinkedList<>();
		Advice advice = RetryInterceptorBuilder.stateless().backOffPolicy(backoffPolicy).maxAttempts(3)
				.build();
		listOfAdvices.add(advice);
		return listOfAdvices.toArray(adviceChain);
	}
	
	

	@Bean
	public SimpleMessageListenerContainer queue1Listener() {
		SimpleMessageListenerContainer emailListener = new SimpleMessageListenerContainer();
		emailListener.setConnectionFactory(connectionFactory);
		emailListener.setAcknowledgeMode(AcknowledgeMode.AUTO);
		emailListener.addQueueNames(new String[] { RabbitMQConfig.queue2 });
		emailListener.setConcurrentConsumers(2);
		emailListener.setAdviceChain(advices());
		emailListener.setDefaultRequeueRejected(false);
		emailListener.setMessageListener(queue2Consumer);
		return emailListener;
	}


}
