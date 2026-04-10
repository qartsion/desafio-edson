package br.com.backend.controller;


import br.com.backend.pojo.Beneficio;
import br.com.ejb.interfaces.BeneficioRemoto;
import java.math.BigDecimal;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beneficios")
public class BeneficioController {


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Beneficio>> listAll() 
    {
        BeneficioRemoto servico = null;

        try {
            Context ctx = new InitialContext();
            servico = (BeneficioRemoto) ctx.lookup(
                "java:global/ejb-module-1.0/BeneficioStateless!br.com.ejb.interfaces.BeneficioRemoto"
            );
        } catch (NamingException ex) {
            System.getLogger(BeneficioController.class.getName())
                  .log(System.Logger.Level.ERROR, "Erro no lookup JNDI", ex);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        if (servico == null) {
            // Retorna 503 Service Unavailable
            return ResponseEntity.status(503).build();
        }

        List<Beneficio> beneficios = (List<Beneficio>) servico.listAll();

        if (beneficios == null || beneficios.isEmpty()) {
            // Retorna 204 No Content
            return ResponseEntity.noContent().build();
        }

        // Retorna 200 OK com a lista
        return ResponseEntity.ok(beneficios);
    }


    @PostMapping(value = "/transfer", 
                consumes = "application/x-www-form-urlencoded", 
                produces = "text/plain")
    public ResponseEntity<String> transfer(@RequestParam Long fromId,
                                           @RequestParam Long toId,
                                           @RequestParam BigDecimal amount) {

        BeneficioRemoto servico = null;

        try {
            Context ctx = new InitialContext();
            servico = (BeneficioRemoto) ctx.lookup(
                "java:global/ejb-module-1.0/BeneficioStateless!br.com.ejb.interfaces.BeneficioRemoto"
            );
        } catch (NamingException ex) {
            return ResponseEntity.status(503).body("Serviço EJB não disponível");
        }

        if (servico == null) {
            return ResponseEntity.status(503).body("EJB BeneficioRemoto não encontrado");
        }

        try {
            servico.transfer(fromId, toId, amount);
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("Erro de negócio: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro inesperado: " + e.getMessage());
        }
    }
    
}
