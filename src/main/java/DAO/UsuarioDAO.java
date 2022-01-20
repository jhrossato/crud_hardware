package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Usuario;

public class UsuarioDAO {
    public Usuario usuario;
    public Connect conect;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String sql;
    
    public UsuarioDAO() {
        conect = new Connect();
        usuario = new Usuario();
    }
    
    public boolean autenticarUsuarioSenha() {
        sql = "SELECT * FROM usuario WHERE usuario_login = ? AND usuario_senha = ?";
        try {
            statement = conect.connection.prepareStatement(sql);
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            resultSet = statement.executeQuery();
            resultSet.next();
            usuario.setLogin(resultSet.getString(1));
            usuario.setSenha(resultSet.getString(2));
            return true;
        } catch (SQLException erro) {
            return false;
        } finally {
            conect.close();
        }
    }

}
