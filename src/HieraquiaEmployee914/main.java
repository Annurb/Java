package HieraquiaEmployee914;

public class main {
    public static void main(String[] args) {
        // Instanciando um CommissionEmployee
        CommissionEmployee employee = new CommissionEmployee(
                "Sue", "Jones", "222-22-222", 10000, .06);

        // Exibindo os dados do CommissionEmployee
        System.out.println("--- TESTE COMMISSION EMPLOYEE ---");
        System.out.printf("%n%s%n", employee.toString());
        System.out.printf("Earnings: %.2f%n%n", employee.earnings());

        // Alterando valores via setters para validar funcionamento
        employee.setGrossSales(5000);
        employee.setCommissionRate(.10);

        System.out.println("--- APÓS ATUALIZAÇÃO DE VENDAS ---");
        System.out.printf("%s%n", employee); // Chama toString automaticamente
        System.out.printf("New Earnings: %.2f%n", employee.earnings());

        // Validando o acesso aos métodos da superclasse Employee
        System.out.printf("%nNome acessado via superclasse: %s %s%n",
                employee.getFirstName(), employee.getLastName());

        HourlyEmployee hourlyEmployee = new HourlyEmployee("Titan", "Rick", "444-44-444", 160, 160);

        System.out.println("--- TESTE HOURLY EMPLOYEE ---");
        System.out.printf("%n%s%n", hourlyEmployee.toString());
        System.out.printf("Earnings: %.2f%n%n", hourlyEmployee.earnings());

        // Alterando valores via setters para validar funcionamento
        hourlyEmployee.setHours(100);
        hourlyEmployee.setWage(1000);

        System.out.println("--- APÓS ATUALIZAÇÃO ---");
        System.out.printf("%s%n", hourlyEmployee); // Chama toString automaticamente
        System.out.printf("New Earnings: %.2f%n", hourlyEmployee.earnings());
    }
}

class Employee{
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    public Employee(String firstName, String lastName, String socialSecurityNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
    String getFirstName(){
        return this.firstName;
    }
    String getLastName(){
        return this.lastName;
    }
    String getSocialSecurityNumber(){
        return this.socialSecurityNumber;
    }

    @Override
    public String toString(){
        return String.format("%s %s%nSSN: %s", getFirstName(), getLastName(), getSocialSecurityNumber());
    }
}

class CommissionEmployee extends Employee{
    private double grossSales;
    private double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate){
        super(firstName, lastName, socialSecurityNumber);
        if(commissionRate <= 0.0 || commissionRate >=1.0){
            throw new IllegalArgumentException("Ilegal");
        }
        if(grossSales < 0.0 ){
            throw new IllegalArgumentException("ilegal");
        }
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }
    public void setGrossSales(double grossSales){
        if(grossSales < 0.0) throw new IllegalArgumentException("ilegal");
        this.commissionRate = commissionRate;
    }
    public void setCommissionRate(double commissionRate){
        if (commissionRate <= 0.0 || commissionRate >= 1.0)
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        this.commissionRate = commissionRate;
    }
    public double getGrossSales(){return grossSales;}
    public double getCommissionRate(){return commissionRate;}
    public double earnings(){
        return getCommissionRate()*getGrossSales();
    }

    @Override
    public String toString(){
        return String.format("%s: %s%n%s: %.2f%n%s:%.2f",
                "commission employee", super.toString(),
                "grossSales", getGrossSales(),
                "commission rate", getCommissionRate());
    }
}

class HourlyEmployee extends Employee{
    private double hours;
    private double wage;
    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, double wage, double hours){
        super(firstName, lastName, socialSecurityNumber);
        if(wage < 0 || hours < 0 || hours > 168){
            throw new IllegalArgumentException("Ilegal");
        }
        this.hours = hours;
        this.wage = wage;
    }
    public void setHours(double hours){
        if(hours < 0 || hours > 168){
            throw new IllegalArgumentException("Ilegal");
        }
        this.hours = hours;
    }
    public void setWage(double wage){
        if(wage < 0){
            throw new IllegalArgumentException("Ilegal");
        }
        this.wage = wage;
    }
    public double getHours(){
        return this.hours;
    }
    public double getWage(){
        return this.wage;
    }
    public double earnings(){
        if(getHours()<=40) {
            return getWage() * getHours();
        }
        else{
            return(40* getWage()) +((getHours()-40)* getWage()*1.5);
        }
    }
    @Override
    public String toString(){
        return String.format("Hourly employee: %s%nWage: %f%nHours: %f%nEarnings: %f%n", super.toString(), getWage(), getHours(), earnings());
    }

}