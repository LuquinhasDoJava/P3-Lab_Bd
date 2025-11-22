package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Servico;
import com.fateczl.edu.HotelTransilvania.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("servico", new Servico());
        model.addAttribute("servicos", servicoService.listarTodos());
        return "/servico/manterServico";
    }

    @PostMapping("/salvar")
    public String salvarServico(@RequestParam String nome,
                                @RequestParam BigDecimal preco,
                                @RequestParam String descricao,
                                @RequestParam(required = false) Integer id,
                                Model model){
        try {
            Servico servico;
            if(id != null){
                // Edição
                Servico servicoExistente = servicoService.procurarPorId(id);
                if(servicoExistente == null){
                    model.addAttribute("erro", "Serviço não encontrado!!");
                    model.addAttribute("servico", new Servico());
                    model.addAttribute("servicos", servicoService.listarTodos());
                    return "/servico/manterServico";
                } else {
                    // Atualiza os dados do serviço existente
                    servicoExistente.setNome(nome);
                    servicoExistente.setDescricao(descricao);
                    servicoExistente.setPreco(preco);
                    servico = servicoExistente;
                }
            } else {
                // Criação
                servico = new Servico(nome, descricao, preco);
            }

            servicoService.salvar(servico);
            model.addAttribute("sucesso","Serviço " + (id != null ? "atualizado" : "cadastrado") + " com sucesso!!");
            model.addAttribute("servico", new Servico());
        } catch (Exception e){
            model.addAttribute("erro", "Erro ao salvar serviço: " + e.getMessage());
        }
        model.addAttribute("servicos", servicoService.listarTodos());
        return "/servico/manterServico";
    }

    @GetMapping("/editar/{id}")
    public String editarServico(@PathVariable Integer id, Model model){
        try {
            Servico servico = servicoService.procurarPorId(id);

            if(servico != null){
                model.addAttribute("servico", servico);
                model.addAttribute("servicos", servicoService.listarTodos());
                return "/servico/manterServico";
            } else {
                model.addAttribute("erro", "Serviço não encontrado!!");
            }
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao editar serviço: " + e.getMessage());
        }
        model.addAttribute("servico", new Servico());
        model.addAttribute("servicos", servicoService.listarTodos());
        return "/servico/manterServico";
    }

    @PostMapping("/excluir/{id}")
    public String excluirServico(@PathVariable Integer id, Model model){
        try{
            Servico servico = servicoService.procurarPorId(id);
            if(servico != null){
                servicoService.deletar(servico);
                model.addAttribute("sucesso","Serviço excluído com sucesso!!");
            } else {
                model.addAttribute("erro","Serviço não encontrado!!");
            }
        } catch (Exception e){
            model.addAttribute("erro", "Erro ao excluir serviço: " + e.getMessage());
        }
        model.addAttribute("servicos", servicoService.listarTodos());
        model.addAttribute("servico", new Servico());
        return "/servico/manterServico";
    }
}