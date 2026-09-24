package br.com.plataforma.domain.service.processo;

import br.com.plataforma.domain.entity.processo.Questionario;
import br.com.plataforma.domain.repository.processo.QuestionarioRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionarioService {

    private final QuestionarioRepository questionarioRepository;

    @Transactional(readOnly = true)
    public Questionario buscarPorId(Long id) {
        return questionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Questionário", id));
    }

    @Transactional(readOnly = true)
    public List<Questionario> listarPorProcesso(Long processoId) {
        return questionarioRepository.findByProcessoSeletivoId(processoId);
    }

    @Transactional
    public Questionario criar(Questionario questionario) {
        validar(questionario);
        return questionarioRepository.save(questionario);
    }

    @Transactional
    public Questionario atualizar(Long id, Questionario dados) {
        Questionario questionario = buscarPorId(id);
        validar(dados);

        questionario.setTitulo(dados.getTitulo());
        questionario.setDescricao(dados.getDescricao());
        questionario.setPontuacaoMinima(dados.getPontuacaoMinima());

        return questionarioRepository.save(questionario);
    }

    @Transactional
    public void deletar(Long id) {
        Questionario questionario = buscarPorId(id);
        questionarioRepository.delete(questionario);
    }

    private void validar(Questionario questionario) {
        if (questionario.getTitulo() == null || questionario.getTitulo().isBlank()) {
            throw new BusinessException("Título do questionário é obrigatório");
        }
        if (questionario.getProcessoSeletivo() == null) {
            throw new BusinessException("Processo seletivo é obrigatório");
        }
    }
}
