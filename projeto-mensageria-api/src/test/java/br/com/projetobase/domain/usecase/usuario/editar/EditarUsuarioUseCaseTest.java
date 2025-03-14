package br.com.projetobase.domain.usecase.usuario.editar;

import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioUseCase;
import br.com.projetobase.domain.usecase.usuario.criar.converter.CriarUsuarioOutputConverter;
import br.com.projetobase.domain.usecase.usuario.editar.converter.EditarUsuarioOutputConverter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class EditarUsuarioUseCaseTest {

    @Mock
    private IUsuarioDataProvider iUsuarioDataProvider;

    EditarUsuarioUseCase useCase;

    @Before
    public void prepararTeste() {
        useCase = criarUseCase();
    }

    public EditarUsuarioUseCase criarUseCase() {
        return EditarUsuarioUseCase.builder()
                .iUsuarioDataProvider(iUsuarioDataProvider)
                .outputConverter(EditarUsuarioOutputConverter.builder().build())
                .build();
    }

}