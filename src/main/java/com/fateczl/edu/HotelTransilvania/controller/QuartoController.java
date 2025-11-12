package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.entity.TipoQuarto;
import com.fateczl.edu.HotelTransilvania.service.QuartoService;
import com.fateczl.edu.HotelTransilvania.service.TipoQuartoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    private TipoQuartoService tipoQuartoService;
    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("quarto", new Quarto());
        model.addAttribute("quartos", quartoService.listarTodos());
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/quarto/cadastroQuarto";
    }

    @PostMapping("/salvar")
    public String salvarQuarto(@RequestParam Integer numero,
                               @RequestParam Integer andar,
                               @RequestParam String descricao,
                               @RequestParam String tipoQuartoNome,
                               Model model){
        try {
            TipoQuarto tipoQuarto = tipoQuartoService.procurarPorId(tipoQuartoNome);
            if(tipoQuarto != null){
                Quarto quarto = new Quarto(numero,andar, descricao, tipoQuarto);
                quartoService.salvar(quarto);
                model.addAttribute("sucesso","Quarto cadastrado com sucesso!!");
                model.addAttribute("quarto", new Quarto());
            } else {
                model.addAttribute("erro","Tipo de Quarto não encontrado!!");
                return "/quarto/cadastroQuarto";
            }
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao salvar quarto: " + e.getMessage());
        }
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/quarto/cadastroQuarto";
    }

    @GetMapping("/editar/{id}")
    public String editarQuarto(@PathVariable Integer id, Model model){
        try {
            Quarto quarto = quartoService.procurarPorId(id);
            if(quarto != null){
                model.addAttribute("quarto",quarto);
                model.addAttribute("tipoQuartos",tipoQuartoService.listarTodos());

                return "/quarto/cadastroQuarto";
            } else {
                model.addAttribute("ero","Quarto não encontrado!!");
                return "/quarto/cadastroQuarto";
            }
        } catch (EntityNotFoundException e){
            model.addAttribute("erro","Erro ao editar quartor: " + e.getMessage());
            return "/quarto/cadastroQuarto";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluirQuarto(@PathVariable Integer id, Model model){
        try {
            quartoService.deletarPorId(id);
            model.addAttribute("sucesso","Quarto deletado com sucesso!!");
        } catch (EntityNotFoundException e){
            model.addAttribute("err","Não foi possivel excluir o quarto: " + e.getMessage());
            return "/quarto/cadastroQuarto";
        }
        return "/quarto/cadastroQuarto";
    }


}
