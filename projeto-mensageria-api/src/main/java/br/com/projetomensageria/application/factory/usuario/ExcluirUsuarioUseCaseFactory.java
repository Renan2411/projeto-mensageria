package br.com.projetomensageria.application.factory.usuario;

import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetomensageria.domain.usecase.usuario.excluir.ExcluirUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcluirUsuarioUseCaseFactory {

    @Bean
    public ExcluirUsuarioUseCase criarExcluirUsuarioUseCase(IUsuarioDataProvider iUsuarioDataProvider) {
        return ExcluirUsuarioUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .build();
    }

}
