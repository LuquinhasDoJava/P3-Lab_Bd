package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Cliente;
import com.fateczl.edu.HotelTransilvania.service.ClienteService;
import com.fateczl.edu.HotelTransilvania.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public String telaInicial(Model model){
        model.addAttribute("cliente",new Cliente());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "/cliente/manterCliente";
    }

    @GetMapping("/salvar")
    public String salvarCliente(@RequestParam String cpf,
                                @RequestParam String nome,
                                @RequestParam String telefone,
                                @RequestParam String cidadeOrigem,
                                Model model){
        try{
            Cliente clienteExistente = clienteService.procurarPorId(cpf);
            if(clienteExistente == null){
                clienteService.salvar(new Cliente(cpf,nome,telefone,cidadeOrigem));
                model.addAttribute("sucesso","Cliente cadastrado com sucesso!!");
            } else {
                clienteExistente.setCidadeOrigem(cidadeOrigem);
                clienteExistente.setNome(nome);
                clienteExistente.setTelefone(telefone);
                clienteService.salvar(clienteExistente);
                model.addAttribute("sucesso","Cliente atualizado com sucesso!!");
            }
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao salvar cliente: " + e.getMessage());
        }
        model.addAttribute("cliente",new Cliente());
        model.addAttribute("clientes",clienteService.listarTodos());
        return "/cliente/manterCliente";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable String cpf, Model model){
        try {
            Cliente cliente = clienteService.procurarPorId(cpf);
            if(cliente != null){
                model.addAttribute("sucesso","Cliente encontrado com sucesso!!");
                model.addAttribute("cliente", cliente);
            } else {
                model.addAttribute("cliente",new Cliente());
                model.addAttribute("erro","Cliente não econtrado!!");
            }
        } catch (Exception e) {
            model.addAttribute("erro","Erro ao editar cliente: " + e.getMessage());
        }
        model.addAttribute("clientes", clienteService.listarTodos());
        return "/cliente/manterCliente";
    }

    @PostMapping("/excluir/{cpf}")
    public String excluirCliente(@PathVariable String cpf, Model model){
        try {
            Cliente cliente = clienteService.procurarPorId(cpf);
            if(cliente != null){
                clienteService.deletar(cliente);
                model.addAttribute("sucesso","Cliente excluido com sucesso!!");
            } else {
                model.addAttribute("erro","Cliente não encontrado!!");
            }
        } catch (Exception e){
            model.addAttribute("erro","Erro ao excluir cliente: " + e.getMessage());
        }
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "/cliente/manterCliente";
    }
}
