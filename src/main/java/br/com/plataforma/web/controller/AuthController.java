package br.com.plataforma.web.controller;

import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.service.usuario.AuthService;
import br.com.plataforma.shared.enumeration.TipoUsuario;
import br.com.plataforma.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/cadastro")
    public String escolherTipo() {
        return "auth/escolher-tipo";
    }

    @GetMapping("/cadastro/aluno")
    public String formAluno(Model model) {
        model.addAttribute("tipo", "ALUNO");
        return "auth/cadastro-aluno";
    }

    @GetMapping("/cadastro/empresa")
    public String formEmpresa(Model model) {
        model.addAttribute("tipo", "EMPRESA");
        return "auth/cadastro-empresa";
    }

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
