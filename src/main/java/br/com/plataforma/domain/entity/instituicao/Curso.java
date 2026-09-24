package br.com.plataforma.domain.entity.instituicao;

import br.com.plataforma.shared.enumeration.NivelCurso;
import br.com.plataforma.shared.enumeration.TipoAluno;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 180)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NivelCurso nivel;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aluno", nullable = false, length = 20)
    private TipoAluno tipoAluno;

    @Column(length = 100)
    private String area;

    @Column(name = "duracao_semestres")
    private Integer duracaoSemestres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private InstituicaoEnsino instituicao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "curso_campus",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "campus_id")
    )
    @Builder.Default
    private Set<Campus> campi = new HashSet<>();
}
