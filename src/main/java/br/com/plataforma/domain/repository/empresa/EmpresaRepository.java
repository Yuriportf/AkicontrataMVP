package br.com.plataforma.domain.repository.empresa;

import br.com.plataforma.domain.entity.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByUsuarioId(Long usuarioId);

    Optional<Empresa> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);

    List<Empresa> findByUf(String uf);

    List<Empresa> findByNomeFantasiaContainingIgnoreCase(String nome);

    List<Empresa> findByRazaoSocialContainingIgnoreCase(String razaoSocial);
}
