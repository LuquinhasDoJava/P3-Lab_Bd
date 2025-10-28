package br.edu.fateczl.hotel_flex.entity;

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
@Getter
@Setter
@Entity
public class Quarto {

    @Id
    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Integer andar;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "tipo_quarto", nullable = false, length = 15)
    private String tipoQuarto;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal preco;

}
