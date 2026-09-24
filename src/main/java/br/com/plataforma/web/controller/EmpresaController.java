package br.com.plataforma.web.controller;

import br.com.plataforma.domain.service.empresa.EmpresaService;
import br.com.plataforma.domain.service.processo.ProcessoSeletivoService;
import br.com.plataforma.domain.service.vaga.VagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;
    private final VagaService vagaService;
    private final ProcessoSeletivoService processoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("titulo", "Dashboard da Empresa");
        return "empresa/dashboard";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        model.addAttribute("titulo", "Perfil da Empresa");
        return "empresa/perfil";
    }

    @GetMapping("/vagas")
    public String listarVagas(Model model) {
        model.addAttribute("titulo", "Minhas vagas");
        return "empresa/vagas/lista";
    }

    @GetMapping("/vagas/nova")
    public String novaVaga(Model model) {
        model.addAttribute("titulo", "Publicar vaga");
        return "empresa/vagas/form";
    }

    @GetMapping("/vagas/{id}/candidatos")
    public String candidatosDaVaga(@PathVariable Long id, Model model) {
        model.addAttribute("vagaId", id);
        model.addAttribute("titulo", "Candidatos da vaga");
        return "empresa/vagas/candidatos";
    }

    @GetMapping("/processos")
    public String listarProcessos(Model model) {
        model.addAttribute("titulo", "Processos seletivos");
        return "empresa/processo-seletivo/lista";
    }

    @GetMapping("/processos/novo")
    public String novoProcesso(Model model) {
        model.addAttribute("titulo", "Novo processo seletivo");
        return "empresa/processo-seletivo/form";
    }

    @GetMapping("/convites")
    public String listarConvites(Model model) {
        model.addAttribute("titulo", "Convites enviados");
        return "empresa/convites/lista";
    }
}
