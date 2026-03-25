package br.com.ejb.interfaces;




import jakarta.ejb.Remote;
import java.math.BigDecimal;

/**
 *
 * @author Edson
 */
@Remote
public interface BeneficioRemoto 
{
    public void transfer(Long fromId, Long toId, BigDecimal amount);
    
}
