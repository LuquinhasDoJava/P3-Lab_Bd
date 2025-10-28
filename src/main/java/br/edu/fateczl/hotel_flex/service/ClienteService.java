package br.edu.fateczl.hotel_flex.service;

import br.edu.fateczl.hotel_flex.entity.Cliente;
import br.edu.fateczl.hotel_flex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void salvar(Cliente c){
        clienteRepository.save(c);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }
}
