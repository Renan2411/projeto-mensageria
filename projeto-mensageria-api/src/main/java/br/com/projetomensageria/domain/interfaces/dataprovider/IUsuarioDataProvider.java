package br.com.projetomensageria.domain.interfaces.dataprovider;

import br.com.projetomensageria.domain.entity.UsuarioEntity;

import java.util.Optional;

public interface IUsuarioDataProvider {

    UsuarioEntity criar(UsuarioEntity usuarioEntity);

    UsuarioEntity editar(UsuarioEntity usuarioEntity);

    boolean existePorCpf(String cpf);

    boolean existePorEmail(String email);

    Optional<UsuarioEntity> buscarPorId(Long idUsuario);

    void excluir(Long idUsuario);

    Optional<UsuarioEntity> buscarPorCpf(String cpf);

    Optional<UsuarioEntity> buscarPorEmail(String login);
}
