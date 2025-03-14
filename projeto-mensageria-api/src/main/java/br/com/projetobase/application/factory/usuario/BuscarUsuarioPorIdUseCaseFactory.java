package br.com.projetobase.application.factory.usuario;

import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.buscarporid.BuscarUsuarioPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarUsuarioPorIdUseCaseFactory {

    @Bean
    public BuscarUsuarioPorIdUseCase criarBuscarUsuarioPorIdUseCase(IUsuarioDataProvider iUsuarioDataProvider) {
        return BuscarUsuarioPorIdUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .build();
    }

}
