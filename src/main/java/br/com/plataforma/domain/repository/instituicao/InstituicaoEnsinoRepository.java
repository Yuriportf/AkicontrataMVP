package br.com.plataforma.domain.repository.instituicao;

import br.com.plataforma.domain.entity.instituicao.InstituicaoEnsino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, Long> {

    Optional<InstituicaoEnsino> findBySigla(String sigla);

    List<InstituicaoEnsino> findByUf(String uf);

    List<InstituicaoEnsino> findByNomeContainingIgnoreCase(String nome);
}
