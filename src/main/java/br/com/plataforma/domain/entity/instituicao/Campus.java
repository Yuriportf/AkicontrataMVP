package br.com.plataforma.domain.entity.instituicao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "campus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String nome;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(nullable = false, length = 20)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private InstituicaoEnsino instituicao;
}
