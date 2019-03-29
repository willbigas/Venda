/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova.interfaces;

import java.util.List;

/**
 *
 * @author Alunos
 */
public interface DaoI<E> {

    public boolean cadastrar(E obj);

    public boolean deletar(E obj);

    public List<E> listar();

    public List<E> pesquisarPorTermo(String termo);

    public List<E> buscaPorCodigoProduto(int codigo); // defasado
}
