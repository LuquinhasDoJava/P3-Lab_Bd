package com.fateczl.edu.HotelTransilvania.entity;

import com.fateczl.edu.HotelTransilvania.serializable.ServicoSolicitadoId;
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
@IdClass(ServicoSolicitadoId.class)
public class ServicoSolicitado {

    @Id
    @ManyToOne
    @JoinColumn(name = "codigo_servico", nullable = false)
    private Servico servico;

    @Id
    @ManyToOne
    @JoinColumn(name = "codigo_estadia", nullable = false)
    private Estadia estadia;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "valor_pago", precision = 7, scale = 2, nullable = false)
    private BigDecimal valorPago;

}

