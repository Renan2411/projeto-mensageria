package br.com.projetobase.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@SequenceGenerator(name = "SEQ_ROLES", allocationSize = 1, sequenceName = "SEQ_ROLES")
@Table(name = "TB_ROLES")
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLES")
    @Column(name = "RL_ID")
    private Long id;

    @Column(name = "RL_NOME")
    @Enumerated(EnumType.STRING)
    private EnumRole nome;

    @Column(name = "RL_DESCRICAO")
    private String descricao;

    public enum EnumRole {
        ADMINISTRADOR,
        USUARIO_COMUM
    }

}
