package br.com.plataforma.domain.entity.aluno;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aluno_pos_graduacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlunoPosGraduacao {

    @Id
    @Column(name = "aluno_id")
    @EqualsAndHashCode.Include
    private Long alunoId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "linha_pesquisa", length = 255)
    private String linhaPesquisa;

    @Column(length = 180)
    private String orientador;
}
