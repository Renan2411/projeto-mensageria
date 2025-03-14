package br.com.projetobase.domain.usecase.usuario.criar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@ApiModel(value = "Entrada do serviço de criar usuários")
@Data
@Builder
public class CriarUsuarioInput {

    @ApiModelProperty(value = "Nome do usuário cadastrado", example = "Renan")
    private String nome;

    @ApiModelProperty(value = "Email do usuário cadastrado", example = "renan@gmail.com")
    private String email;

    @ApiModelProperty(value = "CPF do usuário cadastrado", example = "111111111111")
    private String cpf;

    @ApiModelProperty(value = "Senha do usuário cadastrado", example = "111111111111")
    private String senha;

    @ApiModelProperty(value = "Data de nascimento do usuário cadastrado", example = "24/11/2002")
    private OffsetDateTime dataNascimento;

}
