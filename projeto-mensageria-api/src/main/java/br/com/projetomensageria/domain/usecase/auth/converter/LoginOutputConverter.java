package br.com.projetomensageria.domain.usecase.auth.converter;

import br.com.projetomensageria.domain.entity.UsuarioEntity;
import br.com.projetomensageria.domain.usecase.auth.LoginOutput;
import lombok.Builder;

@Builder
public class LoginOutputConverter {

    public LoginOutput converter(UsuarioEntity usuarioEntity, String token) {
        return LoginOutput.builder()
                .login(usuarioEntity.getEmail())
                .token(token)
                .build();
    }

}
