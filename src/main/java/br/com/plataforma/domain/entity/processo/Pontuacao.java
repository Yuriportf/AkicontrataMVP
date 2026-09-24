package br.com.plataforma.domain.entity.processo;

import br.com.plataforma.domain.entity.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pontuacao",
       uniqueConstraints = @UniqueConstraint(columnNames = {"processo_seletivo_id", "aluno_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "processo_seletivo_id", nullable = false)
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(name = "pontuacao_total", nullable = false, precision = 8, scale = 2)
    private BigDecimal pontuacaoTotal = BigDecimal.ZERO;

    @Column(precision = 5, scale = 2)
    private BigDecimal percentual;

    @Column(name = "calculado_em", nullable = false)
    private LocalDateTime calculadoEm;

    @PrePersist
    public void prePersist() {
        this.calculadoEm = LocalDateTime.now();
    }
}
