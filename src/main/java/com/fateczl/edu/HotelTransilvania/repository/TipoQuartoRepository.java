package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.TipoQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoQuartoRepository extends JpaRepository<TipoQuarto, String> { }