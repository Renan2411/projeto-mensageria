package br.com.projetobase.application.factory.usuario;

import br.com.projetobase.domain.interfaces.dataprovider.IRolesDataProvider;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioRoleDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioUseCase;
import br.com.projetobase.domain.usecase.usuario.criar.converter.CriarUsuarioOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CriarUsuarioUseCaseFactory {

    @Bean
    @DependsOn({"criarCriarUsuarioOutputConverter"})
    public CriarUsuarioUseCase criarUsuarioUseCase(IUsuarioDataProvider iUsuarioDataProvider,
                                                   IRolesDataProvider iRolesDataProvider,
                                                   IUsuarioRoleDataProvider iUsuarioRoleDataProvider,
                                                   PasswordEncoder passwordEncoder,
                                                   CriarUsuarioOutputConverter outputConverter) {
        return CriarUsuarioUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .iRolesDataProvider(iRolesDataProvider)
                .iUsuarioRoleDataProvider(iUsuarioRoleDataProvider)
                .passwordEncoder(passwordEncoder)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean
    public CriarUsuarioOutputConverter criarCriarUsuarioOutputConverter() {
        return CriarUsuarioOutputConverter.builder().build();
    }

}
