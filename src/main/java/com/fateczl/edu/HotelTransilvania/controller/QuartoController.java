package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Quarto;
import com.fateczl.edu.HotelTransilvania.entity.TipoQuarto;
import com.fateczl.edu.HotelTransilvania.service.QuartoService;
import com.fateczl.edu.HotelTransilvania.service.TipoQuartoService;
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
        return "/quarto/manterQuarto";
    }

    @PostMapping("/salvar")
    public String salvarQuarto(@RequestParam Integer numero,
                               @RequestParam Integer andar,
                               @RequestParam String descricao,
                               @RequestParam String tipoQuartoNome,
                               @RequestParam(required = false) Integer codigo,
                               Model model){
        try {
            TipoQuarto tipoQuarto = tipoQuartoService.procurarPorId(tipoQuartoNome);
            if(tipoQuarto != null){
                Quarto quarto;

                if (codigo != null && codigo > 0) {
                    // Edição
                    quarto = quartoService.procurarPorId(codigo)
                            .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

                    // Verifica se outro quarto já tem este número e andar
                    if (quartoService.existeQuartoComNumeroEAndar(numero, andar, codigo)) {
                        model.addAttribute("erro", "Já existe um quarto com o número " + numero + " no andar " + andar);
                        model.addAttribute("quarto", quarto);
                        model.addAttribute("quartos", quartoService.listarTodos());
                        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
                        return "/quarto/manterQuarto";
                    }

                    quarto.setNumero(numero);
                    quarto.setAndar(andar);
                    quarto.setDescricao(descricao);
                    quarto.setTipoQuarto(tipoQuarto);
                    quartoService.atualizar(quarto);
                } else {
                    if (quartoService.existeQuartoComNumeroEAndar(numero, andar)) {
                        model.addAttribute("erro", "Já existe um quarto com o número " + numero + " no andar " + andar);
                        model.addAttribute("quarto", new Quarto());
                        model.addAttribute("quartos", quartoService.listarTodos());
                        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
                        return "/quarto/manterQuarto";
                    }

                    quarto = new Quarto(numero, andar, descricao, tipoQuarto);
                    quartoService.salvar(quarto);
                }

                model.addAttribute("sucesso", "Quarto " + (codigo != null ? "atualizado" : "cadastrado") + " com sucesso!!");
                model.addAttribute("quarto", new Quarto()); // Limpa o formulário

            } else {
                model.addAttribute("erro","Tipo de Quarto não encontrado!!");
            }
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao salvar quarto: " + e.getMessage());
        }
        model.addAttribute("quartos", quartoService.listarTodos());
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/quarto/manterQuarto";
    }

    @GetMapping("/editar/{id}")
    public String editarQuarto(@PathVariable Integer id, Model model){
        try {
            Quarto quarto = quartoService.procurarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
            model.addAttribute("quarto", quarto);
            model.addAttribute("quartos", quartoService.listarTodos());
            model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
            return "/quarto/manterQuarto";
        } catch (Exception e){
            model.addAttribute("erro","Erro ao editar quarto: " + e.getMessage());
            model.addAttribute("quarto", new Quarto());
            model.addAttribute("quartos", quartoService.listarTodos());
            model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
            return "/quarto/manterQuarto";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluirQuarto(@PathVariable Integer id, Model model) {
        try {
            Quarto quarto = quartoService.procurarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
            quartoService.deletar(quarto);
            model.addAttribute("sucesso", "Quarto deletado com sucesso!!");
        } catch (Exception e) {
            model.addAttribute("erro", "Não foi possível excluir o quarto: " + e.getMessage());
        }
        model.addAttribute("quarto", new Quarto());
        model.addAttribute("quartos", quartoService.listarTodos());
        model.addAttribute("tiposQuartos", tipoQuartoService.listarTodos());
        return "/quarto/manterQuarto";
    }
}