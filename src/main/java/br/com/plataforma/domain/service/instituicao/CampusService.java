package br.com.plataforma.domain.service.instituicao;

import br.com.plataforma.domain.entity.instituicao.Campus;
import br.com.plataforma.domain.repository.instituicao.CampusRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository campusRepository;

    @Transactional(readOnly = true)
    public Campus buscarPorId(Long id) {
        return campusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campus", id));
    }

    @Transactional(readOnly = true)
    public List<Campus> listarTodos() {
        return campusRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Campus> listarPorInstituicao(Long instituicaoId) {
        return campusRepository.findByInstituicaoId(instituicaoId);
    }

    /**
     * Lista os campus que oferecem um curso específico.
     * Usado no cadastro do aluno: depois que ele escolhe o curso,
     * o select de campus é populado com essa lista.
     */
    @Transactional(readOnly = true)
    public List<Campus> listarPorCurso(Long cursoId) {
        return campusRepository.findByCursoId(cursoId);
    }

    @Transactional
    public Campus criar(Campus campus) {
        validar(campus);
        return campusRepository.save(campus);
    }

    @Transactional
    public Campus atualizar(Long id, Campus dados) {
        Campus campus = buscarPorId(id);
        validar(dados);

        campus.setNome(dados.getNome());
        campus.setCidade(dados.getCidade());
        campus.setUf(dados.getUf());
        campus.setTipo(dados.getTipo());
        campus.setInstituicao(dados.getInstituicao());

        return campusRepository.save(campus);
    }

    @Transactional
    public void deletar(Long id) {
        Campus campus = buscarPorId(id);
        campusRepository.delete(campus);
    }

    private void validar(Campus campus) {
        if (campus.getNome() == null || campus.getNome().isBlank()) {
            throw new BusinessException("Nome do campus é obrigatório");
        }
        if (campus.getTipo() == null || campus.getTipo().isBlank()) {
            throw new BusinessException("Tipo do campus é obrigatório");
        }
    }
}
