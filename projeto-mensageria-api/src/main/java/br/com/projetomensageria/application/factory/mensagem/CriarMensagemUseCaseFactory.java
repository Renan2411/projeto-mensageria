package br.com.projetomensageria.application.factory.mensagem;

import br.com.projetomensageria.application.mensageria.Sender;
import br.com.projetomensageria.domain.usecase.mensagem.criar.CriarMensagemUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarMensagemUseCaseFactory {

    @Bean
    public CriarMensagemUseCase criarCriarMensagemUseCase(Sender sender) {
        return CriarMensagemUseCase.builder()
                .sender(sender)
                .build();
    }

}
