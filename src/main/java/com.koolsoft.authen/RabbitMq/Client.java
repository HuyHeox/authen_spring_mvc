package com.koolsoft.authen.RabbitMq;

import com.koolsoft.authen.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

		@Autowired
		private RabbitTemplate rabbitTemplate;

	@Autowired
	private DirectExchange directExchange;

	public void send(int n) {
//		ApplicationContext context =
//				new AnnotationConfigApplicationContext(RabbitMqConfig.class);
//		AmqpTemplate template = context.getBean(AmqpTemplate.class);
		Long response = (Long) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "koolsoft", n);
		System.out.println("Got " + response + "");
	}


//	public void sendad(String s){
//		String response = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "koolsoft", s);
//		System.out.println("Got " + response + "");
//	}

}
