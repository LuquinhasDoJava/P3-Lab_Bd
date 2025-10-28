package br.edu.fateczl.hotel_flex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EstadiaServicos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Estadia estadia;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Servico servico;

    @Column(name = "dt_Solicitado", nullable = false)
    private LocalDateTime dtSolicitado;

    @Column(nullable = false)
    private Integer quantidade;
}
