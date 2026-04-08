
package br.com.ejb.interfaces;

import jakarta.ejb.Remote;

/**
 *
 * @author Edson
 */
@Remote
public interface CalculadoraRemoto
{
    public double somar(double a, double b);
}
