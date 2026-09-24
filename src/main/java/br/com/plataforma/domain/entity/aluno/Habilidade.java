package br.com.plataforma.domain.entity.aluno;

import br.com.plataforma.shared.enumeration.NivelHabilidade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habilidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curriculo_id", nullable = false)
    private Curriculo curriculo;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private NivelHabilidade nivel;
}
