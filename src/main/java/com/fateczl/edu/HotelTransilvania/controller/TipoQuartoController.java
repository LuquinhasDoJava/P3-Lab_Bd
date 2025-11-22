package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.TipoQuarto;
import com.fateczl.edu.HotelTransilvania.service.TipoQuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/tipoQuarto")
public class TipoQuartoController {

    @Autowired
    private TipoQuartoService tipoQuartoService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("tipoQuarto", new TipoQuarto());
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/tipoQuarto/manterTipoQuarto";
    }

    @PostMapping("/salvar")
    public String salvarTipoQuarto(@RequestParam String nome,
                                   @RequestParam String descricao,
                                   @RequestParam BigDecimal preco,
                                   @RequestParam(required = false) String nomeOriginal,
                                   Model model){
        try {
            TipoQuarto tipoQuarto;
            if(nomeOriginal != null && !nomeOriginal.isEmpty()){
                TipoQuarto tipoQuartoExistente = tipoQuartoService.procurarPorId(nomeOriginal);

                if(tipoQuartoExistente != null){
                    tipoQuarto = tipoQuartoExistente;
                    tipoQuarto.setNome(nome);
                    tipoQuarto.setDescricao(descricao);
                    tipoQuarto.setPreco(preco);
                } else {
                    model.addAttribute("erro", "Tipo de Quarto não encontrado!!");
                    model.addAttribute("tipoQuarto", new TipoQuarto());
                    model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
                    return "/tipoQuarto/manterTipoQuarto";
                }
            } else {
                if(tipoQuartoService.procurarPorId(nome) != null){
                    model.addAttribute("erro", "Já existe um tipo de quarto com esse nome:"+ nome);
                    model.addAttribute("tipoQuarto", new TipoQuarto());
                    model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
                    return "/tipoQuarto/manterTipoQuarto";
                }
                tipoQuarto = new TipoQuarto(nome, descricao, preco);
            }
            tipoQuartoService.salvar(tipoQuarto);
            model.addAttribute("sucesso","Tipo de quarto " + (nomeOriginal != null ? "atualizado" : "cadastrado") + "com suceso!!");
            model.addAttribute("tipoQuarto",new TipoQuarto());
        } catch (Exception e){
            model.addAttribute("erro", "Erro ao salvar tipo de quarto: " + e.getMessage());
        }
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/tipoQuarto/manterTipoQuarto";
    }

    @GetMapping("/editar/{nome}")
    public String editarTipoQuarto(@PathVariable String nome, Model model){
        try {
            TipoQuarto tipoQuarto = tipoQuartoService.procurarPorId(nome);

            if(tipoQuarto != null){
                model.addAttribute("tipoQuarto", tipoQuarto);
                model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
                return "/tipoQuarto/manterTipoQuarto";
            } else {
                model.addAttribute("erro", "Tipo de quarto não encontrado!!");
            }

        } catch (Exception e) {
            model.addAttribute("erro","Erro ao editar tipo de quarto: " + e.getMessage());
        }
        model.addAttribute("tipoQuarto", new TipoQuarto());
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/tipoQuarto/manterTipoQuarto";
    }

    @PostMapping("/excluir/{nome}")
    public String excluirTipoQuarto(@PathVariable String nome, Model model){
        try{
            TipoQuarto tipoQuarto = tipoQuartoService.procurarPorId(nome);
            if(tipoQuarto != null){
                if(tipoQuartoService.existemQuartosComEsteTipo(nome)){
                    model.addAttribute("erro","Não é possivel excluir este tipo de quarto pois existem quartos vinculados a ele");
                } else {
                    tipoQuartoService.deletar(tipoQuarto);
                    model.addAttribute("sucesso","Tipo de quarto excluido com sucesso!!");
                }
            } else {
                model.addAttribute("erro","Tipo de quarto não encontrado!!");
            }
        } catch (Exception e){
            model.addAttribute("erro", "Erro ao editar tipo de quarto: " + e.getMessage());
        }
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        model.addAttribute("tipoQuarto", new TipoQuarto());
        return "/tipoQuarto/manterTipoQuarto";
    }
}
