package br.com.plataforma.domain.service.processo;

import br.com.plataforma.domain.entity.processo.ProcessoSeletivo;
import br.com.plataforma.domain.repository.processo.ProcessoSeletivoRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessoSeletivoService {

    private final ProcessoSeletivoRepository processoRepository;

    @Transactional(readOnly = true)
    public ProcessoSeletivo buscarPorId(Long id) {
        return processoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Processo seletivo", id));
    }

    @Transactional(readOnly = true)
    public List<ProcessoSeletivo> listarTodos() {
        return processoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProcessoSeletivo> listarPorEmpresa(Long empresaId) {
        return processoRepository.findByEmpresaId(empresaId);
    }

    @Transactional(readOnly = true)
    public List<ProcessoSeletivo> listarAtivos() {
        return processoRepository.findByAtivoTrue();
    }

    @Transactional
    public ProcessoSeletivo criar(ProcessoSeletivo processo) {
        validar(processo);
        if (processo.getAtivo() == null) {
            processo.setAtivo(true);
        }
        return processoRepository.save(processo);
    }

    @Transactional
    public ProcessoSeletivo atualizar(Long id, ProcessoSeletivo dados) {
        ProcessoSeletivo processo = buscarPorId(id);
        validar(dados);

        processo.setNome(dados.getNome());
        processo.setDescricao(dados.getDescricao());
        processo.setAtivo(dados.getAtivo());
        processo.setVaga(dados.getVaga());

        return processoRepository.save(processo);
    }

    @Transactional
    public void encerrar(Long id) {
        ProcessoSeletivo processo = buscarPorId(id);
        processo.setAtivo(false);
        processoRepository.save(processo);
    }

    @Transactional
    public void deletar(Long id) {
        ProcessoSeletivo processo = buscarPorId(id);
        processoRepository.delete(processo);
    }

    private void validar(ProcessoSeletivo processo) {
        if (processo.getNome() == null || processo.getNome().isBlank()) {
            throw new BusinessException("Nome do processo seletivo é obrigatório");
        }
        if (processo.getEmpresa() == null) {
            throw new BusinessException("Empresa é obrigatória");
        }
    }
}
