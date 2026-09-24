package br.com.plataforma.domain.entity.aluno;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curriculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private Aluno aluno;

    @Column(columnDefinition = "TEXT")
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    @Column(name = "curriculo_pdf", length = 255)
    private String curriculoPdf;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ExperienciaProfissional> experiencias = new ArrayList<>();

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<FormacaoAcademica> formacoes = new ArrayList<>();

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Habilidade> habilidades = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void atualizarData() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
