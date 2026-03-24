
package br.com.ejb.local;

import jakarta.ejb.Local;
import java.math.BigDecimal;

/**
 *
 * @author Edson
 */
@Local
public interface BenefioLocal {
    public void transfer(Long fromId, Long toId, BigDecimal amount);
    
}
