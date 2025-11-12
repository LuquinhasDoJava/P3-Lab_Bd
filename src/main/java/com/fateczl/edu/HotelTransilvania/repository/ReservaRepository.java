package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> { }