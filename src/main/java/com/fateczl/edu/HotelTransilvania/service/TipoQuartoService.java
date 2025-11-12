package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.TipoQuarto;
import com.fateczl.edu.HotelTransilvania.repository.TipoQuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoQuartoService {

    @Autowired
    TipoQuartoRepository tipoQuartoRepository;

    public List<TipoQuarto> listarTodos(){
        return tipoQuartoRepository.findAll();
    }

    public TipoQuarto procurarPorId(String id){
        return tipoQuartoRepository.findById(id).orElse(null);
    }
}
