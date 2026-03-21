public class Invoice {
    private String num;
    private String description;
    private int quantity;
    private double price;

    public static void Main(){

    }
    // Getters:
    public String getNum(){
        return num;
    }
    public String description(){
        return description;
    }

    // Setters
    public void setNum(String num){
        this.num = num;
    }
    public void setDescripton(String description){
        this.description = description;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setPrice(double price){
        this.price = price;
    }
}
