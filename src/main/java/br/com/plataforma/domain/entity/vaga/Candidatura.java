package br.com.plataforma.domain.entity.vaga;

import br.com.plataforma.shared.enumeration.StatusCandidatura;
import br.com.plataforma.domain.entity.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidatura",
       uniqueConstraints = @UniqueConstraint(columnNames = {"vaga_id", "aluno_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusCandidatura status = StatusCandidatura.PENDENTE;

    @Column(name = "data_candidatura", nullable = false)
    private LocalDateTime dataCandidatura;

    @PrePersist
    public void prePersist() {
        if (this.dataCandidatura == null) {
            this.dataCandidatura = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusCandidatura.PENDENTE;
        }
    }
}
