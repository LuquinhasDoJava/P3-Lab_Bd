package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.Reserva;
import com.fateczl.edu.HotelTransilvania.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva salvar(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    public void deletar(Reserva reserva){
        reservaRepository.delete(reserva);
    }

    public List<Reserva> listarTodos(){
        return reservaRepository.findAll();
    }

    public Optional<Reserva> procurarPorId(Integer id){
        return reservaRepository.findById(id);
    }

}