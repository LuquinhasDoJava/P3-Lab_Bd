package com.fateczl.edu.HotelTransilvania;

import com.fateczl.edu.HotelTransilvania.entity.Cliente;
import com.fateczl.edu.HotelTransilvania.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TesteCliente {

    @Autowired
    ClienteService clienteService;

    @Test
    void listarTodos() {
        List<Cliente> clienteList = clienteService.listarTodos();
        for(Cliente cliente : clienteList){
            System.out.println("Cpf: "+cliente.getCpf()+"\nNome: "+cliente.getNome());
        }
    }

    @Test
    void procurarPorId(){
        String cpf = "51998194884";
        var cliente = clienteService.procurarPorId(cpf);
        if (cliente != null){
            System.out.println(cliente.getNome()+" foi encontrado!!");
        } else {
            System.out.println("Não foi encontrado ninguem!!");
        }
    }

    @Test
    void excluirAlguem(){
        Cliente cliente = new Cliente("51998194884","Lucas Siqueira Soares", "11958273505", "São Paulo");
        clienteService.deletar(cliente);
    }

    @Test
    void salvarAlguem(){
        Cliente cliente = new Cliente("51998194884","Lucas Siqueira Soares", "11958273505", "São Paulo");
        Cliente clienteSalvo = clienteService.salvar(cliente);
        System.out.println("O cliente salvo foi: "+ clienteSalvo.getNome());
    }

    @Test
    void atualizarAlguem(){
        Cliente cliente = new Cliente("51998194884","Lucas Siqueira Soares", "11958273505", "Bahia");
        clienteService.salvar(cliente);
    }

}