package com.fateczl.edu.HotelTransilvania.service;

import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public Quarto salvar(Quarto quarto) {
        Optional<Quarto> quartoExistente = quartoRepository.findByNumeroAndAndar(quarto.getNumero(), quarto.getAndar());

        if (quartoExistente.isPresent()) {
            throw new RuntimeException("Já existe um quarto com o número " + quarto.getNumero() + " no andar " + quarto.getAndar());
        }

        return quartoRepository.save(quarto);
    }

    public Quarto atualizar(Quarto quarto) {
        Optional<Quarto> quartoExistente = quartoRepository.findByNumeroAndAndarExcludingId(
                quarto.getNumero(), quarto.getAndar(), quarto.getCodigo());

        if (quartoExistente.isPresent()) {
            throw new RuntimeException("Já existe um quarto com o número " + quarto.getNumero() + " no andar " + quarto.getAndar());
        }

        return quartoRepository.save(quarto);
    }

    public void deletar(Quarto quarto){
        quartoRepository.delete(quarto);
    }

    public List<Quarto> listarTodos(){
        return quartoRepository.findAll();
    }

    public Optional<Quarto> procurarPorId(Integer id){
        return quartoRepository.findById(id);
    }

    public boolean existeQuartoComNumeroEAndar(Integer numero, Integer andar) {
        return quartoRepository.findByNumeroAndAndar(numero, andar).isPresent();
    }

    public boolean existeQuartoComNumeroEAndar(Integer numero, Integer andar, Integer excluirId) {
        return quartoRepository.findByNumeroAndAndarExcludingId(numero, andar, excluirId).isPresent();
    }

    public List<Quarto> listarQuartosDisponiveis() {
        return quartoRepository.findByOcupadoFalse();
    }

    public List<Object[]> findQuartosDisponiveis(LocalDate dataEntrada, Integer qtd) {
        return quartoRepository.findQuartosDisponiveis(dataEntrada, qtd);
    }
}