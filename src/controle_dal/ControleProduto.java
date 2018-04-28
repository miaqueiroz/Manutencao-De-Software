package controle_dal;

import bean.Produtos;
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
public class ControleProduto {
    //Produtos produto = new Produtos();
    private final Connection connection;
    
    public ControleProduto(){
        this.connection = new ModuloConexao().Conector();
    }
    
    public void inserir(Produtos produto){
        String sql = "insert into tbProduto (nome, descricao, codBarras, categoria) values (?,?,?,?);";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getCodBarras());
            stmt.setString(4, produto.getCategoria()); 

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleProdutos DAO");
            System.err.println("\nERRO NO MÉTODO inserir");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);            
        }
    }

    
    public ArrayList<Produtos> buscarProduto(){
        String sql = "select * from tbProduto;";
        try {
            ArrayList<Produtos> vetProd = new ArrayList<>();
            PreparedStatement pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Produtos p = new Produtos();
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                //p.setCodBarras(Integer.parseInt(rs.getString("codBarras")));
                p.setCodBarras(rs.getInt("codBarras"));
                p.setCategoria(rs.getString("categoria"));
                
                vetProd.add(p);
            }
            rs.close();
            pst.close();

            return vetProd;
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleProduto - DAO");
            System.err.println("\nERRO NO MÉTODO buscarProduto");
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            System.err.println("\n============================================");
            throw new RuntimeException(e);            
        }
    }    

}
