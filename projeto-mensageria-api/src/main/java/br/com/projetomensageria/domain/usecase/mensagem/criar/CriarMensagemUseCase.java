package br.com.projetomensageria.domain.usecase.mensagem.criar;

import br.com.projetomensageria.application.config.RabbitmlConfig;
import br.com.projetomensageria.application.mensageria.Sender;
import br.com.projetomensageria.domain.entity.dto.MensagemDTO;
import br.com.projetomensageria.domain.validation.Validator;
import lombok.Builder;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.OffsetDateTime;
import java.util.Objects;

@Builder
public class CriarMensagemUseCase {

    private final Sender sender;

    public void executar(CriarMensagemInput entrada) throws InterruptedException {
        validarEntrada(entrada);

        Long idUsuarioLogado = getIdUsuarioLogado();
        MensagemDTO mensagemDTO = criarMensagemDTO(entrada, idUsuarioLogado);
        sender.enviar(RabbitmlConfig.TOPIC_EXCHANGE_MENSAGEM, RabbitmlConfig.ROUT_KEY_MENSAGEM, mensagemDTO);
    }

    private void validarEntrada(CriarMensagemInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getIdUsuarioDestino()), "Ausência do id do usuário de destino")
                .validate(Objects.nonNull(entrada.getConteudo()), "Ausência do conteúdo da mensagem")
                .get();
    }

    private static Long getIdUsuarioLogado() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    private static MensagemDTO criarMensagemDTO(CriarMensagemInput entrada, Long idUsuarioLogado) {
        return MensagemDTO.builder()
                .idUsuarioRementente(idUsuarioLogado)
                .conteudoMensagem(entrada.getConteudo())
                .idUsuarioDestino(entrada.getIdUsuarioDestino())
                .dataHoraEnvio(OffsetDateTime.now())
                .build();
    }

}
