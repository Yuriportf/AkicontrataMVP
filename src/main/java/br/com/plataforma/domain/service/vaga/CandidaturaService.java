package br.com.plataforma.domain.service.vaga;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.domain.entity.vaga.Candidatura;
import br.com.plataforma.domain.entity.vaga.Vaga;
import br.com.plataforma.domain.repository.vaga.CandidaturaRepository;
import br.com.plataforma.shared.enumeration.StatusCandidatura;
import br.com.plataforma.shared.enumeration.StatusVaga;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;

    @Transactional(readOnly = true)
    public Candidatura buscarPorId(Long id) {
        return candidaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidatura", id));
    }

    @Transactional(readOnly = true)
    public List<Candidatura> listarPorAluno(Long alunoId) {
        return candidaturaRepository.findByAlunoId(alunoId);
    }

    @Transactional(readOnly = true)
    public List<Candidatura> listarPorVaga(Long vagaId) {
        return candidaturaRepository.findByVagaId(vagaId);
    }

    @Transactional
    public Candidatura candidatar(Aluno aluno, Vaga vaga) {
        if (vaga.getStatus() != StatusVaga.ABERTA) {
            throw new BusinessException("Vaga não está aberta para candidaturas");
        }

        if (candidaturaRepository.existsByVagaIdAndAlunoId(vaga.getId(), aluno.getId())) {
            throw new BusinessException("Aluno já se candidatou a esta vaga");
        }

        Candidatura candidatura = Candidatura.builder()
                .aluno(aluno)
                .vaga(vaga)
                .status(StatusCandidatura.PENDENTE)
                .build();

        return candidaturaRepository.save(candidatura);
    }

    @Transactional
    public Candidatura atualizarStatus(Long id, StatusCandidatura novoStatus) {
        Candidatura candidatura = buscarPorId(id);
        candidatura.setStatus(novoStatus);
        return candidaturaRepository.save(candidatura);
    }

    @Transactional
    public void cancelar(Long id) {
        Candidatura candidatura = buscarPorId(id);
        candidaturaRepository.delete(candidatura);
    }
}
