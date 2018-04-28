/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author gabri
 */
import java.sql.*;

public class ModuloConexao {
    //método responsável por estabelecer conexão com o BD
    public static Connection Conector() {
        java.sql.Connection conexao = null;
        //a linha abaixo "chama" o driver 
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando informações referente ao banco
        //String url = "jdbc:mysql://localhost:3306/dbinfox";
        String url = "jdbc:mysql://localhost/farmacia1?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "wi-fi147";
        //Estabelecendo a conexão com o banco
        try {
            //Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
            System.err.println("\n============================================");
            System.err.println("\nCLASSE VISITAS CONECTA BANCO");
            System.err.println("\nERRO NO MÉTODO GET CONNECTION");
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            System.err.println("\n============================================");
            throw new RuntimeException(e);
        }
    }
}
