package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.usecase.usuario.excluir.ExcluirUsuarioUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Usuários", tags = "Usuários", description = "Manutenção de usuários do sistema")
@Transactional
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/{id}")
public class ExcluirUsuarioController {

    private final ExcluirUsuarioUseCase useCase;

    @ApiOperation(value = "Excluir um usuário.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Sucesso ao excluir usuário."),
            @ApiResponse(code = 404, message = "Usuário não encontrado.")
    })
    @DeleteMapping
    public ResponseEntity<Void> excluir(@PathVariable("id") Long idUsuario) {
        useCase.executar(idUsuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
