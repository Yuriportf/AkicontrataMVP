package br.com.plataforma.domain.entity.vaga;

import br.com.plataforma.shared.enumeration.ModalidadeVaga;
import br.com.plataforma.shared.enumeration.StatusVaga;
import br.com.plataforma.shared.enumeration.TipoVaga;
import br.com.plataforma.domain.entity.empresa.Empresa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaga")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 180)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ModalidadeVaga modalidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoVaga tipo;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Column(precision = 10, scale = 2)
    private BigDecimal bolsa;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusVaga status = StatusVaga.ABERTA;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDateTime dataPublicacao;

    @Column(name = "data_encerramento")
    private LocalDate dataEncerramento;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RequisitoVaga> requisitos = new ArrayList<>();

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BeneficioVaga> beneficios = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.dataPublicacao == null) {
            this.dataPublicacao = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusVaga.ABERTA;
        }
    }
}
