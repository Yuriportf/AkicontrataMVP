package br.com.plataforma.domain.repository.usuario;

import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);

    List<Usuario> findByAtivoTrue();
}
