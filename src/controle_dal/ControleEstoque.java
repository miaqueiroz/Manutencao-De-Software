package controle_dal;

import bean.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.ModuloConexao;

/**
 *
 * @author gabri
 */
public class ControleEstoque {

    private final Connection connection;
    
    public ControleEstoque(){
        this.connection = new ModuloConexao().Conector();
    }
    
    public void entradaProduto(String nomeProd, int quantidade){
        String sqlSelectEst = "select * from tbestoque where id_produto = ?";
        String sql = "insert into tbestoque (quantidade, id_produto) values (?,?);";
        String sqlSelectProd = "select * from tbproduto where nome = ?";
        String sqlUpdateQuantidade = "update tbestoque set quantidade = ?";
        int idProd = 0;
        int quant = 0;
        
        try {
            PreparedStatement pst = this.connection.prepareStatement(sqlSelectProd);
            pst.setString(1, nomeProd);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
                idProd = rs.getInt("id_produto");
            
            pst = this.connection.prepareStatement(sqlSelectEst);
            pst.setInt(1, idProd);
            rs = pst.executeQuery();
            
            if(!rs.next()){
                pst = this.connection.prepareStatement(sql);
                pst.setInt(1, quantidade);
                pst.setInt(2, idProd);
                pst.executeUpdate();
            }else{
                quant = rs.getInt("quantidade") + quantidade;
                pst = this.connection.prepareStatement(sqlUpdateQuantidade);
                pst.setInt(1, quant);
                pst.executeUpdate();
            }
            
            pst.close();
            rs.close();

            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso");
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleProdutos DAO");
            System.err.println("\nERRO NO MÉTODO entradaProduto");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);              
        }
    }
    
    public int encontrarIdProduto(String nome){
        String sql = "select * from tbproduto where nome = ?";
        int idProduto = 0;

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();
           
            if(!rs.next()){
                System.out.println("rs falso");
            }
            
            idProduto = rs.getInt("id_produto");
            rs.close();
            pst.close();
            return idProduto;
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleProdutos DAO");
            System.err.println("\nERRO NO MÉTODO encontrarIdProduto");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);  
        }
    }    
    
    public ArrayList<Estoque> consultarEstoque(){
        String sql = "select * from tbestoque inner join tbproduto on tbestoque.id_Produto = tbproduto.id_produto";

        try {
            ArrayList<Estoque> vetEstoque = new ArrayList<>();
            PreparedStatement pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Estoque e = new Estoque();
                e.setId_estoque(rs.getInt("id_estoque"));
                e.setQuantidade(rs.getInt("quantidade"));
                e.setId_produto(rs.getInt("id_produto"));
                e.setNome(rs.getString("nome"));
                vetEstoque.add(e);
            }
            rs.close();
            pst.close();
            
            return vetEstoque;
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleEstoque - DAO");
            System.err.println("\nERRO NO MÉTODO consultarEstoque");
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            System.err.println("\n============================================");
            throw new RuntimeException(e);              
        }
    }

    public String encontrarNomeProduto(int id){
        String sql = "select * from tbproduto where id_produto = ?";
        String nome;
        String id_string = id + "";
        
        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, id_string);
            ResultSet rs = pst.executeQuery();
           
            if(!rs.next()){
                System.out.println("rs falso");
            }
            
            nome = rs.getString("nome");
            rs.close();
            pst.close();
            return nome;
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleProdutos DAO");
            System.err.println("\nERRO NO MÉTODO encontrarIdProduto");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);  
        }
    }    
    
    public void saidaProduto(String nome, int saida){
        String sqlBuscaEst = "select * from tbestoque where id_produto = ?";
        String sqlAtualizaEst = "update tbestoque set quantidade = ? where id_produto = ?";
        String sqlBuscaProd = "select * from tbproduto where nome = ?";
        int idProd = 0;
        int quantidade = 0;
        try {
            PreparedStatement pst = this.connection.prepareStatement(sqlBuscaProd);
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
                idProd = rs.getInt("id_produto");
            
            pst = this.connection.prepareStatement(sqlBuscaEst);
            pst.setInt(1, idProd);
            rs = pst.executeQuery();
            
            if(rs.next())
                quantidade = rs.getInt("quantidade");
            
            if(saida < quantidade)
                quantidade = quantidade - saida;
            else{
                JOptionPane.showMessageDialog(null, "Saída maior que quantidade em estoque.");
                throw new RuntimeException();
            }
            
            pst = this.connection.prepareStatement(sqlAtualizaEst);
            
            pst.setInt(1, quantidade);
            pst.setInt(2, idProd);
            pst.executeUpdate();
            
            rs.close();
            pst.close();
            
            JOptionPane.showMessageDialog(null, "Saída realizada com sucesso.");
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleProdutos DAO");
            System.err.println("\nERRO NO MÉTODO saidaProduto");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);  
        }
    }
}
