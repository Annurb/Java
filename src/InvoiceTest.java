public class InvoiceTest{
    public static void main(String[] args){
        Invoice test1 = new Invoice();

        // Testes set
        test1.setDescription("Pão de queijo");
        test1.setNum("1234");
        test1.setQuantity(12);
        test1.setPrice(1.50);

        System.out.printf("Produto: %s", test1.getDescription());
        }
}

