package heartRate316;
import java.time.LocalDateTime;

public class HeartRates{
    private String firstName;
    private String lastName;
    private int day;
    private int month;
    private int year;
    private int age;
    private int maxFrequency;
    private int targetFrequency1;
    private int targetFrequency2;

    // Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setDayBirth(int day){
        this.day = day;
    }
    public void setMonthBirth(int month){
        this.month = month;
    }
    public void setYearBirth(int year){
        this.year = year;
    }

    // Getters
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getDayBirth(){
        return day;
    }
    public int getMounthBirth(){
        return month;
    }
    public int getYearBirth(){
        return year;
    }

    // Métodos a parte

    public int getAge(){
        LocalDateTime now = LocalDateTime.now();
        int age1Month;
        int age1Year;

        int reservedDay = now.getDayOfMonth() - day;
        if (reservedDay< 1){
            age1Month = now.getMonthValue()- 1;
        }
        else{
            age1Month = now.getMonthValue();
        }
        int reservedMonth = age1Month - month;
        if(reservedMonth < 1){
            age1Year = now.getYear()-1;
        }
        else{
            age1Year = now.getYear();
        }
        age = age1Year -year;
    return age;
    }
    public int maxFrequency(){
        maxFrequency = 220 - age;
        return maxFrequency;
    }
    public int targetFrequency1(){
        targetFrequency1 = maxFrequency/2;
        return targetFrequency1;
    }
    public int targetFrequency2(){
        targetFrequency2 = (maxFrequency/100)*85;
        return targetFrequency2;
    }

}