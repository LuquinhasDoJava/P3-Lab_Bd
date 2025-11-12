package com.fateczl.edu.HotelTransilvania;

import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.service.QuartoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TesteQuarto {

    @Autowired
    QuartoService quartoService;

    @Test
    void salvarQuarto(){
    }

    @Test
    void listarTodos(){
        List<Quarto> quartoList = quartoService.listarTodos();

        for(Quarto quarto : quartoList){
            System.out.println("Andar: "+ quarto.getAndar() +" Numero: "+ quarto.getNumero() +" Tipo: "+ quarto.getTipoQuarto().getNome());
        }
    }

}