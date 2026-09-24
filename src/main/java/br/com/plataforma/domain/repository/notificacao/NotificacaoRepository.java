package br.com.plataforma.domain.repository.notificacao;

import br.com.plataforma.domain.entity.notificacao.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByUsuarioIdOrderByCriadoEmDesc(Long usuarioId);

    List<Notificacao> findByUsuarioIdAndLidaFalseOrderByCriadoEmDesc(Long usuarioId);

    long countByUsuarioIdAndLidaFalse(Long usuarioId);
}
