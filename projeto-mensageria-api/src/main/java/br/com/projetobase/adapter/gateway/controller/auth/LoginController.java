package br.com.projetobase.adapter.gateway.controller.auth;

import br.com.projetobase.domain.usecase.auth.LoginInput;
import br.com.projetobase.domain.usecase.auth.LoginOutput;
import br.com.projetobase.domain.usecase.auth.LoginUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private LoginUseCase useCase;

    @ApiOperation(value = "Gera um token para acesso a api.")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Sucesso ao gerar token."),
            @ApiResponse(code = 400, message = "Entrada para geração de token inválida.")
    })
    @PostMapping
    public ResponseEntity<LoginOutput> login(@RequestBody LoginInput loginInput) {
        LoginOutput output = useCase.executar(loginInput);
        return new ResponseEntity<>(output, HttpStatus.ACCEPTED);
    }

}
