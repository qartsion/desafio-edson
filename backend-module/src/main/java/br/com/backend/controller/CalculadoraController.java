package br.com.backend.controller;

import br.com.ejb.interfaces.CalculadoraRemoto;
import static java.lang.System.exit;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {

    @GetMapping
    public String getSoma() {
        CalculadoraRemoto servico = null;

        try {
            Context ctx = new InitialContext();

            // Nome JNDI baseado na classe CalculadoraStateless
            servico = (CalculadoraRemoto) ctx.lookup(
                "java:global/ejb-module-1.0/CalculadoraStateless!br.com.ejb.interfaces.CalculadoraRemoto"
            );
            //    "java:jboss/exported/ejb-module-1.0/CalculadoraStateless!br.com.ejb.interfaces.CalculadoraRemoto"

        } catch (NamingException ex) {
            System.getLogger(CalculadoraController.class.getName())
                  .log(System.Logger.Level.ERROR, "Erro no lookup JNDI", ex);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        if (servico == null) {
            return "Erro: EJB CalculadoraRemoto não encontrado via JNDI.";
        }

        double resultado = servico.somar(1, 2);
        return "Soma é: " + resultado;
    }
}



/*
    Quando não é especificado no GetMapping, ele retorna o que e resiação está
    no cabeçalho

    GET /api/dados
    Accept: application/xml

    // Retorna JSON
    @GetMapping(produces = "application/json")
    public List<String> listarJson() {
        return List.of("Vale Transporte", "Vale Refeição", "Plano de Saúde");
    }

    // Retorna XML
    @GetMapping(value = "/xml", produces = "application/xml")
    public Beneficio listarXml() {
        return new Beneficio("Vale Refeição", "Cartão para alimentação");
    }

    // Retorna texto puro
    @GetMapping(value = "/texto", produces = "text/plain")
    public String listarTexto() {
       
*/
