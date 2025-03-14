package br.com.projetobase.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@EqualsAndHashCode
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
@Table(name = "TB_USUARIO")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @Column(name = "US_ID")
    private Long id;

    @Column(name = "US_NOME")
    private String nome;

    @Column(name = "US_EMAIL")
    private String email;

    @Column(name = "US_CPF")
    private String cpf;

    @Column(name = "US_SENHA")
    private String senha;

    @Column(name = "US_DATA_NASCIMENTO")
    private OffsetDateTime dataNascimento;

}
