public class Invoice {
    private String num;
    private String description;
    private int quantity;
    private double price;
    private double invoice;

    // Getters:
    public String getNum(){
        return num;
    }
    public String getDescription(){
        return description;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }

    // Setters
    public void setNum(String num){
        this.num = num;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setPrice(double price){
        this.price = price;
    }

    // getInvoiceAmount que calcula o valor de fatura (isto é, multiplica a quantidade pelo
    //preço por item) e depois retorna esse valor como double.
    public double getInvoiceAmount(){
        invoice = quantity*price;
        return invoice;
    }
}
