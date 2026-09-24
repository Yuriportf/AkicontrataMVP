package br.com.plataforma.domain.entity.processo;

import br.com.plataforma.domain.entity.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resposta_questionario",
       uniqueConstraints = @UniqueConstraint(columnNames = {"questionario_id", "aluno_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaQuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionario_id", nullable = false)
    private Questionario questionario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(name = "iniciado_em", nullable = false)
    private LocalDateTime iniciadoEm;

    @Column(name = "finalizado_em")
    private LocalDateTime finalizadoEm;

    @OneToMany(mappedBy = "respostaQuestionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RespostaQuestao> respostas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.iniciadoEm == null) {
            this.iniciadoEm = LocalDateTime.now();
        }
    }
}
