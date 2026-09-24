package br.com.plataforma.domain.entity.instituicao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instituicao_ensino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InstituicaoEnsino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 180)
    private String nome;

    @Column(length = 20)
    private String sigla;

    @Column(length = 18)
    private String cnpj;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;
}
