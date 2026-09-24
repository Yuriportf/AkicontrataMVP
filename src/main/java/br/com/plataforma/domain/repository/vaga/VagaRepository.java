package br.com.plataforma.domain.repository.vaga;

import br.com.plataforma.domain.entity.vaga.Vaga;
import br.com.plataforma.shared.enumeration.ModalidadeVaga;
import br.com.plataforma.shared.enumeration.StatusVaga;
import br.com.plataforma.shared.enumeration.TipoVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByEmpresaId(Long empresaId);

    List<Vaga> findByStatus(StatusVaga status);

    List<Vaga> findByEmpresaIdAndStatus(Long empresaId, StatusVaga status);

    List<Vaga> findByModalidade(ModalidadeVaga modalidade);

    List<Vaga> findByTipo(TipoVaga tipo);

    List<Vaga> findByCidadeAndUf(String cidade, String uf);

    List<Vaga> findByTituloContainingIgnoreCase(String titulo);

    List<Vaga> findByStatusOrderByDataPublicacaoDesc(StatusVaga status);
}
