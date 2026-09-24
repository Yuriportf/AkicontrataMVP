package br.com.plataforma.domain.service.vaga;

import br.com.plataforma.domain.entity.vaga.Vaga;
import br.com.plataforma.domain.repository.vaga.VagaRepository;
import br.com.plataforma.shared.enumeration.StatusVaga;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    @Transactional(readOnly = true)
    public Vaga buscarPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga", id));
    }

    @Transactional(readOnly = true)
    public List<Vaga> listarTodas() {
        return vagaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Vaga> listarAbertas() {
        return vagaRepository.findByStatusOrderByDataPublicacaoDesc(StatusVaga.ABERTA);
    }

    @Transactional(readOnly = true)
    public List<Vaga> listarPorEmpresa(Long empresaId) {
        return vagaRepository.findByEmpresaId(empresaId);
    }

    @Transactional(readOnly = true)
    public List<Vaga> listarPorEmpresaEStatus(Long empresaId, StatusVaga status) {
        return vagaRepository.findByEmpresaIdAndStatus(empresaId, status);
    }

    @Transactional
    public Vaga criar(Vaga vaga) {
        validar(vaga);
        if (vaga.getStatus() == null) {
            vaga.setStatus(StatusVaga.ABERTA);
        }
        return vagaRepository.save(vaga);
    }

    @Transactional
    public Vaga atualizar(Long id, Vaga dados) {
        Vaga vaga = buscarPorId(id);
        validar(dados);

        vaga.setTitulo(dados.getTitulo());
        vaga.setDescricao(dados.getDescricao());
        vaga.setModalidade(dados.getModalidade());
        vaga.setTipo(dados.getTipo());
        vaga.setCargaHoraria(dados.getCargaHoraria());
        vaga.setBolsa(dados.getBolsa());
        vaga.setCidade(dados.getCidade());
        vaga.setUf(dados.getUf());
        vaga.setStatus(dados.getStatus());
        vaga.setDataEncerramento(dados.getDataEncerramento());

        return vagaRepository.save(vaga);
    }

    @Transactional
    public void fechar(Long id) {
        Vaga vaga = buscarPorId(id);
        vaga.setStatus(StatusVaga.FECHADA);
        vagaRepository.save(vaga);
    }

    @Transactional
    public void deletar(Long id) {
        Vaga vaga = buscarPorId(id);
        vagaRepository.delete(vaga);
    }

    private void validar(Vaga vaga) {
        if (vaga.getTitulo() == null || vaga.getTitulo().isBlank()) {
            throw new BusinessException("Título da vaga é obrigatório");
        }
        if (vaga.getEmpresa() == null) {
            throw new BusinessException("Empresa é obrigatória");
        }
    }
}
