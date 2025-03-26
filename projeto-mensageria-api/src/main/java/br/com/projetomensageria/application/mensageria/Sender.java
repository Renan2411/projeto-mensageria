package br.com.projetomensageria.application.mensageria;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class Sender {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public void enviar(String exchange, String routKey, Object mensagem) throws InterruptedException {
        log.info("=============ENVIANDO MENSAGEM, CONTEUDO = { " + mensagem + "}==========");
        rabbitTemplate.convertAndSend(exchange, routKey, mensagem);
        log.info("=======MENSAGEM ENVIADA=======");
        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
    }

}
