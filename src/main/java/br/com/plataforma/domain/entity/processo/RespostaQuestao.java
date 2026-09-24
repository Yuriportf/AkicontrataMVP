package br.com.plataforma.domain.entity.processo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "resposta_questao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resposta_questionario_id", nullable = false)
    private RespostaQuestionario respostaQuestionario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questao_id", nullable = false)
    private Questao questao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alternativa_id")
    private Alternativa alternativa;

    @Column(name = "texto_resposta", columnDefinition = "TEXT")
    private String textoResposta;

    @Column(name = "acertou")
    private Boolean acertou;

    @Column(name = "pontos_obtidos", precision = 6, scale = 2)
    private BigDecimal pontosObtidos = BigDecimal.ZERO;
}
