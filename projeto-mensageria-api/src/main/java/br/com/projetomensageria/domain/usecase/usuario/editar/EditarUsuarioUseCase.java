package br.com.projetomensageria.domain.usecase.usuario.editar;

import br.com.projetomensageria.domain.entity.UsuarioEntity;
import br.com.projetomensageria.domain.exception.UsuarioNaoEncontradoException;
import br.com.projetomensageria.domain.exception.generic.GenericValidationException;
import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetomensageria.domain.usecase.usuario.editar.converter.EditarUsuarioOutputConverter;
import br.com.projetomensageria.domain.validation.Validator;
import lombok.Builder;

import java.util.Objects;

@Builder
public class EditarUsuarioUseCase {

    private final IUsuarioDataProvider iUsuarioDataProvider;
    private final EditarUsuarioOutputConverter outputConverter;

    public EditarUsuarioOutput executar(Long idUsuario, EditarUsuarioInput entrada) {
        validarEntrada(idUsuario, entrada);

        UsuarioEntity usuarioEntity = buscarUsuario(idUsuario);
        validarSeEmailJaExiste(entrada.getEmail(), usuarioEntity);
        alterarDadosUsuario(usuarioEntity, entrada);
        editarUsuario(usuarioEntity);

        return outputConverter.converter(usuarioEntity);
    }

    private void validarEntrada(Long idUsuario, EditarUsuarioInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(idUsuario), "Ausência do id do usuário.")
                .validate(Objects.nonNull(entrada.getEmail()), "Ausência do email do usuário.")
                .get();
    }

    private void validarSeEmailJaExiste(String email, UsuarioEntity usuarioEntity) {
        if (usuarioEntity.getEmail().equals(email)) {
            return;
        }

        if (!iUsuarioDataProvider.existePorEmail(email)) {
            return;
        }

        throw new GenericValidationException(String.format("Usuário com email %s já cadastrado.", email));
    }

    private UsuarioEntity buscarUsuario(Long idUsuario) {
        return iUsuarioDataProvider.buscarPorId(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));
    }

    private void alterarDadosUsuario(UsuarioEntity usuarioEntity, EditarUsuarioInput entrada) {
        usuarioEntity.setNome(entrada.getNome());
        usuarioEntity.setEmail(entrada.getEmail());
        usuarioEntity.setDataNascimento(entrada.getDataNascimento());
    }

    private void editarUsuario(UsuarioEntity usuarioEntity) {
        iUsuarioDataProvider.editar(usuarioEntity);
    }

}
