
package br.com.ejb.dao;

import br.com.ejb.database.ConexaoBanco;
import br.com.ejb.exception.ExceptionVazio;
import br.com.ejb.pojo.Beneficio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson
 */
public class BeneficioDao
{
    public List<Beneficio> listarTodos() 
    {     
        List<Beneficio> beneficios = null;
        String sql = "SELECT ID, NOME, DESCRICAO, VALOR, ATIVO FROM BENEFICIO";

        try (Connection conn = ConexaoBanco.getConexaoLocal();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next())
            {     
                beneficios = new ArrayList<>();             
                do {
                    Beneficio b = new Beneficio();
                    b.setId(rs.getInt("ID"));
                    b.setNome(rs.getString("NOME"));
                    b.setDescricao(rs.getString("DESCRICAO"));
                    b.setValor(rs.getDouble("VALOR"));
                    b.setAtivo(rs.getBoolean("ATIVO"));
                    beneficios.add(b);
                }while(rs.next());               
            }else{
                throw new ExceptionVazio("Consulta de dados está vazio!");
            }   
        } catch (ExceptionVazio ev) {
            //throw ev; // relança sem transformar
            throw new ExceptionVazio(ev.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listarTodos - " + e.getMessage(), e);
        }       
        return beneficios;
    }
    
}
