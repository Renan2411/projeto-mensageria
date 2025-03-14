package br.com.projetobase.adapter.gateway.dataprovider.usuario;

import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UsuarioDataProviderImpl implements IUsuarioDataProvider {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity criar(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public UsuarioEntity editar(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<UsuarioEntity> buscarPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    public void excluir(Long idUsuario) {
        usuarioRepository.excluirPorId(idUsuario);
    }

    @Override
    public Optional<UsuarioEntity> buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    @Override
    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
