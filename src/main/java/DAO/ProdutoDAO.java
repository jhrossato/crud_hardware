package DAO;

import Model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    public Connect connect;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String sql;
    public Produto produto;
    
    public ProdutoDAO(){
        connect = new Connect();
        produto = new Produto();
    }
    
    public boolean inserirProduto(){
        String sql = "INSERT INTO produto(tipo, codigoUniversal, marca, modelo, descricao, preco, quantidade) VALUES (?,?,?,?,?,?,?)";
        try{
            connect.getConnection();
            statement = connect.connection.prepareStatement(sql);
            statement.setString(1, produto.getTipo());
            statement.setString(2, produto.getCodigoUniversal());
            statement.setString(3, produto.getMarca());
            statement.setString(4, produto.getModelo());
            statement.setString(5, produto.getDescricao());
            statement.setFloat(6, produto.getPreco());
            statement.setInt(7, produto.getQuantidade());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!");
            return false;
        } finally {
            connect.close();
        }
    }
    
    public boolean buscarProduto() {
        sql = "SELECT * FROM produto WHERE id = ?";
        try {
            connect.getConnection();
            statement = connect.connection.prepareStatement(sql);
            statement.setInt(1, produto.getId());
            resultSet = statement.executeQuery();
            resultSet.next();
            produto.setId(resultSet.getInt(1));
            produto.setTipo(resultSet.getString(2));
            produto.setCodigoUniversal(resultSet.getString(3));
            produto.setMarca(resultSet.getString(4));
            produto.setModelo(resultSet.getString(5));
            produto.setDescricao(resultSet.getString(6));
            produto.setPreco(resultSet.getFloat(7));
            produto.setQuantidade(resultSet.getInt(8));
            return true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar!");
            return false;
        } finally {
            connect.close();
        }
    }
    
    public List<Produto> listarTodosProdutos(){
        String sql = "SELECT * FROM produto";
        List<Produto> retorno = new ArrayList<>();
        try{
            connect.getConnection();
            statement = connect.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                produto = new Produto();
                produto.setId(Integer.parseInt(resultSet.getString("id")));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setCodigoUniversal(resultSet.getString("codigoUniversal"));
                produto.setMarca(resultSet.getString("marca"));
                produto.setModelo(resultSet.getString("modelo"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(Float.parseFloat(resultSet.getString("preco")));
                produto.setQuantidade(Integer.parseInt(resultSet.getString("quantidade"))); 
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar!");
        } finally {
            connect.close();
        }
        return retorno;
    }
    
    public List<Produto> listarProdutoEspecifico(){
        String sql = "SELECT * FROM produto WHERE tipo=?";
        List<Produto> retorno = new ArrayList<>();
        try{
            statement = connect.connection.prepareStatement(sql);
            statement.setString(1, produto.getTipo());
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                produto.setTipo(resultSet.getString("tipo"));
                produto.setCodigoUniversal(resultSet.getString("codigoUniversal"));
                produto.setMarca(resultSet.getString("marca"));
                produto.setModelo(resultSet.getString("modelo"));
                produto.setPreco(Float.parseFloat(resultSet.getString("preco")));
                produto.setDescricao(resultSet.getString("descricao"));               
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar!");  
        } finally {
            connect.close();
        }
        return retorno;
    }
    
    public boolean alterarProduto(){
        String sql = "UPDATE produto SET codigoUniversal=?, marca=?, modelo=?, preco=?,"+
        "descricao=? WHERE tipo=?";
        try{
            statement = connect.connection.prepareStatement(sql);
            statement.setString(1, produto.getCodigoUniversal());
            statement.setString(2, produto.getMarca());
            statement.setString(3, produto.getModelo());
            statement.setFloat(4, produto.getPreco());
            statement.setString(5, produto.getDescricao());
            statement.setString(6, produto.getTipo());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar!");
            return false;            
        } finally {
            connect.close();
        }
    }
    
    public boolean removerProduto(String tipo,String codigoUniversal){
        String sql = "DELETE FROM produto WHERE tipo=? AND codigoUniversal=?";
        try{
            statement = connect.connection.prepareStatement(sql);
            statement.setString(1, tipo);
            statement.setString(2, codigoUniversal);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            return false;            
        } finally {
            connect.close();
        }
    }
    
}
