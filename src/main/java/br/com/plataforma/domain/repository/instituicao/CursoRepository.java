package br.com.plataforma.domain.repository.instituicao;

import br.com.plataforma.domain.entity.instituicao.Curso;
import br.com.plataforma.shared.enumeration.NivelCurso;
import br.com.plataforma.shared.enumeration.TipoAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByTipoAluno(TipoAluno tipoAluno);

    List<Curso> findByNivel(NivelCurso nivel);

    List<Curso> findByTipoAlunoAndNivel(TipoAluno tipoAluno, NivelCurso nivel);

    List<Curso> findByArea(String area);

    List<Curso> findByInstituicaoId(Long instituicaoId);

    @Query("""
        SELECT c FROM Curso c
        JOIN c.campi ca
        WHERE ca.id = :campusId
        ORDER BY c.nivel, c.nome
    """)
    List<Curso> findByCampusId(@Param("campusId") Long campusId);

    List<Curso> findByNomeContainingIgnoreCaseOrderByNome(String nome);
}
