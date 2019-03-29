package prova;

import prova.view.GerenciarVenda;

/**
 *
 * @author Alunos
 */
public class Prova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JanelaPrincipal();
    }

    public static void JanelaPrincipal() {
        GerenciarVenda janelaPrincipal = new GerenciarVenda();
        janelaPrincipal.setTitle("Mercado Joana");
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
    }
}
