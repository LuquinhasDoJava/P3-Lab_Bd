package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.Estadia;
import com.fateczl.edu.HotelTransilvania.repository.EstadiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadiaService {

    @Autowired
    private EstadiaRepository estadiaRepository;

    public Estadia salvar(Estadia estadia){
        return estadiaRepository.save(estadia);
    }

    public void deletar(Estadia estadia){
        estadiaRepository.delete(estadia);
    }

    public List<Estadia> listarTodos(){
        return estadiaRepository.findAll();
    }

    public Optional<Estadia> procurarPorId(Integer id){
        return  estadiaRepository.findById(id);
    }

}