package br.edu.fateczl.hotel_flex.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Quarto quarto;

    @Column(name = "dt_Entrada", nullable = false)
    private LocalDateTime dtEntrada;

    @Column(name = "dt_Saida", nullable = false)
    private LocalDateTime dtSaida;

}
