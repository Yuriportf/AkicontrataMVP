package br.com.plataforma.domain.service.convite;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.domain.entity.convite.ConviteEntrevista;
import br.com.plataforma.domain.entity.empresa.Empresa;
import br.com.plataforma.domain.entity.vaga.Vaga;
import br.com.plataforma.domain.repository.convite.ConviteEntrevistaRepository;
import br.com.plataforma.shared.enumeration.StatusConvite;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConviteService {

    private final ConviteEntrevistaRepository conviteRepository;

    @Transactional(readOnly = true)
    public ConviteEntrevista buscarPorId(Long id) {
        return conviteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convite", id));
    }

    @Transactional(readOnly = true)
    public List<ConviteEntrevista> listarPorAluno(Long alunoId) {
        return conviteRepository.findByAlunoId(alunoId);
    }

    @Transactional(readOnly = true)
    public List<ConviteEntrevista> listarPorEmpresa(Long empresaId) {
        return conviteRepository.findByEmpresaId(empresaId);
    }

    @Transactional
    public ConviteEntrevista convidar(Empresa empresa, Aluno aluno, Vaga vaga, String mensagem) {
        if (empresa == null || aluno == null) {
            throw new BusinessException("Empresa e aluno são obrigatórios");
        }

        ConviteEntrevista convite = ConviteEntrevista.builder()
                .empresa(empresa)
                .aluno(aluno)
                .vaga(vaga)
                .mensagem(mensagem)
                .status(StatusConvite.PENDENTE)
                .build();

        return conviteRepository.save(convite);
    }

    @Transactional
    public ConviteEntrevista aceitar(Long id) {
        ConviteEntrevista convite = buscarPorId(id);
        convite.setStatus(StatusConvite.ACEITO);
        return conviteRepository.save(convite);
    }

    @Transactional
    public ConviteEntrevista recusar(Long id) {
        ConviteEntrevista convite = buscarPorId(id);
        convite.setStatus(StatusConvite.RECUSADO);
        return conviteRepository.save(convite);
    }

    @Transactional
    public void deletar(Long id) {
        ConviteEntrevista convite = buscarPorId(id);
        conviteRepository.delete(convite);
    }
}
