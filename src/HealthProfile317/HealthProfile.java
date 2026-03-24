package HealthProfile317;

public class HealthProfile{
    private String firstName;
    private String lastName;
    private String sex;
    private int day;
    private int month;
    private int year;
    private int height;
    private double weight;

    public static void Main(String[] args){

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
    public int getHeight(){
        return height;
    }
    public double weight(){
        return weight;
    }



}
