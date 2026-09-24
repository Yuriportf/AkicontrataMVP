package br.com.plataforma.domain.service.instituicao;

import br.com.plataforma.domain.entity.instituicao.InstituicaoEnsino;
import br.com.plataforma.domain.repository.instituicao.InstituicaoEnsinoRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituicaoEnsinoService {

    private final InstituicaoEnsinoRepository instituicaoRepository;

    @Transactional(readOnly = true)
    public InstituicaoEnsino buscarPorId(Long id) {
        return instituicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição", id));
    }

    @Transactional(readOnly = true)
    public InstituicaoEnsino buscarPorSigla(String sigla) {
        return instituicaoRepository.findBySigla(sigla)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada com sigla: " + sigla));
    }

    @Transactional(readOnly = true)
    public List<InstituicaoEnsino> listarTodas() {
        return instituicaoRepository.findAll();
    }

    @Transactional
    public InstituicaoEnsino criar(InstituicaoEnsino instituicao) {
        if (instituicao.getNome() == null || instituicao.getNome().isBlank()) {
            throw new BusinessException("Nome da instituição é obrigatório");
        }
        return instituicaoRepository.save(instituicao);
    }

    @Transactional
    public void deletar(Long id) {
        InstituicaoEnsino instituicao = buscarPorId(id);
        instituicaoRepository.delete(instituicao);
    }
}
