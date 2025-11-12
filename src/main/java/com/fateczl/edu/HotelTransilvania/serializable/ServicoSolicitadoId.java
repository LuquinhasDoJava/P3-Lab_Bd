package com.fateczl.edu.HotelTransilvania.serializable;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ServicoSolicitadoId implements Serializable {

    private Integer servico;
    private Integer estadia;

}
