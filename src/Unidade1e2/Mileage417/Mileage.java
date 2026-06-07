package Mileage417;
import java.util.Scanner;

public class Mileage{
    int kilometer;
    int liter;
    double consume;
    int totalKilometers = 0;
    int totalLiters = 0;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Mileage person1 = new Mileage();
        System.out.println("Digite os kilometros: ");
        int kilometers = sc.nextInt();
        while(kilometers != -1){
            System.out.println("Digite os litros: ");
            int liters = sc.nextInt();

            person1.setKilometer(kilometers);
            person1.setLiter(liters);
            System.out.printf("Consumo da viagem: %.2f km/l%n",person1.getConsume());
            person1.xerox();
            System.out.println("Digite os kilometros: ");
            kilometers = sc.nextInt();
        }
    }
    // Setters
    public void setKilometer(int kilometer){
        this.kilometer = kilometer;
    }
    public void setLiter(int liter){
        this.liter = liter;
    }
    // Getters
    public int getKilometer(){
        return kilometer;
    }
    public int getLiter(){
        return liter;
    }

    // functions
    public double getConsume(){
        consume = (double)kilometer / (double)liter;
        return consume;
    }
    public void xerox(){
        totalKilometers += kilometer;
        totalLiters += liter;
        System.out.printf("Total de kilometros: %d%nTotal de litros: %d%n", totalKilometers, totalLiters);
    }



}
