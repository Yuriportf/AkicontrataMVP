package br.com.plataforma.domain.service.instituicao;

import br.com.plataforma.domain.entity.instituicao.Curso;
import br.com.plataforma.domain.repository.instituicao.CursoRepository;
import br.com.plataforma.shared.enumeration.NivelCurso;
import br.com.plataforma.shared.enumeration.TipoAluno;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", id));
    }

    @Transactional(readOnly = true)
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    /**
     * Lista cursos pelo tipo do aluno: TECNICO, SUPERIOR ou POS_GRADUACAO.
     * Usado no cadastro do aluno para popular o select de cursos.
     */
    @Transactional(readOnly = true)
    public List<Curso> listarPorTipoAluno(TipoAluno tipoAluno) {
        return cursoRepository.findByTipoAluno(tipoAluno);
    }

    @Transactional(readOnly = true)
    public List<Curso> listarPorNivel(NivelCurso nivel) {
        return cursoRepository.findByNivel(nivel);
    }

    @Transactional(readOnly = true)
    public List<Curso> listarPorTipoENivel(TipoAluno tipoAluno, NivelCurso nivel) {
        return cursoRepository.findByTipoAlunoAndNivel(tipoAluno, nivel);
    }

    @Transactional(readOnly = true)
    public List<Curso> listarPorCampus(Long campusId) {
        return cursoRepository.findByCampusId(campusId);
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarPorNome(String nome) {
        return cursoRepository.findByNomeContainingIgnoreCaseOrderByNome(nome);
    }

    @Transactional
    public Curso criar(Curso curso) {
        validar(curso);
        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso atualizar(Long id, Curso dados) {
        Curso curso = buscarPorId(id);
        validar(dados);

        curso.setNome(dados.getNome());
        curso.setNivel(dados.getNivel());
        curso.setTipoAluno(dados.getTipoAluno());
        curso.setArea(dados.getArea());
        curso.setDuracaoSemestres(dados.getDuracaoSemestres());
        curso.setInstituicao(dados.getInstituicao());
        curso.setCampi(dados.getCampi());

        return cursoRepository.save(curso);
    }

    @Transactional
    public void deletar(Long id) {
        Curso curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }

    private void validar(Curso curso) {
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new BusinessException("Nome do curso é obrigatório");
        }
        if (curso.getNivel() == null) {
            throw new BusinessException("Nível do curso é obrigatório");
        }
        if (curso.getTipoAluno() == null) {
            throw new BusinessException("Tipo de aluno do curso é obrigatório");
        }
    }
}
