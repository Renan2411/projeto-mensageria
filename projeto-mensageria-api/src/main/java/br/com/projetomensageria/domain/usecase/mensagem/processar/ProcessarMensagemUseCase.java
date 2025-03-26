package br.com.projetomensageria.domain.usecase.mensagem.processar;

import br.com.projetomensageria.application.config.RabbitmlConfig;
import br.com.projetomensageria.application.mensageria.Sender;
import br.com.projetomensageria.domain.entity.MensagemEntity;
import br.com.projetomensageria.domain.entity.UsuarioEntity;
import br.com.projetomensageria.domain.entity.dto.MensagemDTO;
import br.com.projetomensageria.domain.exception.UsuarioNaoEncontradoException;
import br.com.projetomensageria.domain.interfaces.dataprovider.IMensagemDataProvider;
import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import liquibase.pro.packaged.id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ProcessarMensagemUseCase {

    private final IMensagemDataProvider iMensagemDataProvider;
    private final IUsuarioDataProvider iUsuarioDataProvider;
    private final Sender sender;

    private void executar(MensagemDTO mensagemDTO) throws InterruptedException {
        log.info("==============INICIANDO PROCESSAMENTO DA MENSAGEM================");
        try {
            MensagemEntity mensagemEntity = criarMensagemEntity(mensagemDTO);
            mensagemEntity = salvarMensagem(mensagemEntity);
            try {
                UsuarioEntity usuarioEntityDestino = buscarUsuario(mensagemDTO.getIdUsuarioDestino());
                UsuarioEntity usuarioEntityRemetente = buscarUsuario(mensagemDTO.getIdUsuarioRementente());

                mensagemEntity.setUsuarioEntityDestinatario(usuarioEntityDestino);
                mensagemEntity.setUsuarioEntityRemetente(usuarioEntityRemetente);
                mensagemEntity.setSituacao(MensagemEntity.EnumSituacao.ENVIADO);
                editarMensagem(mensagemEntity);
            } catch (Exception e) {
                log.info("==========INICIANDO PROCESSAMENTO DE ERRO==========");
                mensagemEntity.setLogErro(e.getMessage());
                mensagemEntity.setSituacao(MensagemEntity.EnumSituacao.ERRO);
                editarMensagem(mensagemEntity);
                enviarMensagemNovamente(mensagemDTO);
                log.info("==========FINALIZANDO PROCESSAMENTO DE ERRO==========");
            }
        } catch (Exception e) {
            log.info("==========INICIANDO PROCESSAMENTO DE ERRO==========");
            log.info("Erro ao processar mensagem " + e.getMessage());
            enviarMensagemNovamente(mensagemDTO);
            log.info("==========FINALIZANDO PROCESSAMENTO DE ERRO==========");
        } finally {
            log.info("==============FINALIZANDO PROCESSAMENTO DA MENSAGEM================");
        }
    }

    private MensagemEntity criarMensagemEntity(MensagemDTO mensagemDTO) {
        return MensagemEntity.builder()
                .conteudo(mensagemDTO.getConteudoMensagem())
                .dataHoraEnvio(mensagemDTO.getDataHoraEnvio())
                .situacao(MensagemEntity.EnumSituacao.PROCESSANDO)
                .build();
    }

    private MensagemEntity salvarMensagem(MensagemEntity mensagemEntity) {
        return iMensagemDataProvider.salvar(mensagemEntity);
    }

    private UsuarioEntity buscarUsuario(Long idUsuario) {
        return iUsuarioDataProvider.buscarPorId(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));
    }

    private MensagemEntity editarMensagem(MensagemEntity mensagem) {
        return iMensagemDataProvider.editar(mensagem);
    }

    private void enviarMensagemNovamente(MensagemDTO mensagemDTO) throws InterruptedException {
        if (mensagemDTO.isReprocessamento()) {
            return;
        }

        mensagemDTO.setReprocessamento(true);
        sender.enviar(RabbitmlConfig.TOPIC_EXCHANGE_MENSAGEM, RabbitmlConfig.ROUT_KEY_MENSAGEM, mensagemDTO);
    }

}
