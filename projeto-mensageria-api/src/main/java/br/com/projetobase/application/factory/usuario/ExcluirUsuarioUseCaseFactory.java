package br.com.projetobase.application.factory.usuario;

import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.excluir.ExcluirUsuarioUseCase;
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
