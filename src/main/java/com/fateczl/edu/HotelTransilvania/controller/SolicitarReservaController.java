package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.dto.QuartoDisponivelDTO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/solicitar-reserva")
public class SolicitarReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("quartosLivres",quartoService.listarQuartosDisponiveis());
        return "/reserva/reservarQuarto";
    }

    @GetMapping("/procurarCliente")
    public String procurarCliente(@RequestParam String id, Model model){
        try {
            Cliente cliente = clienteService.procurarPorId(id);
            if(cliente == null){
                model.addAttribute("erro","Cliente não encontrado!!");
            } else {
                model.addAttribute("sucesso","Cliente encontrado com sucesso!!");
            }
        } catch (Exception e){
            model.addAttribute("erro","Erro ao procurar cliente: " + e.getMessage());
        }
        model.addAttribute("quartosLivres",quartoService.listarQuartosDisponiveis());
        return "/reserva/reservarQuarto";
    }

    @GetMapping("/procurarQuartos")
    public String procurarQuarto(@RequestParam LocalDate dataEntrada,
                                 @RequestParam Integer qtd,
                                 Model model){
        try{
            List<Object[]> resultados = quartoService.findQuartosDisponiveis(dataEntrada, qtd);

            // Resto do código igual...
            List<QuartoDisponivelDTO> quartosDisponiveis = new ArrayList<>();

            for (Object[] resultado : resultados) {
                QuartoDisponivelDTO dto = new QuartoDisponivelDTO();
                dto.setCodigoQuarto(((Number) resultado[0]).intValue());
                dto.setNumeroQuarto(((Number) resultado[1]).intValue());
                dto.setAndarQuarto(((Number) resultado[2]).intValue());
                dto.setDescricao((String) resultado[3]);
                dto.setTipoQuarto((String) resultado[4]);
                dto.setPreco(((Number) resultado[5]).doubleValue());
                quartosDisponiveis.add(dto);
            }

            model.addAttribute("quartosDisponiveis", quartosDisponiveis);
            model.addAttribute("dataEntrada", dataEntrada);
            model.addAttribute("qtdDias", qtd);


        } catch (Exception e){
            model.addAttribute("erro","Erro ao procurar quartos: " + e.getMessage());
        }
        return "/reserva/reservarQuarto";
    }

    @GetMapping("/finalizar-reserva")
    public String finalizarReserva(@RequestParam String cpfCliente,
                                   @RequestParam Integer codigoQuarto,
                                   @RequestParam LocalDate dtEntrada,
                                   @RequestParam Integer qtdDias,
                                   Model model){
        try {
            Cliente cliente = clienteService.procurarPorId(cpfCliente);
            Quarto quarto = quartoService.procurarPorId(codigoQuarto).orElse(null);
            if(cliente == null){
                model.addAttribute("erro","Cliente não encontrado!!");
                return "/reserva/reservarQuarto";
            } else if(quarto == null){
                model.addAttribute("erro","Quarto não encontrado!!");
                return "/reserva/reservarQuarto";
            }else {
                Reserva reserva = new Reserva(cliente,quarto,dtEntrada,qtdDias);
                reservaService.salvar(reserva);
                model.addAttribute("sucesso","Reserva feita com sucesso!!");
            }
        } catch (Exception e){
            model.addAttribute("erro","Erro ao finalizar reserva: " + e.getMessage());
            return "/reserva/reservarQuarto";
        }
        return "/reserva/reservarQuarto";
    }
}
