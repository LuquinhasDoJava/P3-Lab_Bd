package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.ServicoSolicitado;
import com.fateczl.edu.HotelTransilvania.repository.ServicoSolicitadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoSolicitadoService {

    @Autowired
    private ServicoSolicitadoRepository servicoSolicitadoRepository;

    public ServicoSolicitado salvar(ServicoSolicitado servicoSolicitado){
        return servicoSolicitadoRepository.save(servicoSolicitado);
    }

    public void deletar(ServicoSolicitado servicoSolicitado){
        servicoSolicitadoRepository.delete(servicoSolicitado);
    }

    public List<ServicoSolicitado> listarTodos(){
        return servicoSolicitadoRepository.findAll();
    }

    public ServicoSolicitado procurarPorId(Integer id){
        return servicoSolicitadoRepository.findById(id).orElse(null);
    }

}