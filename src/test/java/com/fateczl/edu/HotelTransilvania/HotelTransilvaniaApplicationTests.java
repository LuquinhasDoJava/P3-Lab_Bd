package com.fateczl.edu.HotelTransilvania;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HotelTransilvaniaApplicationTests {

    @Autowired
    TesteCliente testeCliente;

    @Test
    void teste(){
        testeCliente.atualizarAlguem();
    }

}
