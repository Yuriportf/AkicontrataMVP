package br.com.plataforma.domain.repository.aluno;

import br.com.plataforma.domain.entity.aluno.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByAlunoId(Long alunoId);

    boolean existsByAlunoId(Long alunoId);
}
