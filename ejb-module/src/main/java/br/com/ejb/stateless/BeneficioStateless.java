
package br.com.ejb.stateless;



import br.com.ejb.interfaces.BeneficioRemoto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;

/**
 *
 * @author Edson
 */
@Stateless
public class BeneficioStateless implements BeneficioRemoto
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
      //  Beneficio from = em.find(Beneficio.class, fromId);
      //  Beneficio to   = em.find(Beneficio.class, toId);

        // BUG: sem validações, sem locking, pode gerar saldo negativo e lost update
       /* from.setValor(from.getValor().subtract(amount));
        to.setValor(to.getValor().add(amount));

        em.merge(from);
        em.merge(to);
*/
    }   
}
