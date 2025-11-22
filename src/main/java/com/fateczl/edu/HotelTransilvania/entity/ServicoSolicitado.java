package com.fateczl.edu.HotelTransilvania.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ServicoSolicitado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_servico_solicitado", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "codigo_servico", nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "codigo_estadia", nullable = false)
    private Estadia estadia;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "valor_pago", precision = 7, scale = 2, nullable = false)
    private BigDecimal valorPago;


    public ServicoSolicitado(Servico servico, Estadia estadia, Integer quantidade, BigDecimal valorPago) {
        this.servico = servico;
        this.estadia = estadia;
        this.quantidade = quantidade;
        this.valorPago = valorPago;
    }
}

