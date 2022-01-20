package DAO;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {
    
    public Connection connection = null;
    private final String DRIVER = "org.postgresql.Driver";
    private final String DBNAME = "vendaHardware";
    private final String URL = "jdbc:postgresql://localhost:5432/" + DBNAME;
    private final String LOGIN = "postgres";
    private final String SENHA = "root";
    
    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return connection;
    }
    
    public void close() {
        try {
            connection.close();
            System.out.println("Desconectou");
        } catch (SQLException erro) {
        }
    }
}
