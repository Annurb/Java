package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_5;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
         * Novo requisito:
         *
         * A loja agora quer adicionar novos formatos de relatório:
         * - TXT;
         * - Markdown;
         * - JSON.
         *
         * Todos os formatos seguem a mesma sequência geral:
         * validar vendas, iniciar documento, gerar cabeçalho, gerar linhas,
         * gerar total e finalizar documento.
         *
         * A implementação atual não é adequada para isso, pois cada nova classe
         * tende a copiar a mesma sequência de geração, mudando apenas alguns
         * detalhes de formatação.
         *
         * Refatore o projeto aplicando um padrão de projeto adequado para manter
         * a sequência geral em um único ponto e permitir que cada formato implemente
         * somente as etapas que variam.
         */
        List<Venda> vendas = List.of(

                new Venda("V001", "Ana", 250.00),

                new Venda("V002", "Bruno", 180.50)

        );



        GeradorRelatorioVendas relatorio = new GeradorRelatorioHtml();

        System.out.println(relatorio.gerar(vendas));
    }
}

/**
 * Representa uma venda realizada pela loja virtual.
 */
abstract class GeradorRelatorioVendas{
    public String gerar(List<Venda> vendas) {
        validar(vendas);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append(gerarInicioDocumento());
        relatorio.append(gerarCabecalho());

        double total = 0.0;

        for (Venda venda : vendas) {
            relatorio.append(gerarLinha(venda));
            total += venda.getValor();
        }

        relatorio.append(gerarTotal(total));
        relatorio.append(gerarFimDocumento());

        return relatorio.toString();
    }

    protected void validar(List<Venda> vendas) {
        if (vendas == null) {
            throw new IllegalArgumentException("A lista de vendas não pode ser nula.");
        }
        if (vendas.isEmpty()) {
            throw new IllegalArgumentException("A lista de vendas não pode ser vazia.");
        }
    }
    protected String gerarInicioDocumento(){return "";}
    protected String gerarFimDocumento(){return "";}
    abstract protected String gerarCabecalho();
    abstract protected String gerarLinha(Venda venda);

    protected abstract String gerarTotal(double total);
}
class Venda {
    private final String codigo;
    private final String cliente;
    private final double valor;

    /**
     * Cria uma venda.
     *
     * @param codigo código da venda.
     * @param cliente nome do cliente.
     * @param valor valor da venda.
     */
    public Venda(String codigo, String cliente, double valor) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código da venda não pode ser vazio.");
        }
        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("O valor da venda não pode ser negativo.");
        }

        this.codigo = codigo;
        this.cliente = cliente;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }
}

/**
 * Gera relatórios de vendas em formato CSV.
 *
 * <p>
 * Esta versão inicial contém toda a sequência de geração dentro da própria classe,
 * duplicando a mesma estrutura geral usada por outros formatos.
 * </p>
 */
class GeradorRelatorioCsv extends GeradorRelatorioVendas{
    /**
     * Gera o relatório de vendas em formato CSV.
     *
     * @param
     * @return relatório gerado.
     */


    protected String gerarCabecalho() {
        return "codigo;cliente;valor" + System.lineSeparator();
    }

    protected String gerarLinha(Venda venda) {
        return venda.getCodigo()
                + ";"
                + venda.getCliente()
                + ";"
                + String.format("%.2f", venda.getValor())
                + System.lineSeparator();
    }

    protected String gerarTotal(double total) {
        return "TOTAL;;" + String.format("%.2f", total) + System.lineSeparator();
    }
}

/**
 * Gera relatórios de vendas em formato HTML.
 *
 * <p>
 * Esta versão inicial contém uma sequência de geração muito semelhante à versão
 * CSV, diferindo apenas na forma de representar cada parte do documento.
 * </p>
 */
class GeradorRelatorioHtml extends GeradorRelatorioVendas{
    /**
     * Gera o relatório de vendas em formato HTML.
     *
     * @param
     * @return relatório gerado.
     */


    protected String gerarInicioDocumento() {
        return "<table>" + System.lineSeparator();
    }

    protected String gerarCabecalho() {
        return "  <tr><th>Código</th><th>Cliente</th><th>Valor</th></tr>" + System.lineSeparator();
    }

    protected String gerarLinha(Venda venda) {
        return "  <tr><td>"
                + venda.getCodigo()
                + "</td><td>"
                + venda.getCliente()
                + "</td><td>R$ "
                + String.format("%.2f", venda.getValor())
                + "</td></tr>"
                + System.lineSeparator();
    }

    protected String gerarTotal(double total) {
        return "  <tr><td colspan=\"2\">TOTAL</td><td>R$ "
                + String.format("%.2f", total)
                + "</td></tr>"
                + System.lineSeparator();
    }

    protected String gerarFimDocumento() {
        return "</table>" + System.lineSeparator();
    }
}
class GeradorRelatorioMarkdown extends GeradorRelatorioVendas{
     protected String gerarCabecalho(){
         return " | codigo | cliente | valor |" + System.lineSeparator()+
                 "| --- | --- | --- |" + System.lineSeparator();
     }
     protected String gerarLinha(Venda venda){
        return " | " + venda.getCodigo() + " | "+ venda.getCliente() + "| R$ " + String.format("%.2f", venda.getValor()) + " |"+ System.lineSeparator();
     }

    protected String gerarTotal(double total){
        return "  | **TOTAL** | | **R$ "
                + String.format("%.2f", total)
                + "** |"
                + System.lineSeparator();
    }
}