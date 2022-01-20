package Controller;

import DAO.UsuarioDAO;
import View.LoginView;
import View.MenuView;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class LoginViewController {
    private LoginView view;
    Connection connection;
    UsuarioDAO usuarioAutenticar;
    
    public LoginViewController(LoginView view){
        this.view = view;
    }

    public void autenticar() {
        usuarioAutenticar = new UsuarioDAO();
        usuarioAutenticar.usuario.setLogin(view.getjTextFieldUsuario().getText());
        usuarioAutenticar.usuario.setSenha(view.getjPasswordFieldSenha().getText());
        usuarioAutenticar.conect.getConnection();
        if(usuarioAutenticar.autenticarUsuarioSenha()){
            view.dispose();
            MenuView telaMenu = new MenuView();
            telaMenu.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(view, "Senha ou usuário incorreto!");
        }
    }
}
