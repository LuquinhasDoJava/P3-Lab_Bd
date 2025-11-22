package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.Servico;
import com.fateczl.edu.HotelTransilvania.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico salvar(Servico servico){
        return servicoRepository.save(servico);
    }

    public void deletar(Servico servico){
        servicoRepository.delete(servico);
    }

    public List<Servico> listarTodos(){
        return servicoRepository.findAll();
    }

    public Servico procurarPorId(Integer id){
        return servicoRepository.findById(id).orElse(null);
    }

}