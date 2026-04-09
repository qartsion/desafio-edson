package br.com.ejb.interfaces;




import jakarta.ejb.Remote;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Edson
 */
@Remote
public interface BeneficioRemoto 
{
    public void transfer(Long fromId, Long toId, BigDecimal amount);
    
     public List<?> listAll();
    
    
}
