package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_3;

public class Main {
    public static void main(String[] args) {
        //Pedido pedido = new Pedido("PED-001", 850.00, 3.2);

        //EntregaPedido entrega = new EntregaPedido(pedido);
        //entrega.ativarSeguro();
        //entrega.ativarEntregaExpressa();
        //entrega.ativarEmbalagemPresente();

        //Checkout checkout = new Checkout();
        //checkout.imprimirResumo(pedido, entrega);
//------------------------------------------
        Pedido pedido1 = new Pedido("PED-002", 750.00, 4.2);

        ServicoEntrega entrega1 = new EntregaPadrao(pedido1);

        entrega1 = new SeguroTransporte( entrega1);

        entrega1 = new EntregaExpressa(entrega1);

        entrega1 = new EmbalagemPresente( entrega1);



        Checkout checkout1 = new Checkout();

        checkout1.imprimirResumo(pedido1, entrega1);

        /*
         * Novo requisito:
         *
         * A loja agora quer adicionar novos serviços opcionais de entrega:
         * - entrega agendada;
         * - refrigeração para produtos sensíveis;
         * - confirmação de recebimento por foto;
         * - autorização de recebimento por terceiro.
         *
         * A implementação atual não é adequada para isso, pois EntregaPedido
         * precisaria ser alterada sempre que um novo serviço adicional fosse criado.
         *
         * Refatore o projeto aplicando um padrão de projeto adequado para permitir
         * que serviços adicionais sejam combinados livremente em uma entrega
         * individual, sem criar subclasses para todas as combinações possíveis.
         */
    }
}

/**
 * Representa um pedido realizado na loja virtual.
 */
class Pedido {
    private final String numero;
    private final double valorProdutos;
    private final double pesoKg;

    /**
     * Cria um pedido.
     *
     * @param numero número identificador do pedido.
     * @param valorProdutos valor total dos produtos do pedido.
     * @param pesoKg peso total do pedido em quilogramas.
     */
    public Pedido(String numero, double valorProdutos, double pesoKg) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("O número do pedido não pode ser vazio.");
        }
        if (valorProdutos < 0) {
            throw new IllegalArgumentException("O valor dos produtos não pode ser negativo.");
        }
        if (pesoKg < 0) {
            throw new IllegalArgumentException("O peso não pode ser negativo.");
        }

        this.numero = numero;
        this.valorProdutos = valorProdutos;
        this.pesoKg = pesoKg;
    }

    public String getNumero() {String fetchData;
        return numero;
    }

    public double getValorProdutos() {
        return valorProdutos;
    }

    public double getPesoKg() {
        return pesoKg;
    }
}

/**
 * Representa a entrega de um pedido.
 *
 * <p>
 * Esta versão inicial concentra várias responsabilidades opcionais em uma única
 * classe. Esse acoplamento é intencional neste código inicial, pois representa o
 * problema de projeto a ser refatorado.
 * </p>
 */
// Design patter decorator
interface ServicoEntrega{
    public double calcularValor();
    public String getDescricao();
    Pedido getPedido();

}
class EntregaPadrao implements ServicoEntrega{
    private static final double TAXA_FIXA = 12.00;
    private static final double VALOR_POR_KG = 4.50;

    protected final Pedido pedido;
    protected ServicoEntrega servicoEntrega;
    protected double valor;
    public EntregaPadrao(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        ServicoEntrega servicoEntrega;
        this.pedido = pedido;
    }
    @Override
    public double calcularValor(){
        valor = TAXA_FIXA + pedido.getPesoKg() * VALOR_POR_KG;
        return valor;
    }
    @Override
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder("Entrega padrão");
        return descricao.toString();
    }
    @Override
    public Pedido getPedido() {
        return this.pedido;
    }

}
abstract class EntregaDecorador implements ServicoEntrega{
    protected ServicoEntrega servicoDecorado;

    public EntregaDecorador(ServicoEntrega servicoDecorado){
        if (servicoDecorado == null) {
            throw new IllegalArgumentException("O serviço de entrega não pode ser nulo.");
        }
        this.servicoDecorado = servicoDecorado;
    }

    public double calcularValor() {
        return servicoDecorado.calcularValor();
    }

    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao();
    }

    @Override
    public Pedido getPedido() {
        return servicoDecorado.getPedido();
    }
}
class SeguroTransporte extends EntregaDecorador{

    public SeguroTransporte(ServicoEntrega servicoDecorado){
        super( servicoDecorado);
    }
    @Override
    public double calcularValor(){
        return super.calcularValor() + (getPedido().getValorProdutos() * 0.02);
    }
    @Override
    public String getDescricao() {
        super.getDescricao();
        StringBuilder descricao = new StringBuilder("+ Seguro Transporte");
        return descricao.toString();
    }

}
class EmbalagemPresente extends EntregaDecorador{
    public EmbalagemPresente(ServicoEntrega servicoDecorado){
        super(servicoDecorado);
    }
    @Override
    public double calcularValor(){
        return super.calcularValor() + 9.90;
    }
    @Override
    public String getDescricao() {
        super.getDescricao();
        StringBuilder descricao = new StringBuilder("+ Embalagem Presente");
        return descricao.toString();
    }
}
class EntregaExpressa extends EntregaDecorador{
    public EntregaExpressa(ServicoEntrega servicoDecorado){
        super(servicoDecorado);
    }
    @Override
    public double calcularValor(){
        return super.calcularValor() + 18.00;
    }
    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Entrega Expressa";
    }
}
class EntregaPedido {
    private static final double TAXA_FIXA = 12.00;
    private static final double VALOR_POR_KG = 4.50;

    private final Pedido pedido;

    private boolean entregaExpressa;
    private boolean seguro;
    private boolean embalagemPresente;
    private boolean manuseioFragil;

    /**
     * Cria a entrega associada a um pedido.
     *
     * @param pedido pedido que será entregue.
     */
    public EntregaPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }

        this.pedido = pedido;
    }

    public void ativarEntregaExpressa() {
        entregaExpressa = true;
    }

    public void ativarSeguro() {
        seguro = true;
    }

    public void ativarEmbalagemPresente() {
        embalagemPresente = true;
    }

    public void ativarManuseioFragil() {
        manuseioFragil = true;
    }

    /**
     * Calcula o valor da entrega.
     *
     * @return valor total da entrega, incluindo os opcionais ativados.
     */
    public double calcularValor() {
        double valor = TAXA_FIXA + pedido.getPesoKg() * VALOR_POR_KG;

        if (entregaExpressa) {
            valor += 18.00;
        }

        if (seguro) {
            valor += pedido.getValorProdutos() * 0.02;
        }

        if (embalagemPresente) {
            valor += 9.90;
        }

        if (manuseioFragil) {
            valor += 14.00;
        }

        return valor;
    }

    /**
     * Retorna uma descrição textual da entrega.
     *
     * @return descrição da entrega e dos opcionais ativados.
     */
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder("Entrega padrão");

        if (entregaExpressa) {
            descricao.append(" + entrega expressa");
        }

        if (seguro) {
            descricao.append(" + seguro de transporte");
        }

        if (embalagemPresente) {
            descricao.append(" + embalagem para presente");
        }

        if (manuseioFragil) {
            descricao.append(" + manuseio especial para item frágil");
        }

        return descricao.toString();
    }
}

/**
 * Representa o processo de fechamento da compra.
 */
class Checkout {
    /**
     * Calcula o total a pagar pelo pedido, incluindo produtos e entrega.
     *
     * @param pedido pedido comprado.
     * @param entrega entrega escolhida.
     * @return valor total a pagar.
     */
    public double calcularTotalAPagar(Pedido pedido, ServicoEntrega entrega) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        if (entrega == null) {
            throw new IllegalArgumentException("A entrega não pode ser nula.");
        }

        return pedido.getValorProdutos() + entrega.calcularValor();
    }

    /**
     * Imprime um resumo do pedido.
     *
     * @param pedido pedido comprado.
     * @param entrega entrega escolhida.
     */
    public void imprimirResumo(Pedido pedido, ServicoEntrega entrega) {
        double valorEntrega = entrega.calcularValor();
        double total = calcularTotalAPagar(pedido, entrega);

        System.out.printf("Pedido: %s%n", pedido.getNumero());
        System.out.printf("Produtos: R$ %.2f%n", pedido.getValorProdutos());
        System.out.printf("Entrega: %s%n", entrega.getDescricao());
        System.out.printf("Valor da entrega: R$ %.2f%n", valorEntrega);
        System.out.printf("Total a pagar: R$ %.2f%n", total);
    }
}