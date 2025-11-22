package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.TipoQuarto;
import com.fateczl.edu.HotelTransilvania.repository.QuartoRepository;
import com.fateczl.edu.HotelTransilvania.repository.TipoQuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoQuartoService {

    @Autowired
    private TipoQuartoRepository tipoQuartoRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    public TipoQuarto salvar(TipoQuarto tipoQuarto) {
        return tipoQuartoRepository.save(tipoQuarto);
    }

    public void deletar(TipoQuarto tipoQuarto) {
        tipoQuartoRepository.delete(tipoQuarto);
    }

    public List<TipoQuarto> listarTodos() {
        return tipoQuartoRepository.findAll();
    }

    public TipoQuarto procurarPorId(String nome) {
        return tipoQuartoRepository.findById(nome).orElse(null);
    }

    public boolean existemQuartosComEsteTipo(String nomeTipo) {
        Long count = quartoRepository.countByTipoQuartoNome(nomeTipo);
        return count != null && count > 0;
    }
}