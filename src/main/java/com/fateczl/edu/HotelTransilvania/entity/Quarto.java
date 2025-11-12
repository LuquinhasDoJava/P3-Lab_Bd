package com.fateczl.edu.HotelTransilvania.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_quarto", nullable = false)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "tipo", nullable = false)
    private TipoQuarto tipoQuarto;

    @Column(name = "andar_quarto", nullable = false)
    private Integer andar;

    @Column(name = "numero_quarto", nullable = false)
    private Integer numero;

    @Column(length = 100, nullable = false)
    private String descricao;

    public Quarto(Integer numero, Integer andar, String descricao, TipoQuarto tipoQuarto) {
        this.numero = numero;
        this.andar = andar;
        this.descricao = descricao;
        this.tipoQuarto = tipoQuarto;
    }
}
