package br.com.projetomensageria.domain.usecase.usuario.editar.converter;

import br.com.projetomensageria.domain.entity.UsuarioEntity;
import br.com.projetomensageria.domain.usecase.usuario.editar.EditarUsuarioOutput;
import lombok.Builder;

@Builder
public class EditarUsuarioOutputConverter {

    public EditarUsuarioOutput converter(UsuarioEntity usuarioEntity) {
        return EditarUsuarioOutput.builder()
                .id(usuarioEntity.getId())
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .cpf(usuarioEntity.getCpf())
                .dataNascimento(usuarioEntity.getDataNascimento())
                .build();
    }

}
