package br.com.projetomensageria;

import br.com.projetomensageria.application.mensageria.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoMensageriaApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ProjetoMensageriaApplication.class);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}