package com.fateczl.edu.HotelTransilvania.controller;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/quartos-disponiveis")
    public void gerarRelatorioQuartosDisponiveis(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataConsulta,
                                                 HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=quartos-disponiveis-" + dataConsulta + ".pdf");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("data_consulta", java.sql.Date.valueOf(dataConsulta));
            parametros.put("titulo_relatorio", "Relatório de Quartos Disponíveis");

            // Agora vai encontrar o arquivo na pasta correta
            ClassPathResource resource = new ClassPathResource("reports/quartos-disponiveis.jrxml");
            InputStream jasperStream = resource.getInputStream();

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            try (Connection conexao = dataSource.getConnection()) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            }

            response.flushBuffer();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de quartos disponíveis: " + e.getMessage(), e);
        }
    }

    @GetMapping("/reservas-dia")
    public void gerarRelatorioReservasDia(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataConsulta,
                                          HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=reservas-dia-" + dataConsulta + ".pdf");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("data_consulta", java.sql.Date.valueOf(dataConsulta));
            parametros.put("titulo_relatorio", "Relatório de Reservas do Dia");

            ClassPathResource resource = new ClassPathResource("reports/reservas-dia.jrxml");
            InputStream jasperStream = resource.getInputStream();

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            try (Connection conexao = dataSource.getConnection()) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            }

            response.flushBuffer();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de reservas do dia: " + e.getMessage(), e);
        }
    }

    @GetMapping("/cliente-hospedagem-servicos")
    public void gerarRelatorioClienteHospedagemServicos(@RequestParam Integer codigoEstadia,
                                                        HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=cliente-hospedagem-servicos-" + codigoEstadia + ".pdf");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigo_estadia", codigoEstadia);
            parametros.put("titulo_relatorio", "Relatório de Cliente - Hospedagem e Serviços");

            ClassPathResource resource = new ClassPathResource("reports/cliente-hospedagem-servicos.jrxml");
            InputStream jasperStream = resource.getInputStream();

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            try (Connection conexao = dataSource.getConnection()) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            }

            response.flushBuffer();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de cliente/hospedagem/serviços: " + e.getMessage(), e);
        }
    }
}