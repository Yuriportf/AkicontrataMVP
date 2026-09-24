package br.com.plataforma.domain.service.processo;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.domain.entity.processo.Pontuacao;
import br.com.plataforma.domain.entity.processo.ProcessoSeletivo;
import br.com.plataforma.domain.repository.processo.PontuacaoRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PontuacaoService {

    private final PontuacaoRepository pontuacaoRepository;

    @Transactional(readOnly = true)
    public Pontuacao buscarPorId(Long id) {
        return pontuacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pontuação", id));
    }

    @Transactional(readOnly = true)
    public Pontuacao buscarPorProcessoEAluno(Long processoId, Long alunoId) {
        return pontuacaoRepository.findByProcessoSeletivoIdAndAlunoId(processoId, alunoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pontuação não encontrada para processo " + processoId + " e aluno " + alunoId));
    }

    @Transactional(readOnly = true)
    public List<Pontuacao> rankingDoProcesso(Long processoId) {
        return pontuacaoRepository.rankingByProcessoId(processoId);
    }

    @Transactional(readOnly = true)
    public List<Pontuacao> topCandidatos(Long processoId, int limite) {
        Pageable pageable = PageRequest.of(0, limite);
        return pontuacaoRepository.topCandidatos(processoId, pageable);
    }

    /**
     * Registra/atualiza a pontuação de um aluno em um processo seletivo.
     * Calcula o percentual com base na pontuação máxima possível.
     */
    @Transactional
    public Pontuacao registrar(ProcessoSeletivo processo, Aluno aluno,
                               BigDecimal total, BigDecimal maxima) {
        if (processo == null || aluno == null) {
            throw new BusinessException("Processo e aluno são obrigatórios");
        }
        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Pontuação total não pode ser negativa");
        }

        Pontuacao pontuacao = pontuacaoRepository
                .findByProcessoSeletivoIdAndAlunoId(processo.getId(), aluno.getId())
                .orElseGet(() -> Pontuacao.builder()
                        .processoSeletivo(processo)
                        .aluno(aluno)
                        .build());

        pontuacao.setPontuacaoTotal(total);

        if (maxima != null && maxima.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal percentual = total
                    .multiply(BigDecimal.valueOf(100))
                    .divide(maxima, 2, RoundingMode.HALF_UP);
            pontuacao.setPercentual(percentual);
        }

        return pontuacaoRepository.save(pontuacao);
    }

    @Transactional
    public void deletar(Long id) {
        Pontuacao pontuacao = buscarPorId(id);
        pontuacaoRepository.delete(pontuacao);
    }
}
