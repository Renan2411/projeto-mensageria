package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioInput;
import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioOutput;
import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Usuários", tags = "Usuários", description = "Manutenção de usuários do sistema")
@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/usuarios/{id}")
public class EditarUsuarioController {

    private final EditarUsuarioUseCase useCase;

    @ApiOperation(value = "Editar um usuário.")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Sucesso ao editar usuário."),
            @ApiResponse(code = 400, message = "Entrada para edição de usuário inválida."),
            @ApiResponse(code = 404, message = "Usuário não encontrado.")
    })
    @PutMapping
    public ResponseEntity<EditarUsuarioOutput> editarUsuario(@PathVariable("id") Long idUsuario, @RequestBody EditarUsuarioInput input) {
        EditarUsuarioOutput output = useCase.executar(idUsuario, input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
