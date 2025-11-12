package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Cliente;
import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.entity.Reserva;
import com.fateczl.edu.HotelTransilvania.service.ClienteService;
import com.fateczl.edu.HotelTransilvania.service.QuartoService;
import com.fateczl.edu.HotelTransilvania.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String telaInicia(){
        return "/cliente/telaInicial";
    }

    @GetMapping("/reserva")
    public String telaReserva(){
        return "/cliente/cadastroReserva";
    }

    @GetMapping("/cadastroCliente")
    public String cadastroCliente(@ModelAttribute Cliente cliente, Model model){
        try{
            clienteService.salvar(cliente);
            model.addAttribute("sucesso","Cliente cadastrado com sucesso!!");
            model.addAttribute("cliente", new Cliente());
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar cliente: " + e.getMessage());
        }
        return "/cliente/cadastroCliente";
    }

    @GetMapping("/procurarQuartos")
    public String procurarQuartosDisponiveis(@RequestParam String dataEntrada,
                                             @RequestParam Integer diasReservados,
                                             @RequestParam String cpf, Model model){
        Cliente cliente = clienteService.procurarPorId(cpf);
        if(cliente != null ){
            model.addAttribute("sucesso","Cliente foi encontrado!");
            model.addAttribute("quartos",quartoService.listarTodos());
            return "/cliente/cadastroReserva";
        }
        model.addAttribute("erro","Cliente não encontrado!");
        model.addAttribute("quartos", null);
        return "/cliente/cadastroReserva";
    }

    @GetMapping("/escolherQuarto")
    public String escolherQuarto(@RequestParam String cpf,
                                 @RequestParam String dataEntrada,
                                 @RequestParam Integer diasReservados,
                                 @RequestParam Integer quartoId, Model model){
        try {
            Cliente cliente = clienteService.procurarPorId(cpf);
            if( cliente == null){
                model.addAttribute("erro","Cliente não encontrado!!");
                return "/cliente/cadastroReserva";
            }
            Quarto quarto = quartoService.procurarPorId(quartoId);

            Reserva reserva = new Reserva(cliente,quarto, LocalDate.parse(dataEntrada),diasReservados);

            reservaService.salvar(reserva);

            model.addAttribute("sucesso","Reserva realizada com sucesso!!");
            model.addAttribute("cpf", cpf);
            model.addAttribute("dataEntrada", dataEntrada);
            model.addAttribute("diasReservados", diasReservados);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao escolher quarto e salvar Reserva!" + e.getMessage());
        }
        return "/cliente/cadastroReserva";
    }

}
