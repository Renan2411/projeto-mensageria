package br.com.projetobase.domain.usecase.usuario.criar.converter;

import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioOutput;
import lombok.Builder;

@Builder
public class CriarUsuarioOutputConverter {

    public CriarUsuarioOutput converter(UsuarioEntity usuarioEntity) {
        return CriarUsuarioOutput.builder()
                .id(usuarioEntity.getId())
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .cpf(usuarioEntity.getCpf())
                .dataNascimento(usuarioEntity.getDataNascimento())
                .build();
    }

}
