package br.com.ejb.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBanco {

    private static final String URL = "jdbc:mariadb://localhost:3306/desafio_edson";
    private static final String USER = "root";
    private static final String PASSWORD = "masterkey";
    private static final Logger LOGGER = Logger.getLogger(ConexaoBanco.class.getName());

    // 🔹 Conexão local (DriverManager)
    public static Connection getConexaoLocal() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco local", ex);
            throw new RuntimeException("Não foi possível conectar com banco de dados local!");
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Driver não encontrado", ex);
            throw new RuntimeException("Driver JDBC não encontrado!");
        }
    }

    // 🔹 Conexão gerenciada pelo servidor (WildFly)
    public static Connection getConexaoServidor() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/jdbc/DesafioDS"); // Nome JNDI configurado no WildFly
            return ds.getConnection();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro ao obter conexão do servidor", ex);
            throw new RuntimeException("Não foi possível obter conexão gerenciada!");
        }
    }
}
