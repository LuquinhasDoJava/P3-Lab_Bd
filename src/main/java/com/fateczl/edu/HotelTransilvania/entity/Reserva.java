package com.fateczl.edu.HotelTransilvania.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_reserva",nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_quarto", nullable = false)
    private Quarto quarto;

    @Column(name = "data_reserva", nullable = false)
    private LocalDate dtEntrada;

    @Column(name = "dias_reservados", nullable = false)
    private Integer qtdDias;

    public Reserva(Cliente cliente, Quarto quarto, LocalDate parse, Integer diasReservados) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.dtEntrada = parse;
        this.qtdDias = diasReservados;
    }
}
