package br.com.projetomensageria.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensagemDTO implements Serializable {

    private Long idUsuarioRementente;
    private Long idUsuarioDestino;
    private String conteudoMensagem;
    private OffsetDateTime dataHoraEnvio;
    private boolean reprocessamento;

}
