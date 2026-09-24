package br.com.plataforma.domain.entity.aluno;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "aluno_superior")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlunoSuperior {

    @Id
    @Column(name = "aluno_id")
    @EqualsAndHashCode.Include
    private Long alunoId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "semestre_atual")
    private Integer semestreAtual;

    @Column(precision = 4, scale = 2)
    private BigDecimal ira;

    @Column(length = 20)
    private String turno;
}
