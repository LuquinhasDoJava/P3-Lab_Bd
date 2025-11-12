package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> { }