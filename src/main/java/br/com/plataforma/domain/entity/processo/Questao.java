package br.com.plataforma.domain.entity.processo;

import br.com.plataforma.shared.enumeration.TipoQuestao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionario_id", nullable = false)
    private Questionario questionario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoQuestao tipo;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal peso = BigDecimal.ONE;

    @Column(name = "ordem")
    private Integer ordem;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alternativa> alternativas = new ArrayList<>();
}
