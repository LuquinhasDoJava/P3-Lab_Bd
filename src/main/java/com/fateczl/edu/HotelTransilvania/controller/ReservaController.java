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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("reservas", reservaService.listarTodos());
        return "/reserva/manterReserva";
    }


    @GetMapping("/salvar")
    public String salvarReserva(@RequestParam (required = false) Integer id,
                                @RequestParam String cpfCliente,
                                @RequestParam Integer codigoQuarto,
                                @RequestParam LocalDate dtEntrada,
                                @RequestParam Integer qtdDias,
                                Model model){
        try{
            Cliente cliente = clienteService.procurarPorId(cpfCliente);
            Quarto quarto = quartoService.procurarPorId(codigoQuarto).orElse(null);
            if(cliente == null){
                model.addAttribute("erro", "Cliente não encontrado!!");
                model.addAttribute("reservas",reservaService.listarTodos());
                return "/reserva/manterReserva";
            } else if (quarto == null) {
                model.addAttribute("erro", "Quarto não encontrado!!");
                model.addAttribute("reservas",reservaService.listarTodos());
                return "/reserva/manterReserva";
            }
            Reserva reserva;
            if(id != null){
                reserva = reservaService.procurarPorId(id);
                if(reserva == null){
                    model.addAttribute("erro", "Reserva não encontrado!!");
                    return "/reserva/manterReserva";
                }
                reserva.setCliente(cliente);
                reserva.setQuarto(quarto);
                reserva.setDtEntrada(dtEntrada);
                reserva.setQtdDias(qtdDias);
            } else {
                reserva = new Reserva(cliente,quarto,dtEntrada,qtdDias);
            }
            reservaService.salvar(reserva);
            model.addAttribute("sucesso","Sucesso ao " + (id != null ? "atualizar" : "cadastrar") + "reserva!!");
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao salvar reserva: " + e.getMessage());
        }
        model.addAttribute("reservas", reservaService.listarTodos());
        return "/reserva/manterReserva";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable Integer id, Model model){
        try {
            Reserva reserva = reservaService.procurarPorId(id);
            model.addAttribute("reserva",reserva);
            model.addAttribute("reservas",reservaService.listarTodos());
            return "/reserva/manterReserva";
        } catch (Exception e) {
            model.addAttribute("reserva",new Reserva());
            model.addAttribute("reservas", reservaService.listarTodos());
            model.addAttribute("erro","Erro ao editar Reserva!");
            return "/reserva/manterReserva";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluirReserva(@PathVariable Integer id, Model model){
        try{
            Reserva reserva = reservaService.procurarPorId(id);
            reservaService.deletar(reserva);
            model.addAttribute("sucesso","Sucesso ao excluir reserva!!");
        } catch (Exception e){
            model.addAttribute("erro","Erro ao excluir o quarto: " + e.getMessage());
        }
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("reservas", reservaService.listarTodos());
        return "/reserva/manterReserva";
    }
}
