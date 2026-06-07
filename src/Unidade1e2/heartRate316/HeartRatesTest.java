package heartRate316;

import java.time.LocalDateTime;

public class HeartRatesTest {
    public static void main(String[] args){
        HeartRates test1 = new HeartRates();

        // test1 setters
        test1.setFirstName("Ricardo");
        test1.setLastName("Gorgonzola");
        test1.setDayBirth(24);
        test1.setMonthBirth(04);
        test1.setYearBirth(2001);

        //Impressões
        System.out.printf("Nome: %s %s%n", test1.getFirstName(), test1.getLastName());
        System.out.printf("Data de Nascimento: %d/%d/%d%n", test1.getDayBirth(), test1.getMounthBirth(), test1.getYearBirth());
        System.out.printf("Idade do paciente: %d%n", test1.getAge());
        System.out.printf("Frequência máxima: %d bpm%n", test1.maxFrequency());
        System.out.printf("Intervalo de frequência alvo: %d - %d bpm%n", test1.targetFrequency1(),  test1.targetFrequency2());

    }
}
