package br.com.plataforma.domain.repository.aluno;

import br.com.plataforma.domain.entity.aluno.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {

    Optional<Curriculo> findByAlunoId(Long alunoId);

    boolean existsByAlunoId(Long alunoId);
}
