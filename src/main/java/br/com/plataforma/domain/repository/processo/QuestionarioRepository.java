package br.com.plataforma.domain.repository.processo;

import br.com.plataforma.domain.entity.processo.Questionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

    List<Questionario> findByProcessoSeletivoId(Long processoSeletivoId);

    List<Questionario> findByProcessoSeletivoIdOrderById(Long processoSeletivoId);
}
