package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.ServicoSolicitado;
import com.fateczl.edu.HotelTransilvania.serializable.ServicoSolicitadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoSolicitadoRepository extends JpaRepository<ServicoSolicitado, ServicoSolicitadoId> { }