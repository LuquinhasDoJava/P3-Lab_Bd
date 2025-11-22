package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    @Query("SELECT q FROM Quarto q WHERE q.numero = :numero AND q.andar = :andar")
    Optional<Quarto> findByNumeroAndAndar(@Param("numero") Integer numero, @Param("andar") Integer andar);

    @Query("SELECT q FROM Quarto q WHERE q.numero = :numero AND q.andar = :andar AND q.codigo != :codigo")
    Optional<Quarto> findByNumeroAndAndarExcludingId(
            @Param("numero") Integer numero,
            @Param("andar") Integer andar,
            @Param("codigo") Integer codigo);

    @Query("SELECT COUNT(q) FROM Quarto q WHERE q.tipoQuarto.nome = :nomeTipo")
    Long countByTipoQuartoNome(@Param("nomeTipo") String nomeTipo);

    List<Quarto> findByOcupadoFalse();

    @Query(value = "SELECT codigo_quarto, numero_quarto, andar_quarto, descricao, tipo_quarto, preco " +
            "FROM listarQuartosDisponiveis(:dataEntrada, :qtdDias)", nativeQuery = true)
    List<Object[]> findQuartosDisponiveis(@Param("dataEntrada") LocalDate dataEntrada,
                                          @Param("qtdDias") Integer qtdDias);
}