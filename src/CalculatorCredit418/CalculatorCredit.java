package CalculatorCredit418;

public class CalculatorCredit{
    String numberAccount;
    int balance;
    int itens;
    int credits;
    int limit;
    int newSald;

    public static void main(String[] args){
        CalculatorCredit person1 = new CalculatorCredit();
        person1.setNumberAccount("11111");
        person1.setBalance(2000);
        person1.setLimit(200);
        person1.setItens(312);
        person1.setCredits(200);
        System.out.printf("%d%n", person1. calcNewBalance());
    }
    // Setters
    private void setNumberAccount(String numberAccount){
        this.numberAccount = numberAccount;
    }
    private void setBalance(int balance){
        this.balance = balance;
    }
    private void setItens(int itens){
        this.itens = itens;
    }
    private void setCredits(int credits){
        this.credits = credits;
    }
    private void setLimit(int limit){
        this.limit = limit;
    }

    //Getters
    private String getNumberAccount(){
        return numberAccount;
    }
    private int getBalance(){
        return balance;
    }
    private int getItens(){
        return itens;
    }
    private int getCredits(){
        return credits;
    }
    private int getLimit(){
        return limit;
    }
    // Functions
    private int calcNewBalance(){
        newSald = balance + itens - credits;
        if(newSald > limit){
            System.out.println("Limite de crédito excedido");
        }
        return newSald;
    }
}