package br.edu.fateczl.hotel_flex.repository;

import br.edu.fateczl.hotel_flex.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,String> {

}
