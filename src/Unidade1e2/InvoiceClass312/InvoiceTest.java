package InvoiceClass312;

public class InvoiceTest {
    public static void main(String[] args) {
        Invoice test1 = new Invoice();

        // Testes set
        test1.setDescription("Pão de queijo");
        test1.setNum("1234");
        test1.setQuantity(12);
        test1.setPrice(1.50);

        // Testes get
        System.out.printf("Produto: %s%n", test1.getDescription());
        System.out.printf("Número do produto: %s%n", test1.getNum());
        System.out.printf("Quantidade: %d%n", test1.getQuantity());
        System.out.printf("Preço: R$ %.2f%n", test1.getPrice());

        // Teste InvoiceClass312.Invoice
        System.out.printf("Fatura do produto: %.2f", test1.getInvoiceAmount());
    }
}

