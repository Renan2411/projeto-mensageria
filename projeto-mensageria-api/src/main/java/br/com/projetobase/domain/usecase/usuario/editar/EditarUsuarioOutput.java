package br.com.projetobase.domain.usecase.usuario.editar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@ApiModel(value = "Entrada para o serviço de edição de usuários")
@Data
@Builder
public class EditarUsuarioOutput {

    @ApiModelProperty(value = "id do usuário editado", example = "1")
    private Long id;

    @ApiModelProperty(value = "nome do usuário editado", example = "Renan")
    private String nome;

    @ApiModelProperty(value = "email do usuário editado", example = "renan@gmail.com")
    private String email;

    @ApiModelProperty(value = "cpf do usuário editado", example = "1111111")
    private String cpf;

    @ApiModelProperty(value = "data de nascimento do usuário editado", example = "24/11/2002")
    private OffsetDateTime dataNascimento;

}
