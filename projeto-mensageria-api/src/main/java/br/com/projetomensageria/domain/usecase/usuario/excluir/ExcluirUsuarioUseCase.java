package br.com.projetomensageria.domain.usecase.usuario.excluir;

import br.com.projetomensageria.domain.exception.generic.GenericValidationException;
import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import lombok.Builder;

import java.util.Objects;

@Builder
public class ExcluirUsuarioUseCase {

    private final IUsuarioDataProvider iUsuarioDataProvider;

    public void executar(Long idUsuario) {
        validarEntrada(idUsuario);
        excluirUsuario(idUsuario);
    }

    private void validarEntrada(Long idUsuario) {
        if (Objects.isNull(idUsuario)) {
            throw new GenericValidationException("Ausência do id do usuário");
        }
    }

    private void excluirUsuario(Long idUsuario) {
        iUsuarioDataProvider.excluir(idUsuario);
    }

}
