package Controller;

import View.MenuView;
import View.SubMenuView;


public class MenuViewController {
    private MenuView view;

    public MenuViewController(MenuView view) {
        this.view = view;
    }
    
    public void gerenciarProdutos() {
        SubMenuView telaSubMenu = new SubMenuView();
        telaSubMenu.setVisible(true);   
    }
       
}
