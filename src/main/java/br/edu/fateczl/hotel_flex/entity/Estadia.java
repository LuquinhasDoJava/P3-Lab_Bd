package br.edu.fateczl.hotel_flex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Estadia {

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

    @OneToMany(mappedBy = "estadia")
    private List<EstadiaServicos> servicosSolicitados;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "check_In", nullable = false)
    private LocalDateTime checkIn;

    @Column(name = "check_Out")
    private LocalDateTime checkOut;

}
