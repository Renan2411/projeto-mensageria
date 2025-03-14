package br.com.projetobase.domain.exception;

import br.com.projetobase.domain.exception.generic.GenericNotFoundException;

public class UsuarioNaoEncontradoException extends GenericNotFoundException {

    public UsuarioNaoEncontradoException(Long idUsuario) {
        super(String.format("Usuário de id %d não encontrado.", idUsuario));
    }

    public UsuarioNaoEncontradoException(String cpf) {
        super(String.format("Usuário de cpf %s não encontrado.", cpf));
    }

    public UsuarioNaoEncontradoException(String campo, String valorCampo) {
        super(String.format("Usuário de %s %s não encontrado.", campo, valorCampo));
    }

}
