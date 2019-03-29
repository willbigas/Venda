package prova.dao;

import java.sql.Connection;
import prova.factory.Conexao;

/**
 *
 * @author Alunos
 */
public class Dao {
    protected Connection conexao;

    public Dao() {
        conexao = Conexao.getConexao();
    }
    
}
