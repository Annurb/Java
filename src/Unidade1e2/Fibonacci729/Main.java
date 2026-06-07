package Fibonacci729;

import java.util.Scanner;

class Fibonacci{
    public static void main(String[] args) {
        Fibonacci Test1 = new Fibonacci();
        Test1.calcFibonacci(4);
        System.out.printf("%n----------------------------%n");

        Scanner person = new Scanner(System.in);
        System.out.print("Digite um número para calcular Fibonacci: ");
        int i = person.nextInt();

        Fibonacci test2 = new Fibonacci();
        test2.calcFibonacci(i);

        System.out.printf("O maior valor é %.2f", test2.getMaiorValor());

    }
    private double num1 = 0;
    private double num2 = 1;
    private double maiorValor = 0;
    private void calcFibonacci(int n){

        for(int i = 1; i< n; i++){
            System.out.printf("%.2f - %.2f -", num1, num2);
            maiorValor = num2;
            num1 = num1 + num2;
            num2 = num1+ num2;
        }
    }
    private double getMaiorValor(){
        return this.maiorValor;
    }
}