package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public Quarto salvar(Quarto quarto){
        return quartoRepository.save(quarto);
    }

    public void deletar(Quarto quarto){
        quartoRepository.delete(quarto);
    }

    public List<Quarto> listarTodos(){
        return quartoRepository.findAll();
    }

    public Quarto procurarPorId(Integer id){
        return quartoRepository.findById(id).orElse(null);
    }

    public List<Quarto> procurarQuartosDisponiveisPorData(LocalDate entrada, LocalDate saida){
        List<Quarto> quartos = new ArrayList<>();

        return quartos;
    }

    public void deletarPorId(Integer id) {
        quartoRepository.deleteById(id);
    }
}