package com.fateczl.edu.HotelTransilvania.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Estadia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_estadia", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "quarto", nullable = false)
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "codigo_reserva")
    private Reserva reserva;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @Column(nullable = false)
    private boolean ativa;

    public Estadia(Cliente cliente, Quarto quarto, Reserva reserva, LocalDate checkIn, LocalDate checkOut, boolean ativa) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.reserva = reserva;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ativa = ativa;
    }

    public Estadia(Cliente cliente, Quarto quarto, LocalDate checkIn, LocalDate checkOut, boolean ativa) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ativa = ativa;
    }
}
