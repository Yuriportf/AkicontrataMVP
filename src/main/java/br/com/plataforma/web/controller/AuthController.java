package br.com.plataforma.web.controller;

import br.com.plataforma.domain.service.aluno.AlunoService;
import br.com.plataforma.domain.service.empresa.EmpresaService;
import br.com.plataforma.shared.enumeration.NivelCurso;
import br.com.plataforma.shared.enumeration.Periodo;
import br.com.plataforma.shared.enumeration.TipoAluno;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.web.dto.request.CadastroAlunoRequest;
import br.com.plataforma.web.dto.request.CadastroEmpresaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AlunoService alunoService;
    private final EmpresaService empresaService;

    @GetMapping("/cadastro")
    public String escolherTipo() {
        return "auth/escolher-tipo";
    }

    // ============================================================
    // ALUNO
    // ============================================================
    @GetMapping("/cadastro/aluno")
    public String formAluno(Model model) {
        model.addAttribute("tiposAluno", TipoAluno.values());
        model.addAttribute("periodos", Periodo.values());
        model.addAttribute("niveis", NivelCurso.values());
        return "auth/cadastro-aluno";
    }

    @PostMapping("/cadastro/aluno")
    public String cadastrarAluno(@ModelAttribute CadastroAlunoRequest req,
                                 RedirectAttributes redirect) {
        try {
            alunoService.cadastrarCompleto(req);
            redirect.addFlashAttribute("mensagem", "Cadastro realizado! Faça login para continuar.");
            return "redirect:/login";
        } catch (BusinessException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/auth/cadastro/aluno";
        } catch (Exception e) {
            redirect.addFlashAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/auth/cadastro/aluno";
        }
    }

    // ============================================================
    // EMPRESA
    // ============================================================
    @GetMapping("/cadastro/empresa")
    public String formEmpresa() {
        return "auth/cadastro-empresa";
    }

    @PostMapping("/cadastro/empresa")
    public String cadastrarEmpresa(@ModelAttribute CadastroEmpresaRequest req,
                                   RedirectAttributes redirect) {
        try {
            empresaService.cadastrarCompleto(req);
            redirect.addFlashAttribute("mensagem", "Empresa cadastrada! Faça login para continuar.");
            return "redirect:/login";
        } catch (BusinessException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/auth/cadastro/empresa";
        } catch (Exception e) {
            redirect.addFlashAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/auth/cadastro/empresa";
        }
    }
}
