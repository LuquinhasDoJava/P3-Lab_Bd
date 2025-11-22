package com.fateczl.edu.HotelTransilvania.controller;

import com.fateczl.edu.HotelTransilvania.entity.Estadia;
import com.fateczl.edu.HotelTransilvania.entity.Servico;
import com.fateczl.edu.HotelTransilvania.entity.ServicoSolicitado;
import com.fateczl.edu.HotelTransilvania.service.EstadiaService;
import com.fateczl.edu.HotelTransilvania.service.ServicoService;
import com.fateczl.edu.HotelTransilvania.service.ServicoSolicitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/servico-solicitado")
public class ServicoSolicitadoController {

    @Autowired
    private ServicoSolicitadoService servicoSolicitadoService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private EstadiaService estadiaService;

    @GetMapping
    public String telaInicial(Model model){
        return carregarTela(model, new ServicoSolicitado());
    }

    @PostMapping("/salvar")
    public String salvarServicoSolicitado(@RequestParam(required = false) Integer id,
                                          @RequestParam Integer codigoServico,
                                          @RequestParam Integer codigoEstadia,
                                          @RequestParam Integer quantidade,
                                          @RequestParam(required = false) BigDecimal valorPago,
                                          Model model){
        try {
            Servico servico = servicoService.procurarPorId(codigoServico);
            Estadia estadia = estadiaService.procurarPorId(codigoEstadia).orElse(null);
            if(servico == null){
                return erroRetorno(model,"Servico não encontrado!!");
            } else if (estadia == null) {
                return erroRetorno(model,"Estadia não encontrada!!");
            } else if (id != null) {
                ServicoSolicitado servicoSolicitado = servicoSolicitadoService.procurarPorId(id);
                if(servicoSolicitado == null){
                    return erroRetorno(model,"Servico solicitado não encontrado!!");
                } else {
                    servicoSolicitado.setServico(servico);
                    servicoSolicitado.setEstadia(estadia);
                    servicoSolicitado.setQuantidade(quantidade);
                    servicoSolicitado.setValorPago(valorPago);
                    servicoSolicitadoService.salvar(servicoSolicitado);
                    model.addAttribute("sucesso","Servico solicitado atualizado com sucesso!!");
                    return carregarTela(model, servicoSolicitado);
                }
            } else {
                BigDecimal valor;
                if(valorPago == null){
                    valor = servico.getPreco().multiply(new BigDecimal(quantidade));
                } else {
                    valor = valorPago;
                }
                ServicoSolicitado servicoSolicitado = new ServicoSolicitado(servico, estadia, quantidade, valor);
                servicoSolicitadoService.salvar(servicoSolicitado);
                model.addAttribute("sucesso","Servico solicitado cadastrado com sucesso!!");
                return carregarTela(model, new ServicoSolicitado());
            }
        } catch (Exception e) {
            return erroRetorno(model,"Erro ao salvar servico solicitado: " + e.getMessage());
        }
    }

    @GetMapping("/editar/{id}")
    public String editarServicoSolicitado(@PathVariable Integer id, Model model){
        try {
            ServicoSolicitado servicoSolicitado = servicoSolicitadoService.procurarPorId(id);
            if (servicoSolicitado != null) {
                return carregarTela(model, servicoSolicitado);
            } else {
                return erroRetorno(model,"Servico solicidato não encontrado!!");
            }
        } catch (Exception e) {
            return erroRetorno(model,"Erro ao editar servico solicitado: "  + e.getMessage());
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluirServicoSolicitado(@PathVariable Integer id, Model model){
        try {
            ServicoSolicitado servicoSolicitado = servicoSolicitadoService.procurarPorId(id);
            if(servicoSolicitado == null){
                return erroRetorno(model,"Servico solicitado não encontrado!!");
            } else {
                servicoSolicitadoService.deletar(servicoSolicitado);
                model.addAttribute("sucesso","Servico solicitado deletado com sucesso!!");
                return carregarTela(model, new ServicoSolicitado());
            }
        } catch (Exception e){
            return erroRetorno(model,"Erro ao excluir servico solicitado: " + e.getMessage());
        }
    }

    private String erroRetorno(Model model, String mensagem) {
        model.addAttribute("erro",mensagem);
        return carregarTela(model,new ServicoSolicitado());
    }

    private String carregarTela(Model model, ServicoSolicitado servicoSolicitado) {
        model.addAttribute("servicoSolicitado", servicoSolicitado);
        model.addAttribute("servicoSolicitados",servicoSolicitadoService.listarTodos());
        return "/servicoSolicitado/manterServicoSolicitado";
    }
}
