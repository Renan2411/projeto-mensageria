package br.com.projetobase.adapter.gateway.dataprovider.roles;

import br.com.projetobase.domain.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByNome(RolesEntity.EnumRole nome);

    @Query("SELECT roles FROM RolesEntity roles " +
            "INNER JOIN UsuarioRoleEntity usuarioRoleEntity ON usuarioRoleEntity.rolesEntity.id = roles.id " +
            "WHERE usuarioRoleEntity.usuarioEntity.id = :idUsuario")
    List<RolesEntity> buscarPorIdUsuario(@Param("idUsuario") Long idUsuario);

}
