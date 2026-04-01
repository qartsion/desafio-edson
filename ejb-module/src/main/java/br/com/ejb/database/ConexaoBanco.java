package br.com.ejb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBanco
{
    private static final String URL = "jdbc:mariadb://localhost:3306/desafio_edson";
    private static final String USER = "root";
    private static final String PASSWORD = "masterkey";
    private static final Logger LOGGER = Logger.getLogger(ConexaoBanco.class.getName());

    public static Connection getConnection() 
    {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco", ex);
            throw new RuntimeException("Não foi possível conectar com banco de dados!");
        } catch (ClassNotFoundException ex) {
            System.getLogger(ConexaoBanco.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
}