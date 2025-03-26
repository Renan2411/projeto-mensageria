package br.com.projetomensageria.domain.usecase.mensagem.criar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@ApiModel(value = "Entrada do serviço de enviar mensagem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarMensagemInput {

    @ApiModelProperty(value = "Id do usuário que esta enviando a mensagem", example = "1", required = true)
    private Long idUsuarioRemetente;

    @ApiModelProperty(value = "Id do usuário que irá receber a mensagem", example = "1", required = true)
    private Long idUsuarioDestino;

    @ApiModelProperty(value = "Conteúdo da mensagem", example = "Enviando Mensagem", required = true)
    private String conteudo;

    @ApiModelProperty(value = "Data e hora de envio da mensagme", required = true)
    private OffsetDateTime dataHoraEnvio;

}
