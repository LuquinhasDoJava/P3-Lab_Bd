package br.edu.fateczl.hotel_flex.entity;

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
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true, length = 25)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal valor;

}
