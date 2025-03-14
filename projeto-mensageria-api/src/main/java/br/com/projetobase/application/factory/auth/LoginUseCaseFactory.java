package br.com.projetobase.application.factory.auth;

import br.com.projetobase.application.config.auth.CustomUserDetailService;
import br.com.projetobase.application.config.auth.JwtService;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.auth.LoginUseCase;
import br.com.projetobase.domain.usecase.auth.converter.LoginOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoginUseCaseFactory {

    @Bean
    @DependsOn("criarLoginOutputConverter")
    public LoginUseCase criarLoginUseCase(PasswordEncoder passwordEncoder,
                                          CustomUserDetailService customUserDetailService,
                                          IUsuarioDataProvider iUsuarioDataProvider,
                                          JwtService jwtService,
                                          LoginOutputConverter outputConverter) {
        return LoginUseCase.builder()
                .passwordEncoder(passwordEncoder)
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .jwtService(jwtService)
                .outputConverter(outputConverter)
                .customUserDetailService(customUserDetailService)
                .build();
    }

    @Bean
    public LoginOutputConverter criarLoginOutputConverter() {
        return LoginOutputConverter.builder().build();
    }

}
