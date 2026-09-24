package br.com.plataforma.domain.entity.aluno;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aluno_tecnico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlunoTecnico {

    @Id
    @Column(name = "aluno_id")
    @EqualsAndHashCode.Include
    private Long alunoId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "modulo_atual")
    private Integer moduloAtual;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Column(length = 20)
    private String turno;
}
