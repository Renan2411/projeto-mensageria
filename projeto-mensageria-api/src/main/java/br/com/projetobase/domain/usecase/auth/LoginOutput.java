package br.com.projetobase.domain.usecase.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Saída para o serviço de login da aplicação")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutput {

    @ApiModelProperty(value = "Login do usuário", example = "renan.monteiro@gmail.com")
    private String login;

    @ApiModelProperty(value = "Token de autenticação para requisições", example = "123")
    private String token;

}
