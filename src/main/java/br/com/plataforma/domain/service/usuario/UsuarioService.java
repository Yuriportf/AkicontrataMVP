package br.com.plataforma.domain.service.usuario;

import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.repository.usuario.UsuarioRepository;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarPorTipo(TipoUsuario tipo) {
        return usuarioRepository.findByTipoUsuario(tipo);
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException("Já existe usuário com o email: " + usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = buscarPorId(id);

        if (!usuario.getEmail().equals(dados.getEmail())
                && usuarioRepository.existsByEmail(dados.getEmail())) {
            throw new BusinessException("Email já em uso: " + dados.getEmail());
        }

        usuario.setEmail(dados.getEmail());
        usuario.setTipoUsuario(dados.getTipoUsuario());
        usuario.setAtivo(dados.getAtivo());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void desativar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
