package br.com.plataforma.domain.entity.empresa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "responsavel_empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ResponsavelEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 180)
    private String nome;

    @Column(length = 100)
    private String cargo;

    @Column(length = 180)
    private String email;

    @Column(length = 20)
    private String telefone;
}
