package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.Estadia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadiaRepository extends JpaRepository<Estadia, Integer> { }