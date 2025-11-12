package com.fateczl.edu.HotelTransilvania.repository;

import com.fateczl.edu.HotelTransilvania.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    public List<Cliente> findQuartosBy();

}