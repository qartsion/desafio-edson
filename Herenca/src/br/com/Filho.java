
package br.com;

/**
 *
 * @author Edson
 */
public class Filho extends Pai
{

    public Filho() {
         System.out.println("Construtor Filho");
    }

    @Override
    public void imprimirPai() {
        System.err.println("Imprimir Método Filho!");
    }
    
    
    
}
