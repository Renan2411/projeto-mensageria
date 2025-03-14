package br.com.projetomensageria.domain.usecase.usuario.buscarporid;

import br.com.projetomensageria.domain.entity.UsuarioEntity;
import br.com.projetomensageria.domain.exception.UsuarioNaoEncontradoException;
import br.com.projetomensageria.domain.exception.generic.GenericValidationException;
import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import lombok.Builder;

import java.util.Objects;

@Builder
public class BuscarUsuarioPorIdUseCase {

    private final IUsuarioDataProvider iUsuarioDataProvider;

    public BuscarUsuarioPorIdOutput executar(Long idUsuario) {
        validarEntrada(idUsuario);

        UsuarioEntity usuarioEntity = buscarUsuario(idUsuario);

        return montarOutput(usuarioEntity);
    }

    private void validarEntrada(Long idUsuario) {
        if (Objects.isNull(idUsuario)) {
            throw new GenericValidationException("Ausência do id do usuário.");
        }
    }

    private UsuarioEntity buscarUsuario(Long idUsuario) {
        return iUsuarioDataProvider.buscarPorId(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));
    }

    private BuscarUsuarioPorIdOutput montarOutput(UsuarioEntity usuarioEntity) {
        return BuscarUsuarioPorIdOutput.builder()
                .id(usuarioEntity.getId())
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .cpf(usuarioEntity.getCpf())
                .dataNascimento(usuarioEntity.getDataNascimento())
                .build();
    }

}
