package br.com.projetomensageria.domain.usecase.usuario.editar;

import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetomensageria.domain.usecase.usuario.editar.converter.EditarUsuarioOutputConverter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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