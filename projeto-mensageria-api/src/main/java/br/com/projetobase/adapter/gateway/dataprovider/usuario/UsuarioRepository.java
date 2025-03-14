package br.com.projetobase.adapter.gateway.dataprovider.usuario;

import br.com.projetobase.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    @Modifying
    @Query("DELETE FROM UsuarioEntity usuarioEntity WHERE usuarioEntity.id = :id")
    void excluirPorId(@Param("id") Long idUsuario);

    Optional<UsuarioEntity> findByCpf(String cpf);

    Optional<UsuarioEntity> findByEmail(String email);
}
