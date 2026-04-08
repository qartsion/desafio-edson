
package br.com.ejb.pojo;

/**
 *
 * @author Edson
 */
public class Beneficio
{
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private boolean ativo;
    
    public Beneficio(){}
    
    public Beneficio(int id, String nome, String descricao, double valor, boolean ativo)
    {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.ativo = ativo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
