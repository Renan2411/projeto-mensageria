package br.com.projetobase.domain.usecase.usuario.criar;

import br.com.projetobase.domain.entity.RolesEntity;
import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.entity.UsuarioRoleEntity;
import br.com.projetobase.domain.exception.generic.GenericNotFoundException;
import br.com.projetobase.domain.exception.generic.GenericValidationException;
import br.com.projetobase.domain.interfaces.dataprovider.IRolesDataProvider;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioRoleDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.converter.CriarUsuarioOutputConverter;
import br.com.projetobase.domain.validation.Validator;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Builder
public class CriarUsuarioUseCase {

    private final IUsuarioDataProvider iUsuarioDataProvider;
    private final IRolesDataProvider iRolesDataProvider;
    private final IUsuarioRoleDataProvider iUsuarioRoleDataProvider;
    private final PasswordEncoder passwordEncoder;
    private final CriarUsuarioOutputConverter outputConverter;

    public CriarUsuarioOutput executar(CriarUsuarioInput entrada) {
        validarEntrada(entrada);
        validarSeCpfJaExiste(entrada.getCpf());
        validarSeEmailJaExiste(entrada.getEmail());

        UsuarioEntity usuarioEntity = criarUsuarioEntity(entrada);
        usuarioEntity = salvarUsuario(usuarioEntity);
        vincularRole(usuarioEntity);

        return outputConverter.converter(usuarioEntity);
    }

    private void validarEntrada(CriarUsuarioInput entrada) {
        Validator.of(entrada)
                .validate(Objects.nonNull(entrada.getCpf()), "Ausência do cpf.")
                .validate(Objects.nonNull(entrada.getNome()), "Ausência do nome.")
                .validate(Objects.nonNull(entrada.getEmail()), "Ausência do email.")
                .validate(Objects.nonNull(entrada.getSenha()), "Ausência da senha")
                .get();
    }

    private void validarSeCpfJaExiste(String cpf) {
        if (!iUsuarioDataProvider.existePorCpf(cpf)) {
            return;
        }

        throw new GenericValidationException(String.format("Usuário com CPF %s já cadastrado.", cpf));
    }

    private void validarSeEmailJaExiste(String email) {
        if (!iUsuarioDataProvider.existePorEmail(email)) {
            return;
        }

        throw new GenericValidationException(String.format("Usuário com email %s já cadastrado.", email));
    }

    private UsuarioEntity criarUsuarioEntity(CriarUsuarioInput entrada) {
        return UsuarioEntity.builder()
                .nome(entrada.getNome())
                .senha(passwordEncoder.encode(entrada.getSenha()))
                .cpf(entrada.getCpf())
                .email(entrada.getEmail())
                .dataNascimento(entrada.getDataNascimento())
                .build();
    }

    private UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity) {
        return iUsuarioDataProvider.criar(usuarioEntity);
    }

    private void vincularRole(UsuarioEntity usuarioEntity) {
        RolesEntity rolesEntity = iRolesDataProvider.buscarPorNome(RolesEntity.EnumRole.USUARIO_COMUM)
                .orElseThrow(() -> new GenericNotFoundException("Role não encontrada"));

        UsuarioRoleEntity usuarioRoleEntity = UsuarioRoleEntity.builder()
                .usuarioEntity(usuarioEntity)
                .rolesEntity(rolesEntity)
                .build();

        iUsuarioRoleDataProvider.criar(usuarioRoleEntity);
    }

}
