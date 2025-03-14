package br.com.projetobase.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@EqualsAndHashCode
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SEQ_USER_ROLE", sequenceName = "SEQ_USER_ROLE", allocationSize = 1)
@Table(name = "TB_USER_ROLE")
public class UsuarioRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE")
    @Column(name = "UR_ID")
    private Long id;

    @JoinColumn(name = "US_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuarioEntity;

    @JoinColumn(name = "RL_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private RolesEntity rolesEntity;

}
