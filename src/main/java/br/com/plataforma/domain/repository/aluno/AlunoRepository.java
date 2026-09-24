package br.com.plataforma.domain.repository.aluno;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.shared.enumeration.TipoAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByUsuarioId(Long usuarioId);

    Optional<Aluno> findByMatricula(String matricula);

    Optional<Aluno> findByCpf(String cpf);

    boolean existsByMatriculaAndCursoId(String matricula, Long cursoId);

    List<Aluno> findByTipoAluno(TipoAluno tipoAluno);

    List<Aluno> findByCursoId(Long cursoId);

    List<Aluno> findByCampusId(Long campusId);

    List<Aluno> findByCursoIdAndCampusId(Long cursoId, Long campusId);

    List<Aluno> findByNomeCompletoContainingIgnoreCase(String nome);
}
