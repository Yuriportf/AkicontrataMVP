package br.com.plataforma.web.controller;

import br.com.plataforma.domain.entity.aluno.Aluno;
import br.com.plataforma.domain.service.aluno.AlunoService;
import br.com.plataforma.domain.service.aluno.CurriculoService;
import br.com.plataforma.domain.service.aluno.PortfolioService;
import br.com.plataforma.domain.service.instituicao.CampusService;
import br.com.plataforma.domain.service.instituicao.CursoService;
import br.com.plataforma.shared.enumeration.TipoAluno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;
    private final CurriculoService curriculoService;
    private final PortfolioService portfolioService;
    private final CursoService cursoService;
    private final CampusService campusService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // TODO: pegar aluno do usuário logado
        model.addAttribute("titulo", "Dashboard do Aluno");
        return "aluno/dashboard";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        // TODO: pegar aluno do usuário logado
        model.addAttribute("titulo", "Meu Perfil");
        return "aluno/perfil";
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        model.addAttribute("tipos", TipoAluno.values());
        return "aluno/cursos";
    }

    @GetMapping("/campus/{cursoId}")
    public String listarCampusPorCurso(@PathVariable Long cursoId, Model model) {
        model.addAttribute("campi", campusService.listarPorCurso(cursoId));
        return "aluno/campus";
    }

    @GetMapping("/vagas")
    public String listarVagas(Model model) {
        model.addAttribute("titulo", "Vagas disponíveis");
        return "aluno/vagas";
    }

    @GetMapping("/candidaturas")
    public String listarCandidaturas(Model model) {
        model.addAttribute("titulo", "Minhas candidaturas");
        return "aluno/candidaturas";
    }

    @GetMapping("/convites")
    public String listarConvites(Model model) {
        model.addAttribute("titulo", "Convites de entrevista");
        return "aluno/convites";
    }

    @GetMapping("/curriculo")
    public String verCurriculo(Model model) {
        model.addAttribute("titulo", "Meu currículo");
        return "aluno/curriculo";
    }

    @GetMapping("/portfolio")
    public String verPortfolio(Model model) {
        model.addAttribute("titulo", "Meu portfólio");
        return "aluno/portfolio";
    }
}
