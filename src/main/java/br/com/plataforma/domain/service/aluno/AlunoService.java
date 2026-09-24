package br.com.plataforma.domain.service.aluno;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.domain.repository.aluno.AlunoRepository;
import br.com.plataforma.shared.enumeration.TipoAluno;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno", id));
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorUsuarioId(Long usuarioId) {
        return alunoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado para usuário: " + usuarioId));
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com matrícula: " + matricula));
    }

    @Transactional(readOnly = true)
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Aluno> listarPorTipo(TipoAluno tipo) {
        return alunoRepository.findByTipoAluno(tipo);
    }

    @Transactional(readOnly = true)
    public List<Aluno> listarPorCurso(Long cursoId) {
        return alunoRepository.findByCursoId(cursoId);
    }

    @Transactional(readOnly = true)
    public List<Aluno> listarPorCampus(Long campusId) {
        return alunoRepository.findByCampusId(campusId);
    }

    @Transactional
    public Aluno criar(Aluno aluno) {
        validar(aluno);

        if (aluno.getMatricula() != null && aluno.getCurso() != null
                && alunoRepository.existsByMatriculaAndCursoId(aluno.getMatricula(), aluno.getCurso().getId())) {
            throw new BusinessException("Já existe aluno com essa matrícula nesse curso");
        }

        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long id, Aluno dados) {
        Aluno aluno = buscarPorId(id);
        validar(dados);

        aluno.setNomeCompleto(dados.getNomeCompleto());
        aluno.setCpf(dados.getCpf());
        aluno.setDataNascimento(dados.getDataNascimento());
        aluno.setTelefone(dados.getTelefone());
        aluno.setFotoUrl(dados.getFotoUrl());
        aluno.setTipoAluno(dados.getTipoAluno());
        aluno.setCurso(dados.getCurso());
        aluno.setCampus(dados.getCampus());
        aluno.setPeriodo(dados.getPeriodo());
        aluno.setDataInicio(dados.getDataInicio());
        aluno.setDataPrevisaoFim(dados.getDataPrevisaoFim());

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }

    private void validar(Aluno aluno) {
        if (aluno.getNomeCompleto() == null || aluno.getNomeCompleto().isBlank()) {
            throw new BusinessException("Nome completo é obrigatório");
        }
        if (aluno.getMatricula() == null || aluno.getMatricula().isBlank()) {
            throw new BusinessException("Matrícula é obrigatória");
        }
        if (aluno.getCurso() == null) {
            throw new BusinessException("Curso é obrigatório");
        }
        if (aluno.getTipoAluno() == null) {
            throw new BusinessException("Tipo de aluno é obrigatório");
        }
    }
}
