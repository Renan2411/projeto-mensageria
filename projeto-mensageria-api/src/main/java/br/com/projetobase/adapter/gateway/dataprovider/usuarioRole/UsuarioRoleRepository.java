package br.com.projetobase.adapter.gateway.dataprovider.usuarioRole;

import br.com.projetobase.domain.entity.UsuarioRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRoleRepository extends JpaRepository<UsuarioRoleEntity, Long> {

    @Query("SELECT usuarioRoleEntity FROM UsuarioRoleEntity usuarioRoleEntity WHERE usuarioRoleEntity.usuarioEntity.id = :id")
    Optional<UsuarioRoleEntity> buscarPorIdUsuario(@Param("id") Long idUsuario);

    List<UsuarioRoleEntity> findByRolesEntityId(Long idRole);

}
