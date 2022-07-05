package com.koolsoft.authen.RabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class Server {

//	@RabbitHandler
//	public String qqqqqqq(String s) {
//		System.out.println("Received request for " + s);
//
//		System.out.println("Returned " + s);
//
//		return s;
//	}

	@RabbitListener(queues = "rpc.request")
	public long factorial(int n) {
			System.out.println("Received request for " + n);

		long result = computeFactorial(n);

		System.out.println("Returned " + result);

		return result;
	}

	public long computeFactorial(int number) {
		long result = 1;

		for (int f = 2; f <= number; f++) {
			result *= f;
		}

		return result;
	}


}
