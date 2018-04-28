package controle_dal;

import bean.Usuarios;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import telas.TelaPrincipal;
import util.ModuloConexao;

/**
 *
 * @author gabri
 */
public class ControleUsuario {

    private final Connection connection;

    public ControleUsuario() {
        this.connection = new ModuloConexao().Conector();

    }

    public void logar(String log, String sen) {
        String sql = "select * from tbusuario where login = ? and senha = ?";

        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, log);
            pst.setString(2, sen);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                TelaPrincipal principal = new TelaPrincipal(nome);
                principal.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "usuario e/ou senha inválido");
            }
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleUsuario DAO");
            System.err.println("\nERRO NO MÉTODO Logar");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);
        }
    }

    public void cadastrarUsuario(Usuarios user) {
        String sql = "insert into tbusuario (nome, fone, login, senha, endereco) values (?,?,?,?,?);";
        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);

            pst.setString(1, user.getNome());
            pst.setString(2, user.getFone());
            pst.setString(3, user.getLogin());
            pst.setString(4, user.getSenha());
            pst.setString(5, user.getEndereco());

            pst.execute();
            pst.close();

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleUsuario DAO");
            System.err.println("\nERRO NO MÉTODO cadastrarUsuario");
            System.err.println("\nCAUSA: " + e.getCause());
            System.err.println("\nMENSAGEM " + e.getMessage());
            e.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Usuarios> buscarUsuario() {
        String sql = "select * from tbusuario;";
        try {
            ArrayList<Usuarios> vetUsuario = new ArrayList<Usuarios>();
            PreparedStatement pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setNome(rs.getString("nome"));
                u.setFone(rs.getString("fone"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setEndereco(rs.getString("endereco"));
                vetUsuario.add(u);
            }
            rs.close();
            pst.close();
            
            return vetUsuario;
            
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleUsuario - DAO");
            System.err.println("\nERRO NO MÉTODO buscarUsuario");
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            System.err.println("\n============================================");
            throw new RuntimeException(e);
        }
    }
    
    public void deletarUsuario(String nome){
        String sql = "delete from tbusuario where nome = ?";
        try {
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso !");
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE ControleUsuario - DAO");
            System.err.println("\nERRO NO MÉTODO deletarUsuario");
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            System.err.println("\n============================================");
            throw new RuntimeException(e);            
        }
    }
}
