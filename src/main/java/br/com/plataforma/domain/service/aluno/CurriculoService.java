package br.com.plataforma.domain.service.aluno;

import br.com.plataforma.domain.entity.aluno.Curriculo;
import br.com.plataforma.domain.repository.aluno.CurriculoRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    @Transactional(readOnly = true)
    public Curriculo buscarPorId(Long id) {
        return curriculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Currículo", id));
    }

    @Transactional(readOnly = true)
    public Curriculo buscarPorAluno(Long alunoId) {
        return curriculoRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Currículo não encontrado para aluno: " + alunoId));
    }

    @Transactional
    public Curriculo criar(Curriculo curriculo) {
        if (curriculo.getAluno() == null) {
            throw new BusinessException("Aluno é obrigatório");
        }
        if (curriculoRepository.existsByAlunoId(curriculo.getAluno().getId())) {
            throw new BusinessException("Aluno já possui currículo cadastrado");
        }
        return curriculoRepository.save(curriculo);
    }

    @Transactional
    public Curriculo atualizar(Long id, Curriculo dados) {
        Curriculo curriculo = buscarPorId(id);

        curriculo.setResumo(dados.getResumo());
        curriculo.setObjetivo(dados.getObjetivo());
        curriculo.setCurriculoPdf(dados.getCurriculoPdf());

        return curriculoRepository.save(curriculo);
    }

    @Transactional
    public void deletar(Long id) {
        Curriculo curriculo = buscarPorId(id);
        curriculoRepository.delete(curriculo);
    }
}
