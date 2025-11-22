package com.fateczl.edu.HotelTransilvania.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDisponivelDTO {
    private Integer codigoQuarto;
    private Integer numeroQuarto;
    private Integer andarQuarto;
    private String descricao;
    private String tipoQuarto;
    private Double preco;
}