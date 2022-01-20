package Controller;

import DAO.ProdutoDAO;
import View.SubMenuView;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class SubMenuViewController {
    private final SubMenuView view;
    ProdutoDAO produtos;
    JTable tabela = new JTable();
    
    
    public SubMenuViewController(SubMenuView view) {
        this.view = view;
    }
        
    public void inserirSubMenuView(){
        produtos = new ProdutoDAO();
        if(GetSubMenuDados()){
            if(produtos.inserirProduto()){
                LimpaSubMenuDados();
                JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
            }
        }
    }
    
    public void buscarSubMenuView(){
        produtos = new ProdutoDAO();
        if(GetId()){
            if(produtos.buscarProduto()){
                SetSubMenuDados();
                JOptionPane.showMessageDialog(null, "Produto encontrado!");
            }
        }
    }
   
    
    private boolean GetSubMenuDados(){
        if(!"Selecione".equals(view.getjComboBox()))
            produtos.produto.setTipo(view.getjComboBox());
        else { 
            JOptionPane.showMessageDialog(null, "Selecione um produto");
            return false;
        }
        produtos.produto.setCodigoUniversal(view.getjTextFieldCodigoUniversal().getText());
        produtos.produto.setMarca(view.getjTextFieldMarca().getText());
        produtos.produto.setModelo(view.getjTextFieldModelo().getText());
        produtos.produto.setDescricao(view.getjTextFieldDescricao().getText());
        produtos.produto.setPreco(Float.parseFloat(view.getjTextFieldPreco().getText()));
        produtos.produto.setQuantidade(Integer.parseInt(view.getjTextFieldQuantidade().getText()));
        return true;
    }
    
    private void LimpaSubMenuDados(){
        view.getjTextFieldCodigoUniversal().setText("");
        view.getjTextFieldMarca().setText("");
        view.getjTextFieldModelo().setText("");
        view.getjTextFieldDescricao().setText("");
        view.getjTextFieldPreco().setText("");
        view.getjTextFieldQuantidade().setText("");
    }
     
    private boolean GetId(){
        if(!"".equals(view.getjTextFieldId().getText())){
            produtos.produto.setId(Integer.parseInt(view.getjTextFieldId().getText()));
            return true;
        }
        else
            return false;
    }
    
    private void SetSubMenuDados(){
        valorComboBox();
        view.getjTextFieldCodigoUniversal().setText(produtos.produto.getCodigoUniversal());
        view.getjTextFieldMarca().setText(produtos.produto.getMarca());
        view.getjTextFieldModelo().setText(produtos.produto.getModelo());
        view.getjTextFieldDescricao().setText(produtos.produto.getDescricao());
        view.getjTextFieldPreco().setText(String.valueOf(produtos.produto.getPreco()));
        view.getjTextFieldQuantidade().setText(String.valueOf(produtos.produto.getQuantidade()));
    }
    
    private void valorComboBox(){
        String valorComboBox = produtos.produto.getTipo();
        switch(valorComboBox){
            case "Fonte":
                view.setjComboBox(1);
                break;
            case "HD/SSD":
                view.setjComboBox(2);
                break;
            case "Memória":
                view.setjComboBox(3);
                break;
            case "Placa de Vídeo":
                view.setjComboBox(4);
                break;
            case "Placa Mãe":
                view.setjComboBox(5);
                break;
            case "Processador":
                view.setjComboBox(6);
                break;
        } 
    }
}