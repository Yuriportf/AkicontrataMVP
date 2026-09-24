package br.com.plataforma.domain.repository.convite;

import br.com.plataforma.domain.entity.convite.ConviteEntrevista;
import br.com.plataforma.shared.enumeration.StatusConvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConviteEntrevistaRepository extends JpaRepository<ConviteEntrevista, Long> {

    List<ConviteEntrevista> findByAlunoId(Long alunoId);

    List<ConviteEntrevista> findByEmpresaId(Long empresaId);

    List<ConviteEntrevista> findByAlunoIdAndStatus(Long alunoId, StatusConvite status);

    List<ConviteEntrevista> findByEmpresaIdAndStatus(Long empresaId, StatusConvite status);

    List<ConviteEntrevista> findByVagaId(Long vagaId);
}
