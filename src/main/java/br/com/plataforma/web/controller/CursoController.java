package br.com.plataforma.web.controller;

import br.com.plataforma.domain.entity.instituicao.Campus;
import br.com.plataforma.domain.entity.instituicao.Curso;
import br.com.plataforma.domain.service.instituicao.CampusService;
import br.com.plataforma.domain.service.instituicao.CursoService;
import br.com.plataforma.shared.enumeration.TipoAluno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;
    private final CampusService campusService;

    /**
     * Página que lista todos os cursos, com filtro opcional por tipo de aluno.
     * Ex: /cursos?tipo=SUPERIOR
     */
    @GetMapping
    public String listar(@RequestParam(required = false) TipoAluno tipo, Model model) {
        List<Curso> cursos = (tipo != null)
                ? cursoService.listarPorTipoAluno(tipo)
                : cursoService.listarTodos();

        model.addAttribute("cursos", cursos);
        model.addAttribute("tipoSelecionado", tipo);
        return "curso/lista";
    }

    /**
     * Retorna os campus que oferecem um curso — usado via AJAX (fetch)
     * no formulário de cadastro do aluno para popular o select de campus.
     */
    @GetMapping("/{cursoId}/campus")
    @ResponseBody
    public List<Campus> campusDoCurso(@PathVariable Long cursoId) {
        return campusService.listarPorCurso(cursoId);
    }

    /**
     * Retorna cursos por tipo de aluno — usado via AJAX no cadastro.
     * Ex: /cursos/api?tipo=TECNICO
     */
    @GetMapping("/api")
    @ResponseBody
    public List<Curso> cursosPorTipo(@RequestParam TipoAluno tipo) {
        return cursoService.listarPorTipoAluno(tipo);
    }
}
