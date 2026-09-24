package br.com.plataforma.domain.service.usuario;

import br.com.plataforma.domain.entity.usuario.Role;
import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.repository.usuario.RoleRepository;
import br.com.plataforma.domain.repository.usuario.UsuarioRepository;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import br.com.plataforma.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Cadastra um novo usuário com senha criptografada e role padrão.
     */
    @Transactional
    public Usuario cadastrar(String email, String senhaBruta, TipoUsuario tipoUsuario) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new BusinessException("Já existe usuário com o email: " + email);
        }

        Usuario usuario = Usuario.builder()
                .email(email)
                .senha(passwordEncoder.encode(senhaBruta))
                .tipoUsuario(tipoUsuario)
                .ativo(true)
                .build();

        // adiciona role padrão de acordo com o tipo
        String nomeRole = switch (tipoUsuario) {
            case ALUNO -> "ROLE_ALUNO";
            case EMPRESA -> "ROLE_EMPRESA";
            case ADMIN -> "ROLE_ADMIN";
        };

        roleRepository.findByNome(nomeRole).ifPresent(role -> {
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            usuario.setRoles(roles);
        });

        return usuarioRepository.save(usuario);
    }

    /**
     * Verifica se a senha confere com o hash salvo.
     */
    public boolean senhaConfere(String senhaBruta, String senhaHash) {
        return passwordEncoder.matches(senhaBruta, senhaHash);
    }
}
