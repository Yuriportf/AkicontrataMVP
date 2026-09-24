package br.com.plataforma.domain.service.notificacao;

import br.com.plataforma.domain.entity.notificacao.Notificacao;
import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.repository.notificacao.NotificacaoRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    @Transactional(readOnly = true)
    public Notificacao buscarPorId(Long id) {
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação", id));
    }

    @Transactional(readOnly = true)
    public List<Notificacao> listarPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdOrderByCriadoEmDesc(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<Notificacao> listarNaoLidas(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdAndLidaFalseOrderByCriadoEmDesc(usuarioId);
    }

    @Transactional(readOnly = true)
    public long contarNaoLidas(Long usuarioId) {
        return notificacaoRepository.countByUsuarioIdAndLidaFalse(usuarioId);
    }

    @Transactional
    public Notificacao criar(Usuario usuario, String titulo, String mensagem) {
        if (usuario == null) {
            throw new BusinessException("Usuário é obrigatório");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new BusinessException("Título da notificação é obrigatório");
        }

        Notificacao notificacao = Notificacao.builder()
                .usuario(usuario)
                .titulo(titulo)
                .mensagem(mensagem)
                .lida(false)
                .build();

        return notificacaoRepository.save(notificacao);
    }

    @Transactional
    public Notificacao marcarComoLida(Long id) {
        Notificacao notificacao = buscarPorId(id);
        notificacao.setLida(true);
        return notificacaoRepository.save(notificacao);
    }

    @Transactional
    public void marcarTodasComoLidas(Long usuarioId) {
        List<Notificacao> naoLidas = notificacaoRepository
                .findByUsuarioIdAndLidaFalseOrderByCriadoEmDesc(usuarioId);
        naoLidas.forEach(n -> n.setLida(true));
        notificacaoRepository.saveAll(naoLidas);
    }

    @Transactional
    public void deletar(Long id) {
        Notificacao notificacao = buscarPorId(id);
        notificacaoRepository.delete(notificacao);
    }
}
