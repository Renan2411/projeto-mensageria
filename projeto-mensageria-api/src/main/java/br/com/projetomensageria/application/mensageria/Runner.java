package br.com.projetomensageria.application.mensageria;

import br.com.projetomensageria.ProjetoMensageriaApplication;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sendin message...");

        rabbitTemplate.convertAndSend(ProjetoMensageriaApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
    }
}
