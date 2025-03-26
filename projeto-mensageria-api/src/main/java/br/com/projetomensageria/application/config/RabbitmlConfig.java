package br.com.projetomensageria.application.config;

import br.com.projetomensageria.application.mensageria.Receiver;
import br.com.projetomensageria.domain.usecase.mensagem.processar.ProcessarMensagemUseCase;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmlConfig {

    public static final String topicExchangeName = "spring-boot-exchange";
    public static final String TOPIC_EXCHANGE_MENSAGEM = "mensagem-exchange";
    public static final String QUEUE_MENSAGEM = "mensagem";
    public static final String ROUT_KEY_MENSAGEM = "mensagem-key";
    public static final String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean(value = "queueMensagem")
    Queue queueMensagem() {
        return new Queue(QUEUE_MENSAGEM, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    TopicExchange exchangeMensagem() {
        return new TopicExchange(TOPIC_EXCHANGE_MENSAGEM);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    Binding bindingMensagem(@Qualifier("queueMensagem") Queue queue, @Qualifier("exchangeMensagem") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUT_KEY_MENSAGEM);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerMensagem(ConnectionFactory connectionFactory, @Qualifier("listenerMensagem") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_MENSAGEM);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean(value = "listenerMensagem")
    MessageListenerAdapter listenerAdapterMensagem(ProcessarMensagemUseCase processarMensagemUseCase) {
        return new MessageListenerAdapter(processarMensagemUseCase, "executar");
    }

}
