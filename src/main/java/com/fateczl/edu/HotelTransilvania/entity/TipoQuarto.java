package com.fateczl.edu.HotelTransilvania.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class TipoQuarto {

    @Id
    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 30, nullable = false)
    private String descricao;

    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal preco;

}
