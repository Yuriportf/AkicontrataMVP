package br.com.plataforma.domain.entity.processo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Questionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "processo_seletivo_id", nullable = false)
    private ProcessoSeletivo processoSeletivo;

    @Column(nullable = false, length = 180)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "pontuacao_minima", precision = 6, scale = 2)
    private BigDecimal pontuacaoMinima = BigDecimal.ZERO;

    @OneToMany(mappedBy = "questionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Questao> questoes = new ArrayList<>();
}
