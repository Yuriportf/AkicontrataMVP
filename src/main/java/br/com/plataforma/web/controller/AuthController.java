package br.com.plataforma.web.controller;

import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.service.aluno.AlunoService;
import br.com.plataforma.domain.service.instituicao.CampusService;
import br.com.plataforma.domain.service.instituicao.CursoService;
import br.com.plataforma.domain.service.usuario.AuthService;
import br.com.plataforma.shared.enumeration.NivelCurso;
import br.com.plataforma.shared.enumeration.Periodo;
import br.com.plataforma.shared.enumeration.TipoAluno;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import br.com.plataforma.shared.exception.BusinessException;
import br.com.plataforma.web.dto.request.CadastroAlunoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AlunoService alunoService;
    private final CursoService cursoService;
    private final CampusService campusService;

    @GetMapping("/cadastro")
    public String escolherTipo() {
        return "auth/escolher-tipo";
    }

    @GetMapping("/cadastro/aluno")
    public String formAluno(Model model) {
        model.addAttribute("tiposAluno", TipoAluno.values());
        model.addAttribute("periodos", Periodo.values());
        model.addAttribute("niveis", NivelCurso.values());
        return "auth/cadastro-aluno";
    }

    @GetMapping("/cadastro/empresa")
    public String formEmpresa(Model model) {
        return "auth/cadastro-empresa";
    }

    /**
     * Cadastro de ALUNO (completo).
     */
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

    /**
     * Cadastro de EMPRESA (básico).
     */
    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String email,
                            @RequestParam String senha,
                            @RequestParam TipoUsuario tipoUsuario,
                            RedirectAttributes redirect) {
        try {
            Usuario usuario = authService.cadastrar(email, senha, tipoUsuario);
            redirect.addFlashAttribute("mensagem", "Cadastro realizado! Faça login para continuar.");
            return "redirect:/login";
        } catch (BusinessException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/auth/cadastro";
        }
    }
}
