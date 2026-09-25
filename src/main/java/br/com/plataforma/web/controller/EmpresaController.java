package br.com.plataforma.web.controller;

import br.com.plataforma.domain.entity.empresa.Empresa;
import br.com.plataforma.domain.entity.usuario.Usuario;
import br.com.plataforma.domain.repository.usuario.UsuarioRepository;
import br.com.plataforma.domain.service.empresa.EmpresaService;
import br.com.plataforma.domain.service.processo.ProcessoSeletivoService;
import br.com.plataforma.domain.service.vaga.VagaService;
import br.com.plataforma.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;
    private final VagaService vagaService;
    private final ProcessoSeletivoService processoService;
    private final UsuarioRepository usuarioRepository;

    private Empresa getEmpresaLogada(Authentication auth) {
        String email = auth.getName();
        Usuario u = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + email));
        return empresaService.buscarPorUsuarioId(u.getId());
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        Empresa empresa = getEmpresaLogada(auth);
        model.addAttribute("empresa", empresa);
        return "empresa/dashboard";
    }

    @GetMapping("/perfil")
    public String perfil(Authentication auth, Model model) {
        Empresa empresa = getEmpresaLogada(auth);
        model.addAttribute("empresa", empresa);
        return "empresa/perfil";
    }

    @PostMapping("/perfil")
    public String salvarPerfil(@ModelAttribute Empresa form,
                               Authentication auth,
                               RedirectAttributes redirect) {
        Empresa atual = getEmpresaLogada(auth);
        empresaService.atualizar(atual.getId(), form);
        redirect.addFlashAttribute("mensagem", "Perfil atualizado!");
        return "redirect:/empresa/perfil";
    }

    @GetMapping("/vagas")        public String vagas()        { return "empresa/vagas/lista"; }
    @GetMapping("/vagas/nova")   public String novaVaga()     { return "empresa/vagas/form"; }
    @GetMapping("/vagas/{id}/candidatos")
    public String candidatos(@PathVariable Long id, Model model) {
        model.addAttribute("vagaId", id);
        return "empresa/vagas/candidatos";
    }
    @GetMapping("/processos")    public String processos()    { return "empresa/processo-seletivo/lista"; }
    @GetMapping("/convites")     public String convites()     { return "empresa/convites/lista"; }
}
