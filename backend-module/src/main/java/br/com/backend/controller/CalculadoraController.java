
package br.com.backend.controller;

import br.com.ejb.interfaces.CalculadoraRemoto;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Edson
 */
@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController 
{
   @GetMapping
    public String getSoma() 
    {
       Context ctx = null;
       CalculadoraRemoto servico = null;
       
       try {
           ctx = new InitialContext();
       } catch (NamingException ex) {
           System.getLogger(CalculadoraController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
       }

       try {
            servico = (CalculadoraRemoto) ctx.lookup("java:global/ejb-module/CalculadoraStateless!br.com.ejb.interfaces.CalculadoraRemoto");
       } catch (NamingException ex) {
           System.getLogger(CalculadoraController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
       }
       
       return String.valueOf("Soma é: "+ "" +servico.somar(1, 2));
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
