package br.edu.fateczl.hotel_flex;

import br.edu.fateczl.hotel_flex.entity.Cliente;
import br.edu.fateczl.hotel_flex.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HotelFlexApplicationTests {

    @Autowired
    ClienteService service;
	@Test
	void contextLoads() {
/*        Cliente cliente = new Cliente("51998194884","Lucas Siqueira","11958273505","São Paulo");
        service.salvar(cliente);*/
        List<Cliente> clienteList = service.listarTodos();

        for(Cliente c: clienteList){
            System.out.println(c.toString());
        }


	}

}
