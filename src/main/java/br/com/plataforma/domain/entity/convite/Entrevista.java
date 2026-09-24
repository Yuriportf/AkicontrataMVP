package br.com.plataforma.domain.entity.convite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "entrevista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "convite_id", nullable = false, unique = true)
    private ConviteEntrevista convite;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(length = 255)
    private String local;

    @Column(name = "link_online", length = 255)
    private String linkOnline;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
