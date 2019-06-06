package com.soc.exames.amqp;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.soc.exames.domain.Exame;

public class ExameAmqp {
	
	private final static String QUEUE_NEW_EXAMS = "newexames";
	private final static String QUEUE_UPDATED_EXAMS = "updatedexames";
	
	public void newExame(Exame exame) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NEW_EXAMS, false, false, false, null);
			String message = String.valueOf(exame.getId());
			channel.basicPublish("", QUEUE_NEW_EXAMS, null, message.getBytes());
		}
	}
	
	public void updateExame(Exame exame) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_UPDATED_EXAMS, false, false, false, null);
			String message = String.valueOf(exame.getId());
			channel.basicPublish("", QUEUE_UPDATED_EXAMS, null, message.getBytes());
		}
	}
}
