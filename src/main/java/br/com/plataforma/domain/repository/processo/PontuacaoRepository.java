package br.com.plataforma.domain.repository.processo;

import br.com.plataforma.domain.entity.processo.Pontuacao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {

    Optional<Pontuacao> findByProcessoSeletivoIdAndAlunoId(Long processoSeletivoId, Long alunoId);

    List<Pontuacao> findByProcessoSeletivoId(Long processoSeletivoId);

    List<Pontuacao> findByAlunoId(Long alunoId);

    /**
     * Ranking do processo seletivo — maior pontuação primeiro.
     */
    @Query("""
        SELECT p FROM Pontuacao p
        WHERE p.processoSeletivo.id = :processoId
        ORDER BY p.pontuacaoTotal DESC
    """)
    List<Pontuacao> rankingByProcessoId(@Param("processoId") Long processoId);

    /**
     * Top N candidatos de um processo.
     */
    @Query("""
        SELECT p FROM Pontuacao p
        WHERE p.processoSeletivo.id = :processoId
        ORDER BY p.pontuacaoTotal DESC
    """)
    List<Pontuacao> topCandidatos(@Param("processoId") Long processoId, Pageable pageable);
}
