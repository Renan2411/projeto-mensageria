package br.com.projetobase.domain.usecase.usuario.editar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@ApiModel(value = "Entrada do serviço de editar usuários")
@Data
@Builder
public class EditarUsuarioInput {

    @ApiModelProperty(value = "nome do usuário editado", example = "Renan")
    private String nome;

    @ApiModelProperty(value = "email do usuário editado", example = "renan@gmail.com")
    private String email;

    @ApiModelProperty(value = "data de nascimento do usuário editado", example = "24/11/2002")
    private OffsetDateTime dataNascimento;

}
