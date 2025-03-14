package br.com.projetobase.domain.usecase.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Entrada para o serviço de login da aplicação")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInput {

    @ApiModelProperty(value = "Login do usuário na aplicação", example = "renan.monteiro@gmail.com")
    private String login;

    @ApiModelProperty(value = "Senha do usuário na aplicação", example = "123")
    private String senha;

}
