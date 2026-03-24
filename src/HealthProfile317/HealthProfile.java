package HealthProfile317;
import java.time.LocalDateTime;

public class HealthProfile{
    private String firstName;
    private String lastName;
    private String sex;
    private int day;
    private int month;
    private int year;
    private double height;
    private double weight;
    private int age;
    private int maxFrequency;
    private int targetFrequency1;
    private int targetFrequency2;
    private double imc;

    public HealthProfile(String firstName, String lastName, String sex, int day, int month, int year, double height, double weight){
      this.firstName = firstName;
      this.lastName = lastName;
      this.sex = sex;
      this.day = day;
      this.month = month;
      this.year = year;
      this.height = height;
      this.weight = weight;
    }
    // Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setDay(int day){
        this.day = day;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    // Getters
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getSex(){
        return this.sex;
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public double getHeight(){
        return height;
    }
    public double weight(){
        return weight;
    }
    public void getInfo(){
        getAge();
        getMaxFrequency();
        getTargetFrequency1();
        getTargetFrequency2();
        getImc();
        System.out.printf("Nome do paciente: %s%s%nSexo: %s%n", this.firstName, this.lastName, this.sex);
        System.out.printf("Data de nascimento: %d/%d/%d%nIdade: %d%n", this.day, this.month, this.year, this.age);
        System.out.printf("IMC: %f%nFrequência cardíada alvo: %d%nIntervalo de Frequência cardíaca: %d - %d%n", this.imc, this.maxFrequency, this.targetFrequency1, this.targetFrequency2);
    }

    // Calculo da idade
    public int getAge(){
        LocalDateTime now = LocalDateTime.now();
        int age1Month;
        int age1Year;

        int reservedDay = now.getDayOfMonth() - day;
        if (reservedDay < 1){
            age1Month = now.getMonthValue() - 1;
        }
        else{
            age1Month= now.getMonthValue();
        }
        int reservedMonth = age1Month - month;
        if(reservedMonth < 1){
            age1Year = now.getYear()-1;
        }
        else{
            age1Year = now.getYear();
        }
        age = age1Year - year;
        return age;
    }
    public int getMaxFrequency(){
        maxFrequency = 220 - age;
        return maxFrequency;
    }
    public int getTargetFrequency1(){
        targetFrequency1 = maxFrequency/2;
        return targetFrequency1;
    }
    public int getTargetFrequency2() {
        targetFrequency2 = (maxFrequency *85)/ 100;
        return targetFrequency2;
    }
    public double getImc(){
        imc = weight/(height*height)*10000;
        return imc;
    }
    //java.time.LocalDate birthDate = java.time.LocalDate.of(year, month, day);
    //    return java.time.Period.between(birthDate, java.time.LocalDate.now()).getYears();
}
