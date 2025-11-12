package com.fateczl.edu.HotelTransilvania.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 13, nullable = false)
    private String telefone;

    @Column(length = 30, nullable = false)
    private String cidadeOrigem;

}
