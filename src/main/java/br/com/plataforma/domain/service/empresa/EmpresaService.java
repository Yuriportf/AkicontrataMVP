package br.com.plataforma.domain.service.empresa;

import br.com.plataforma.domain.entity.empresa.Empresa;
import br.com.plataforma.domain.repository.empresa.EmpresaRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", id));
    }

    @Transactional(readOnly = true)
    public Empresa buscarPorUsuarioId(Long usuarioId) {
        return empresaRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada para usuário: " + usuarioId));
    }

    @Transactional(readOnly = true)
    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Empresa> buscarPorNome(String nome) {
        return empresaRepository.findByNomeFantasiaContainingIgnoreCase(nome);
    }

    @Transactional
    public Empresa criar(Empresa empresa) {
        validar(empresa);

        if (empresa.getCnpj() != null && empresaRepository.existsByCnpj(empresa.getCnpj())) {
            throw new BusinessException("Já existe empresa com o CNPJ: " + empresa.getCnpj());
        }

        return empresaRepository.save(empresa);
    }

    @Transactional
    public Empresa atualizar(Long id, Empresa dados) {
        Empresa empresa = buscarPorId(id);
        validar(dados);

        if (dados.getCnpj() != null && !dados.getCnpj().equals(empresa.getCnpj())
                && empresaRepository.existsByCnpj(dados.getCnpj())) {
            throw new BusinessException("CNPJ já em uso: " + dados.getCnpj());
        }

        empresa.setRazaoSocial(dados.getRazaoSocial());
        empresa.setNomeFantasia(dados.getNomeFantasia());
        empresa.setCnpj(dados.getCnpj());
        empresa.setDescricao(dados.getDescricao());
        empresa.setSite(dados.getSite());
        empresa.setLogoUrl(dados.getLogoUrl());
        empresa.setCidade(dados.getCidade());
        empresa.setUf(dados.getUf());

        return empresaRepository.save(empresa);
    }

    @Transactional
    public void deletar(Long id) {
        Empresa empresa = buscarPorId(id);
        empresaRepository.delete(empresa);
    }

    private void validar(Empresa empresa) {
        if (empresa.getRazaoSocial() == null || empresa.getRazaoSocial().isBlank()) {
            throw new BusinessException("Razão social é obrigatória");
        }
    }
}
