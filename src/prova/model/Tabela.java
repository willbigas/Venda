package prova.model;

/**
 *
 * @author Alunos
 */
public class Tabela {

    private Integer codigo;
    private String nomeProduto;
    private Double valor;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Tabela{" + "codigo=" + codigo + ", nomeProduto=" + nomeProduto + ", valor=" + valor + '}';
    }

}
