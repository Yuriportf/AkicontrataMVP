package br.com.plataforma.domain.service.aluno;

import br.com.plataforma.domain.entity.aluno.Portfolio;
import br.com.plataforma.domain.repository.aluno.PortfolioRepository;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Transactional(readOnly = true)
    public Portfolio buscarPorId(Long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfólio", id));
    }

    @Transactional(readOnly = true)
    public Portfolio buscarPorAluno(Long alunoId) {
        return portfolioRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfólio não encontrado para aluno: " + alunoId));
    }

    @Transactional
    public Portfolio criar(Portfolio portfolio) {
        if (portfolio.getAluno() == null) {
            throw new BusinessException("Aluno é obrigatório");
        }
        if (portfolioRepository.existsByAlunoId(portfolio.getAluno().getId())) {
            throw new BusinessException("Aluno já possui portfólio cadastrado");
        }
        return portfolioRepository.save(portfolio);
    }

    @Transactional
    public Portfolio atualizar(Long id, Portfolio dados) {
        Portfolio portfolio = buscarPorId(id);

        portfolio.setLinkedinUrl(dados.getLinkedinUrl());
        portfolio.setGithubUrl(dados.getGithubUrl());
        portfolio.setBehanceUrl(dados.getBehanceUrl());
        portfolio.setSitePessoal(dados.getSitePessoal());
        portfolio.setDescricao(dados.getDescricao());

        return portfolioRepository.save(portfolio);
    }

    @Transactional
    public void deletar(Long id) {
        Portfolio portfolio = buscarPorId(id);
        portfolioRepository.delete(portfolio);
    }
}
