package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.Cliente;
import com.fateczl.edu.HotelTransilvania.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deletar(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente procurarPorId(String cpf){
        return clienteRepository.findById(cpf).orElse(null);
    }

}