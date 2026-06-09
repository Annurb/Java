package Unidade4_Praticas_de_desenvolvimento_de_software.Atividade_4;

public class Main {
    public static void main(String[] args) {

        //Refatoracao
        Pedido pedido = new Pedido("PED-001", 850.00);



        //Interface da abstract factory é instanciada
        GatewayPagamento gateway = new GatewayCielo();

        Checkout checkout = new Checkout(gateway);

        checkout.finalizarCompra(pedido);

        /*
         * Novo requisito:
         *
         * A loja agora quer adicionar novos provedores de pagamento:
         * - Rede;
         * - Stripe;
         * - Adyen.
         *
         * Cada provedor terá suas próprias classes para autorizar, capturar e gerar
         * comprovantes. A implementação atual não é adequada para isso, pois Checkout
         * precisaria ser alterada sempre que um novo provedor fosse criado.
         *
         * Refatore o projeto aplicando um padrão de projeto adequado para permitir
         * que Checkout trabalhe com uma família consistente de objetos de pagamento
         * sem conhecer suas classes concretas.@startuml

         */
    }
}

/**
 * Representa os provedores de pagamento suportados pela implementação inicial.
 */

interface GatewayPagamento{
    public AutorizadorPagamento criarAutorizador();
    public CapturadorPagamento criarCapturador();
    public GeradorComprovantePagamento criarGeradorComprovante();
    public String getNome();

}
interface GeradorComprovantePagamento{
    public String gerar(Pedido pedido,String  codigoCaptura );
}
interface AutorizadorPagamento{
    public String autorizar(Pedido pedido);
}
interface CapturadorPagamento{
    public String capturar(String codigoAutorizacao);
}

class GatewayMercadoPago implements GatewayPagamento{
    public AutorizadorPagamento criarAutorizador(){
        return new MercadoPagoAutorizador();
    }
    public CapturadorPagamento criarCapturador(){
        return new MercadoPagoCapturador();
    }
    public GeradorComprovantePagamento criarGeradorComprovante(){
        return new MercadoPagoComprovante() ;
    }
    public String getNome(){
        return "Mercado Pago";
    }
}
class GatewayCielo implements GatewayPagamento{
    public AutorizadorPagamento criarAutorizador(){
        return new CieloAutorizador();
    }
    public CapturadorPagamento criarCapturador(){
        return new CieloCapturador();
    }
    public GeradorComprovantePagamento criarGeradorComprovante(){
        return new CieloComprovante() ;
    }
    public String getNome(){
        return "Cielo";
    }
}



/**
 * Representa um pedido realizado na loja virtual.
 */
class Pedido {
    private final String numero;
    private final double valorTotal;

    /**
     * Cria um pedido.
     *
     * @param numero número identificador do pedido.
     * @param valorTotal valor total do pedido.
     */
    public Pedido(String numero, double valorTotal) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("O número do pedido não pode ser vazio.");
        }
        if (valorTotal < 0) {
            throw new IllegalArgumentException("O valor total não pode ser negativo.");
        }

        this.numero = numero;
        this.valorTotal = valorTotal;
    }

    public String getNumero() {
        return numero;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

/**
 * Representa o processo de checkout da loja.
 *
 * <p>
 * Esta versão inicial conhece diretamente as classes concretas de cada provedor.
 * Esse acoplamento é intencional neste código inicial, pois representa o problema
 * de projeto a ser refatorado.
 * </p>
 */
class Checkout {
    private GatewayPagamento gatewayPagamento;

    /**
     * Cria um checkout configurado para um provedor de pagamento.
     *
     * @param
     */
    public Checkout(GatewayPagamento gatewayPagamento) {
        if (gatewayPagamento == null) {
            throw new IllegalArgumentException("O provedor não pode ser nulo.");
        }

        this.gatewayPagamento = gatewayPagamento;
    }

    /**
     * Finaliza a compra processando autorização, captura e comprovante.
     *
     * @param pedido pedido a ser pago.
     */
    public void finalizarCompra(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }

        String codigoAutorizacao;
        String codigoCaptura;
        String comprovante;

        GeradorComprovantePagamento geradorComprovante  = gatewayPagamento.criarGeradorComprovante();
        AutorizadorPagamento autorizador = gatewayPagamento.criarAutorizador();
        CapturadorPagamento capturador  = gatewayPagamento.criarCapturador() ;

        codigoAutorizacao = autorizador.autorizar(pedido);
        codigoCaptura = capturador.capturar(codigoAutorizacao);
        comprovante = geradorComprovante.gerar(pedido, codigoCaptura);

        System.out.println("Compra finalizada.");
        System.out.println(comprovante);
    }
}

/**
 * Autoriza pagamentos usando a Cielo.
 */
class CieloAutorizador implements AutorizadorPagamento{
    /**
     * Autoriza um pedido.
     *
     * @param pedido pedido a ser autorizado.
     * @return código de autorização.
     */
    public String autorizar(Pedido pedido) {
        System.out.printf("[CIELO] Autorizando pagamento do pedido %s no valor de R$ %.2f%n",
                pedido.getNumero(), pedido.getValorTotal());
        return "CIELO-AUT-" + pedido.getNumero();
    }
}

/**
 * Captura pagamentos autorizados usando a Cielo.
 */
class CieloCapturador implements CapturadorPagamento{
    /**
     * Captura uma autorização.
     *
     * @param codigoAutorizacao código de autorização.
     * @return código de captura.
     */
    public String capturar(String codigoAutorizacao) {
        System.out.printf("[CIELO] Capturando autorização %s%n", codigoAutorizacao);
        return "CIELO-CAP-" + codigoAutorizacao;
    }
}

/**
 * Gera comprovantes de pagamento da Cielo.
 */
class CieloComprovante implements GeradorComprovantePagamento{
    /**
     * Gera o comprovante do pagamento capturado.
     *
     * @param pedido pedido pago.
     * @param codigoCaptura código de captura.
     * @return texto do comprovante.
     */
    public String gerar(Pedido pedido, String codigoCaptura) {
        return String.format("[CIELO] Comprovante do pedido %s | Captura: %s",
                pedido.getNumero(), codigoCaptura);
    }
}

/**
 * Autoriza pagamentos usando Mercado Pago.
 */
class MercadoPagoAutorizador implements AutorizadorPagamento{
    /**
     * Autoriza um pedido.
     *
     * @param pedido pedido a ser autorizado.
     * @return código de autorização.
     */
    public String autorizar(Pedido pedido) {
        System.out.printf("[MERCADO PAGO] Autorizando pagamento do pedido %s no valor de R$ %.2f%n",
                pedido.getNumero(), pedido.getValorTotal());
        return "MP-AUT-" + pedido.getNumero();
    }
}

/**
 * Captura pagamentos autorizados usando Mercado Pago.
 */
class MercadoPagoCapturador implements CapturadorPagamento{
    /**
     * Captura uma autorização.
     *
     * @param codigoAutorizacao código de autorização.
     * @return código de captura.
     */
    public String capturar(String codigoAutorizacao) {
        System.out.printf("[MERCADO PAGO] Capturando autorização %s%n", codigoAutorizacao);
        return "MP-CAP-" + codigoAutorizacao;
    }
}

/**
 * Gera comprovantes de pagamento do Mercado Pago.
 */
class MercadoPagoComprovante implements GeradorComprovantePagamento{
    /**
     * Gera o comprovante do pagamento capturado.
     *
     * @param pedido pedido pago.
     * @param codigoCaptura código de captura.
     * @return texto do comprovante.
     */
    public String gerar(Pedido pedido, String codigoCaptura) {
        return String.format("[MERCADO PAGO] Comprovante do pedido %s | Captura: %s",
                pedido.getNumero(), codigoCaptura);
    }
}