package br.com.plataforma.domain.repository.instituicao;

import br.com.plataforma.domain.entity.instituicao.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {

    Optional<Campus> findByNome(String nome);

    List<Campus> findByInstituicaoId(Long instituicaoId);

    List<Campus> findByUf(String uf);

    List<Campus> findByTipo(String tipo);

    /**
     * Busca os campus que oferecem um curso específico.
     * Útil para montar o select de campus no cadastro do aluno.
     */
    @Query("""
        SELECT ca FROM Curso c
        JOIN c.campi ca
        WHERE c.id = :cursoId
        ORDER BY ca.nome
    """)
    List<Campus> findByCursoId(@Param("cursoId") Long cursoId);
}
