package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.usecase.usuario.buscarporid.BuscarUsuarioPorIdOutput;
import br.com.projetobase.domain.usecase.usuario.buscarporid.BuscarUsuarioPorIdUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Usuários", tags = "Usuários", description = "Manutenção de usuários do sistema")
@Transactional
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/{id}")
public class BuscarUsuarioPorIdController {

    private final BuscarUsuarioPorIdUseCase useCase;

    @ApiOperation(value = "Buscar um usuário por id.")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Usuário retornado com sucesso."),
            @ApiResponse(code = 404, message = "Usuário não encontrado.")
    })
    @GetMapping
    public ResponseEntity<BuscarUsuarioPorIdOutput> buscarPorId(@PathVariable("id") Long idUsuario) {
        BuscarUsuarioPorIdOutput output = useCase.executar(idUsuario);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
