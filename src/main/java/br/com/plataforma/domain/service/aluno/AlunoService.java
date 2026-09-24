package br.com.plataforma.domain.service.aluno;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.domain.entity.instituicao.Campus;
import br.com.plataforma.domain.entity.instituicao.Curso;
import br.com.plataforma.domain.entity.usuario.Role;
import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.repository.aluno.AlunoRepository;
import br.com.plataforma.domain.repository.instituicao.CampusRepository;
import br.com.plataforma.domain.repository.instituicao.CursoRepository;
import br.com.plataforma.domain.repository.usuario.RoleRepository;
import br.com.plataforma.domain.repository.usuario.UsuarioRepository;
import br.com.plataforma.shared.enumeration.TipoAluno;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import br.com.plataforma.web.dto.request.CadastroAlunoRequest;
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
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final CursoRepository cursoRepository;
    private final CampusRepository campusRepository;
    private final PasswordEncoder passwordEncoder;

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
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @Transactional
    public Aluno cadastrarCompleto(CadastroAlunoRequest req) {
        // 1. Email único
        if (usuarioRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException("Já existe usuário com o email: " + req.getEmail());
        }

        // 2. Curso e campus
        Curso curso = cursoRepository.findById(req.getCursoId())
                .orElseThrow(() -> new BusinessException("Curso não encontrado"));

        Campus campus = campusRepository.findById(req.getCampusId())
                .orElseThrow(() -> new BusinessException("Campus não encontrado"));

        // 3. Matrícula única no curso
        if (alunoRepository.existsByMatriculaAndCursoId(req.getMatricula(), req.getCursoId())) {
            throw new BusinessException("Já existe aluno com essa matrícula nesse curso");
        }

        // 4. Roles (fora do lambda)
        Set<Role> roles = new HashSet<>();
        Optional<Role> roleOpt = roleRepository.findByNome("ROLE_ALUNO");
        roleOpt.ifPresent(roles::add);

        // 5. Usuário
        Usuario usuario = Usuario.builder()
                .email(req.getEmail())
                .senha(passwordEncoder.encode(req.getSenha()))
                .tipoUsuario(TipoUsuario.ALUNO)
                .ativo(true)
                .roles(roles)
                .build();

        usuario = usuarioRepository.save(usuario);

        // 6. Aluno
        TipoAluno tipoAluno = TipoAluno.valueOf(req.getTipoAluno());

        Aluno aluno = Aluno.builder()
                .usuario(usuario)
                .nomeCompleto(req.getNomeCompleto())
                .cpf(req.getCpf())
                .dataNascimento(req.getDataNascimento())
                .telefone(req.getTelefone())
                .tipoAluno(tipoAluno)
                .curso(curso)
                .campus(campus)
                .matricula(req.getMatricula())
                .periodo(req.getPeriodo())
                .dataInicio(req.getDataInicio())
                .dataPrevisaoFim(req.getDataPrevisaoFim())
                .build();

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}
