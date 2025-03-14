package br.com.projetobase.domain.usecase.usuario.criar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@ApiModel(value = "Saída da criação de um usuário")
@Data
@Builder
public class CriarUsuarioOutput {

    @ApiModelProperty(value = "Id do usuário cadastrado", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do usuário cadastrado", example = "Renan")
    private String nome;

    @ApiModelProperty(value = "Email do usuário cadastrado", example = "renan@gmail.com")
    private String email;

    @ApiModelProperty(value = "CPF do usuário cadastrado", example = "111111111111")
    private String cpf;

    @ApiModelProperty(value = "Data de nascimento do usuário cadastrado", example = "24/11/2002")
    private OffsetDateTime dataNascimento;

}
