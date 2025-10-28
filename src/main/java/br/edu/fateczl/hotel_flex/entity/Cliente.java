package br.edu.fateczl.hotel_flex.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Cliente {

    @Id
    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 100)
    private String nome;

    @Column(nullable = false, length = 11)
    private String telefone;


    @Column(name = "cidade_origem", nullable = false, length = 35)
    private String cidadeOrigem;

}