package prova.control;

import java.util.ArrayList;
import java.util.List;
import prova.model.Tabela;

/**
 *
 * @author Alunos
 */
public class TabelaControl {

    public static List<Tabela> criarTabela() {
        List<Tabela> listTabela = new ArrayList<>();
        Tabela t1 = new Tabela();
        t1.setCodigo(100);
        t1.setNomeProduto("Macarrão");
        t1.setValor(3.50);
        listTabela.add(t1);

        Tabela t2 = new Tabela();
        t2.setCodigo(101);
        t2.setNomeProduto("Bolacha");
        t2.setValor(4.00);
        listTabela.add(t2);

        Tabela t3 = new Tabela();
        t3.setCodigo(102);
        t3.setNomeProduto("Arroz");
        t3.setValor(3.75);
        listTabela.add(t3);

        Tabela t4 = new Tabela();
        t4.setCodigo(103);
        t4.setNomeProduto("Sorvete");
        t4.setValor(1.50);
        listTabela.add(t4);

        Tabela t5 = new Tabela();
        t5.setCodigo(104);
        t5.setNomeProduto("Pilhas");
        t5.setValor(7.30);
        listTabela.add(t5);
        return listTabela;
    }

}
