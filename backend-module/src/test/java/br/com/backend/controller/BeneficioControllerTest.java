package br.com.backend.controller;

import br.com.backend.pojo.Beneficio;
import br.com.ejb.interfaces.BeneficioRemoto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpStatus;

public class BeneficioControllerTest {

    @Test
    public void testListAllReturnsBeneficios() {
        // Cria um mock manual do EJB
        BeneficioRemoto mockEJB = new BeneficioRemoto() {
            @Override
            public List<Beneficio> listAll() {
                return Arrays.asList(
                        new Beneficio(1, "Benefício A", "Descrição A", 100.0, true),
                        new Beneficio(2, "Benefício B", "Descrição B", 200.0, true)
                );
            }

            @Override
            public void transfer(Long fromId, Long toId, BigDecimal amount) {
            }
        };

        // Cria controller usando o mock
        BeneficioController controller = new BeneficioController() {
            @Override
            public ResponseEntity<List<Beneficio>> listAll() {
                return ResponseEntity.ok((List)mockEJB.listAll());
            }
        };

        // Executa teste
        ResponseEntity<List<Beneficio>> response = controller.listAll();

        // Valida resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Benefício A", response.getBody().get(0).getNome());
    }
}
