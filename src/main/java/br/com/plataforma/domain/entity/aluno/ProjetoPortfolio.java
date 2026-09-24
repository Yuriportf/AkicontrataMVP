package br.com.plataforma.domain.entity.aluno;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projeto_portfolio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProjetoPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false, length = 180)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 255)
    private String link;

    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;
}
