package br.com.projetomensageria.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@SequenceGenerator(name = "SEQ_MENSAGEM", sequenceName = "SEQ_MENSAGEM", allocationSize = 1)
@Table(name = "tb_mensagem")
public class MensagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENSAGEM")
    @Column(name = "MS_ID")
    private Long id;

    @ManyToOne
    @Column(name = "US_ID_REMETENTE")
    private UsuarioEntity usuarioEntityRemetente;

    @ManyToOne
    @Column(name = "US_ID_DESTINATARIO")
    private UsuarioEntity usuarioEntityDestinatario;

    @Column(name = "MS_CONTEUDO")
    private String conteudo;

    @Column(name = "MS_SITUACAO")
    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    @Column(name = "MS_DTHR_ENVIO")
    private OffsetDateTime dataHoraEnvio;

    public enum EnumSituacao {
        PROCESSANDO,
        ERRO,
        ENVIADO,
        LIDO
    }

}
