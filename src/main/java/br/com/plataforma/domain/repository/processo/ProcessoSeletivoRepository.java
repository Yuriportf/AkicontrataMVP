package br.com.plataforma.domain.repository.processo;

import br.com.plataforma.domain.entity.processo.ProcessoSeletivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoSeletivoRepository extends JpaRepository<ProcessoSeletivo, Long> {

    List<ProcessoSeletivo> findByEmpresaId(Long empresaId);

    List<ProcessoSeletivo> findByEmpresaIdAndAtivoTrue(Long empresaId);

    List<ProcessoSeletivo> findByAtivoTrue();

    List<ProcessoSeletivo> findByVagaId(Long vagaId);
}
