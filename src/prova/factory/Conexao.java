/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alunos
 */
public class Conexao {
    private final static String URL = "jdbc:mysql://localhost:3306/prova";
    private final static String USER="root";
    private final static String PASS="";
    private static Connection conexao;
    
    public static Connection getConexao(){
        if(conexao==null){
            try {
                conexao = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Conectou...");
        }
        return conexao;
    }
    
}
