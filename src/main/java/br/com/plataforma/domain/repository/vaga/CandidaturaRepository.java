package br.com.plataforma.domain.repository.vaga;

import br.com.plataforma.domain.entity.vaga.Candidatura;
import br.com.plataforma.shared.enumeration.StatusCandidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    Optional<Candidatura> findByVagaIdAndAlunoId(Long vagaId, Long alunoId);

    boolean existsByVagaIdAndAlunoId(Long vagaId, Long alunoId);

    List<Candidatura> findByAlunoId(Long alunoId);

    List<Candidatura> findByVagaId(Long vagaId);

    List<Candidatura> findByStatus(StatusCandidatura status);

    List<Candidatura> findByVagaIdAndStatus(Long vagaId, StatusCandidatura status);

    long countByVagaId(Long vagaId);
}
