package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Cliente;
import com.fateczl.edu.HotelTransilvania.entity.Estadia;
import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.entity.Reserva;
import com.fateczl.edu.HotelTransilvania.service.ClienteService;
import com.fateczl.edu.HotelTransilvania.service.EstadiaService;
import com.fateczl.edu.HotelTransilvania.service.QuartoService;
import com.fateczl.edu.HotelTransilvania.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/estadia")
public class EstadiaController {

    @Autowired
    private EstadiaService estadiaService;

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("estadia",new Estadia());
        model.addAttribute("estadias",estadiaService.listarTodos());
        return "/estadia/manterEstadia";
    }

    @GetMapping("/salvar")
    public String salvarEstadia(@RequestParam(required = false) Integer id,
                                @RequestParam String cpfCliente,
                                @RequestParam(required = false) Integer quartoCodigo,
                                @RequestParam(required = false) Integer reservaCodigo,
                                @RequestParam(required = false) LocalDate checkIn,
                                @RequestParam(required = false) LocalDate checkOut,
                                @RequestParam(required = false) Boolean ativa,
                                Model model){
        try {
            Cliente cliente = clienteService.procurarPorId(cpfCliente);
            Quarto quarto = quartoService.procurarPorId(quartoCodigo).orElse(null);
            if(cliente == null){
                return erroRetorno(model,"Cliente não encontrado!!");
            } else if (quarto == null) {
                return erroRetorno(model,"Quarto não encontrado!!");
            }

            //Se chegar "vazio" a estadia não esta ativa, se chegar preenchida será o valor TRUE
            //pelo menos foi assim que eu fiz funcionar HAHAHAHA
            if(ativa == null){
                ativa = false;
            }

            Estadia estadia;
            if(id != null){
                Estadia estadiaExistente = estadiaService.procurarPorId(id).orElse(null);
                if(estadiaExistente == null){
                    return erroRetorno(model,"Estadia não encontrada!!");
                } else {
                    estadiaExistente.setCliente(cliente);
                    estadiaExistente.setQuarto(quarto);
                    if(reservaCodigo != null){
                        Reserva reserva = reservaService.procurarPorId(reservaCodigo);
                        if(reserva == null){
                            return erroRetorno(model,"Reserva não encontrada!!");
                        } else {
                            estadiaExistente.setReserva(reserva);
                        }
                    }
                    estadiaExistente.setCheckIn(checkIn);
                    estadiaExistente.setCheckOut(checkOut);
                    estadiaExistente.setAtiva(ativa);
                    estadia = estadiaExistente;
                }
            } else {
                if(reservaCodigo == null){
                    estadia = new Estadia(cliente,quarto,checkIn,checkOut,true);
                } else {
                    Reserva reserva = reservaService.procurarPorId(reservaCodigo);
                    estadia = new Estadia(cliente,quarto,reserva,checkIn,checkOut,true);
                }
            }
            estadiaService.salvar(estadia);
            model.addAttribute("sucesso","Estadia " + (id != null ? "atualizada" : "cadastrada") + "com sucesso!!");
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao salvar estadia: " + e.getMessage());
        }
        model.addAttribute("estadias", estadiaService.listarTodos());
        return "/estadia/manterEstadia";
    }

    @GetMapping("/editar/{id}")
    public String editarEstadia(@PathVariable Integer id, Model model){
        try {
            Estadia estadia = estadiaService.procurarPorId(id).orElse(null);
            model.addAttribute("estadia", estadia);
            model.addAttribute("estadias",estadiaService.listarTodos());
            return "/estadia/manterEstadia";
        } catch (Exception e) {
            return erroRetorno(model,"Erro ao editar Estadia: " + e.getMessage());
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluirEstadia(@PathVariable Integer id, Model model){
        try{
            Estadia estadia = estadiaService.procurarPorId(id).orElse(null);
            estadiaService.deletar(estadia);
            model.addAttribute("sucesso","Estadia excluida com sucesso!!");
        } catch (Exception e){
            return erroRetorno(model,"Erro ao excluir estadia: " + e.getMessage());
        }
        model.addAttribute("estadia",new Estadia());
        model.addAttribute("estadias",estadiaService.listarTodos());
        return "/estadia/manterEstadia";
    }

    private String erroRetorno(Model model, String mensagem) {
        model.addAttribute("erro",mensagem);
        model.addAttribute("estadia",new Estadia());
        model.addAttribute("estadias", estadiaService.listarTodos());
        return "/estadia/manterEstadia";
    }
}
