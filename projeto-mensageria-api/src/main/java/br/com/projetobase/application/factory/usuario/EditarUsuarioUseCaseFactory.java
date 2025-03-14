package br.com.projetobase.application.factory.usuario;

import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioUseCase;
import br.com.projetobase.domain.usecase.usuario.editar.converter.EditarUsuarioOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class EditarUsuarioUseCaseFactory {

    @Bean
    @DependsOn("criarEditarUsuarioOutputConverter")
    public EditarUsuarioUseCase criarEditarUsuarioUseCase(IUsuarioDataProvider iUsuarioDataProvider,
                                                          EditarUsuarioOutputConverter outputConverter
    ) {
        return EditarUsuarioUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .outputConverter(outputConverter)
                .build();
    }

    @Bean
    public EditarUsuarioOutputConverter criarEditarUsuarioOutputConverter() {
        return EditarUsuarioOutputConverter.builder().build();
    }

}
