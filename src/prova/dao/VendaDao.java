/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prova.interfaces.DaoI;
import prova.model.Venda;

/**
 *
 * @author Alunos
 */
public class VendaDao extends Dao implements DaoI<Venda> {

    public VendaDao() {
        //Contrutor da super classe Dao. Faz a conexão.
        super();
    }

    @Override
    public List<Venda> listar() {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("SELECT "
                    + "id, codigoProduto, nomeProduto, valor , quantidade , nomeCliente FROM vendas ORDER BY id DESC");
            ResultSet result = stmt.executeQuery();
            List<Venda> lista = new ArrayList<>();
            while (result.next()) {
                Venda v = new Venda();
                v.setId(result.getInt("id"));
                v.setCodigoProduto(result.getInt("codigoProduto"));
                v.setNomeProduto(result.getString("nomeProduto"));
                v.setValor(result.getDouble("valor"));
                v.setQuantidade(result.getInt("quantidade"));
                v.setNomeCliente(result.getString("nomeCliente"));
                lista.add(v);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Método para cadastrar um produto no banco de dados
     * <br>Retorna id do produto cadastrado
     * <br><b>Retorna 0 (zero) se houver erro</b>
     *
     * @param obj
     * @return int
     */
    @Override
    public boolean cadastrar(Venda obj) {
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(
                    "INSERT INTO vendas(codigoProduto, nomeProduto, valor , quantidade , nomeCliente)"
                    + " VALUES(?, ?, ? , ? , ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getCodigoProduto());
            stmt.setString(2, obj.getNomeProduto());
            stmt.setDouble(3, obj.getValor());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setString(5, obj.getNomeCliente());
            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Venda obj) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + " DELETE from vendas"
                    + " WHERE id = ? ");
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Venda> pesquisarPorTermo(String termo) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(""
                    + "select id, codigoProduto, nomeProduto, valor , quantidade , nomeCliente FROM vendas"
                    + " WHERE "
                    + " (codigoProduto = ? OR nomeCliente LIKE ?) ");
            stmt.setString(1, termo);
            stmt.setString(2, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Venda> lista = new ArrayList<>();
            while (result.next()) {
                Venda v = new Venda();
                v.setId(result.getInt("id"));
                v.setCodigoProduto(result.getInt("codigoProduto"));
                v.setNomeProduto(result.getString("nomeProduto"));
                v.setValor(result.getDouble("valor"));
                v.setQuantidade(result.getInt("quantidade"));
                v.setNomeCliente(result.getString("nomeCliente"));
                lista.add(v);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Venda> buscaPorCodigoProduto(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
