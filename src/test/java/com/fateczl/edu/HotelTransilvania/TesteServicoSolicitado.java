package com.fateczl.edu.HotelTransilvania;

import com.fateczl.edu.HotelTransilvania.entity.ServicoSolicitado;
import com.fateczl.edu.HotelTransilvania.service.ServicoSolicitadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TesteServicoSolicitado {

    @Autowired
    private ServicoSolicitadoService servicoSolicitadoService;

    @Test
    void listarTodos(){
        List<ServicoSolicitado> servicoSolicitadoList = servicoSolicitadoService.listarTodos();

        for(ServicoSolicitado servicoSolicitado : servicoSolicitadoList){
            System.out.println("Estadia: "+ servicoSolicitado.getEstadia().getId() +" Solicitou o servico de "+ servicoSolicitado.getServico().getNome() +" para : "+ servicoSolicitado.getEstadia().getCliente().getNome());
        }
    }

}
