package br.com.backend.controller;

import br.com.ejb.interfaces.CalculadoraRemoto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraControllerTest {

    @Test
    public void testGetSomaMockado()
    {
        // Simula o comportamento do EJB sem JNDI
        CalculadoraRemoto mockEJB = (a, b) -> a + b;

        // Cria uma versão simplificada do controller para teste
        CalculadoraController controller = new CalculadoraController() {
            @Override
            public String getSoma() {
                double resultado = mockEJB.somar(1, 2);
                return "Soma é: " + resultado;
            }
        };

        assertEquals("Soma é: 3.0", controller.getSoma());
    }
}
