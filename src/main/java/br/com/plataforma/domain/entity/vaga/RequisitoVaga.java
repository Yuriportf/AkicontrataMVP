package br.com.plataforma.domain.entity.vaga;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "requisito_vaga")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisitoVaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @Column(nullable = false, length = 255)
    private String descricao;
}
