package br.com.projetomensageria.domain.usecase.auth;

import br.com.projetomensageria.application.config.auth.CustomUserDetailService;
import br.com.projetomensageria.application.config.auth.JwtService;
import br.com.projetomensageria.domain.entity.UsuarioEntity;
import br.com.projetomensageria.domain.exception.UsuarioNaoEncontradoException;
import br.com.projetomensageria.domain.exception.generic.GenericValidationException;
import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetomensageria.domain.usecase.auth.converter.LoginOutputConverter;
import br.com.projetomensageria.domain.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class LoginUseCase {

    private final PasswordEncoder passwordEncoder;
    private final IUsuarioDataProvider iUsuarioDataProvider;
    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;
    private final LoginOutputConverter outputConverter;

    public LoginOutput executar(LoginInput entrada) {
        validarEntrada(entrada);

        UsuarioEntity usuarioEntity = buscarUsuario(entrada);
        UserDetails user = customUserDetailService.loadUserByUsername(usuarioEntity.getCpf());
        boolean senhasBatem = passwordEncoder.matches(entrada.getSenha(), user.getPassword());

        if (!senhasBatem) {
            throw new GenericValidationException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuarioEntity);
        return outputConverter.converter(usuarioEntity, token);
    }

    private UsuarioEntity buscarUsuario(LoginInput entrada) {
        return iUsuarioDataProvider.buscarPorEmail(entrada.getLogin())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Email", entrada.getLogin()));
    }

    private void validarEntrada(LoginInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getLogin()), "Ausência do login.")
                .validate(Objects.nonNull(entrada.getSenha()), "Ausência da senha.")
                .get();
    }

}
