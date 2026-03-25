
package br.com.ejb.stateless;



import br.com.ejb.interfaces.CalculadoraRemoto;
import jakarta.ejb.Stateless;

/**
 *
 * @author Edson
 */
@Stateless
public class CalculadoraStateless implements CalculadoraRemoto
{
    @Override
    public double somar(double a, double b)
    {
        return (a + b);
    }
    
}
