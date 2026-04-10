
package br.com.ejb.stateless;



import br.com.ejb.interfaces.BeneficioRemoto;
import br.com.ejb.pojo.Beneficio;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Edson
 */
@Stateless
public class BeneficioStateless implements BeneficioRemoto
{
  
    @PersistenceContext//(unitName = "desafioPU")
    private EntityManager em;

        @Override
        public void transfer(Long fromId, Long toId, BigDecimal amount)
        {
            Beneficio from = em.find(Beneficio.class, fromId.intValue(), LockModeType.PESSIMISTIC_WRITE);
            Beneficio to   = em.find(Beneficio.class, toId.intValue(), LockModeType.PESSIMISTIC_WRITE);

            if (from == null || to == null) {
                throw new IllegalArgumentException("Conta origem ou destino inexistente");
            }
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Valor inválido para transferência");
            }
            if (BigDecimal.valueOf(from.getValor()).compareTo(amount) < 0) {
                throw new IllegalStateException("Saldo insuficiente");
            }

            // Atualiza valores
            from.setValor(from.getValor() - amount.doubleValue());
            to.setValor(to.getValor() + amount.doubleValue());

            em.merge(from);
            em.merge(to);
        }

    @Override
    public List<Beneficio> listAll() 
    {
         return em.createQuery("SELECT b FROM Beneficio b", Beneficio.class)
                 .getResultList();
    }

}
