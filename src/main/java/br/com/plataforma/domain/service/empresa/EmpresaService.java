package br.com.plataforma.domain.service.empresa;

import br.com.plataforma.domain.entity.empresa.Empresa;
import br.com.plataforma.domain.entity.empresa.ResponsavelEmpresa;
import br.com.plataforma.domain.entity.usuario.Role;
import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.repository.empresa.EmpresaRepository;
import br.com.plataforma.domain.repository.usuario.RoleRepository;
import br.com.plataforma.domain.repository.usuario.UsuarioRepository;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import br.com.plataforma.web.dto.request.CadastroEmpresaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Transactional
    public Empresa cadastrarCompleto(CadastroEmpresaRequest req) {
        // 1. Validações
        if (usuarioRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException("Já existe usuário com o email: " + req.getEmail());
        }
        if (empresaRepository.existsByCnpj(req.getCnpj())) {
            throw new BusinessException("Já existe empresa com o CNPJ: " + req.getCnpj());
        }

        // 2. Roles
        Set<Role> roles = new HashSet<>();
        Optional<Role> roleOpt = roleRepository.findByNome("ROLE_EMPRESA");
        roleOpt.ifPresent(roles::add);

        // 3. Usuário
        Usuario usuario = Usuario.builder()
                .email(req.getEmail())
                .senha(passwordEncoder.encode(req.getSenha()))
                .tipoUsuario(TipoUsuario.EMPRESA)
                .ativo(true)
                .roles(roles)
                .build();
        usuario = usuarioRepository.save(usuario);

        // 4. Empresa
        Empresa empresa = Empresa.builder()
                .usuario(usuario)
                .razaoSocial(req.getRazaoSocial())
                .nomeFantasia(req.getNomeFantasia())
                .cnpj(req.getCnpj())
                .descricao(req.getDescricao())
                .site(req.getSite())
                .logoUrl(req.getLogoUrl())
                .cidade(req.getCidade())
                .uf(req.getUf())
                .build();
        empresa = empresaRepository.save(empresa);

        // 5. Responsável (opcional)
        if (req.getResponsavelNome() != null && !req.getResponsavelNome().isBlank()) {
            ResponsavelEmpresa resp = ResponsavelEmpresa.builder()
                    .empresa(empresa)
                    .nome(req.getResponsavelNome())
                    .cargo(req.getResponsavelCargo())
                    .email(req.getResponsavelEmail())
                    .telefone(req.getResponsavelTelefone())
                    .build();
            empresa.getResponsaveis().add(resp);
            empresa = empresaRepository.save(empresa);
        }

        return empresa;
    }

    @Transactional
    public Empresa atualizar(Long id, Empresa dados) {
        Empresa empresa = buscarPorId(id);

        if (dados.getRazaoSocial() != null) empresa.setRazaoSocial(dados.getRazaoSocial());
        if (dados.getNomeFantasia() != null) empresa.setNomeFantasia(dados.getNomeFantasia());
        if (dados.getDescricao() != null) empresa.setDescricao(dados.getDescricao());
        if (dados.getSite() != null) empresa.setSite(dados.getSite());
        if (dados.getLogoUrl() != null) empresa.setLogoUrl(dados.getLogoUrl());
        if (dados.getCidade() != null) empresa.setCidade(dados.getCidade());
        if (dados.getUf() != null) empresa.setUf(dados.getUf());

        return empresaRepository.save(empresa);
    }

    @Transactional
    public void deletar(Long id) {
        Empresa empresa = buscarPorId(id);
        empresaRepository.delete(empresa);
    }
}
