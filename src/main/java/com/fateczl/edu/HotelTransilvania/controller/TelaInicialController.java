package com.fateczl.edu.HotelTransilvania.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelaInicialController {

    @GetMapping("/")
    public String home() {
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}