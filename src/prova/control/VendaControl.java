package prova.control;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prova.dao.VendaDao;
import prova.model.Tabela;
import prova.model.Venda;
import prova.util.Mensagem;
import prova.util.UtilFormat;
import prova.view.GerenciarVenda;

/**
 *
 * @author William
 */
public class VendaControl {

    TabelaControl tabelaControl;
    List<Venda> listVenda;
    VendaDao vendaDao;
    Venda venda;
    List<Tabela> listTabela;

    public VendaControl() {
        vendaDao = new VendaDao();
        listTabela = new ArrayList<>();
        tabelaControl = new TabelaControl();
        listTabela = tabelaControl.criarTabela();
    }

    public void listarAction() {
        listVenda = vendaDao.listar();
        atualizarJTable();
    }

    private void atualizarJTable() {
        DefaultTableModel model
                = (DefaultTableModel) GerenciarVenda.tblVenda.getModel();
        model.setNumRows(0);
        double total = 0;
        Double subtotal = 0.0;

        for (Venda v : listVenda) {
            subtotal = v.getQuantidade() * v.getValor();
            model.addRow(new Object[]{
                v.getNomeCliente(),
                v.getCodigoProduto(),
                v.getNomeProduto(),
                v.getQuantidade(),
                UtilFormat.decimalFormat(v.getValor()),
                UtilFormat.decimalFormat(subtotal)
            });

            total += subtotal;
        }
        atualizarLabelTotal(total);
    }

    private void atualizarLabelTotal(double total) {
        GerenciarVenda.lblValorTotal.setText("R$" + UtilFormat.decimalFormat(total));
    }

    public Tabela pegandoCodigoDoproduto() {
        System.out.println(listTabela);
        for (Tabela tabela : listTabela) {
            if (tabela.getCodigo().equals(Integer.valueOf(GerenciarVenda.tfCodigoProduto.getText()))) {
                return tabela;
            }
        }
        return null;

    }

    public void cadastrarAction() {
        if (validandoCamposVazios()) {
            return;
        }

        if (validandoCampoQuantidade()) {
            return;
        }

        if (validandoCampoCodigoProduto()) {
            return;
        }

        Tabela t = pegandoCodigoDoproduto();
        if (t == null) {
            JOptionPane.showMessageDialog(null, Mensagem.PRODUTO_NAO_ENCONTRADO);
            return;
        }
        venda = new Venda();
        venda.setCodigoProduto(t.getCodigo());
        venda.setNomeProduto(t.getNomeProduto());
        venda.setNomeCliente(GerenciarVenda.tfNomeCliente.getText());
        venda.setQuantidade(Integer.valueOf(GerenciarVenda.tfQuantidade.getText()));
        venda.setValor(t.getValor());

        Boolean res = vendaDao.cadastrar(venda);
        if (res) {
            JOptionPane.showMessageDialog(null, Mensagem.SUCESSO_CADASTRO);
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, Mensagem.ERRO_CADASTRO);
        }

        limpandoCampos();
        venda = null;
    }

    private void limpandoCampos() {
        GerenciarVenda.tfCodigoProduto.setText(null);
        GerenciarVenda.tfNomeCliente.setText(null);
        GerenciarVenda.tfQuantidade.setText(null);
    }

    private boolean validandoCamposVazios() throws HeadlessException {
        if (GerenciarVenda.tfCodigoProduto.getText().isEmpty()
                || GerenciarVenda.tfNomeCliente.getText().isEmpty()
                || GerenciarVenda.tfQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Mensagem.CAMPOS_VAZIOS);
            return true;
        }
        return false;
    }

    private boolean validandoCampoCodigoProduto() throws HeadlessException {
        try {
            Integer qtd = Integer.valueOf(GerenciarVenda.tfCodigoProduto.getText());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, Mensagem.CAMPO_CODIGO_INVALIDO);
            return true;
        }
        return false;
    }

    private boolean validandoCampoQuantidade() throws HeadlessException {
        try {
            Integer qtd = Integer.valueOf(GerenciarVenda.tfQuantidade.getText());
            if (qtd <= 0) {
                JOptionPane.showMessageDialog(null, Mensagem.CAMPO_QUANTIDADE_MAIORQUE1);
                return true;
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, Mensagem.CAMPO_QUANTIDADE_INVALIDO);
            return true;
        }
        return false;
    }

    private Venda getProdutoSelecionadoTable() {
        int i = GerenciarVenda.tblVenda.getSelectedRow();
        if (i >= 0) {
            return listVenda.get(i);
        }
        return null;
    }

    public void excluirAction() {
        venda = getProdutoSelecionadoTable();
        if (venda == null) {
            JOptionPane.showMessageDialog(null, Mensagem.PRODUTO_NAO_SELECIONADO);
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, Mensagem.PERGUNTA_EXCLUIR_PRODUTO + venda.getNomeProduto() + "?");
        if (i == JOptionPane.YES_OPTION) {
            if (vendaDao.deletar(venda)) {
                JOptionPane.showMessageDialog(null, Mensagem.SUCESSO_EXCLUIR);
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, Mensagem.ERRO_EXCLUIR);
            }
        }
        venda = null;
    }

    public void salvarAction() {
        if (venda == null) {
            cadastrarAction();
        } else {
            return;
        }
    }

    public void pesquisarAction() {
        listVenda = vendaDao.pesquisarPorTermo(GerenciarVenda.tfPesquisar.getText());
        atualizarJTable();
    }

}
