//Description: Build an employee management system with the following requirements:
//Use an abstract class Employee with fields like employeeId, name, and baseSalary.
//Provide an abstract method calculateSalary() and a concrete method displayDetails().
//Create two subclasses: FullTimeEmployee and PartTimeEmployee, implementing calculateSalary() based on work hours or fixed salary.
//Use encapsulation to restrict direct access to fields and provide getter and setter methods.
//Create an interface Department with methods like assignDepartment() and getDepartmentDetails().
//Ensure polymorphism by processing a list of employees and displaying their details using the Employee reference.
abstract class Employee{
    private String employeeId;
    private String name;
    private int baseSalary;

    String getEmployeeId(){
        return this.employeeId;
    }

    String getName(){
        return this.name;
    }

    int getBaseSalary(){
        return this.baseSalary;
    }

    Employee(String id, String name, int salary){
        this.employeeId = id;
        this.name = name;
        this.baseSalary = salary;
    }

    abstract int calculateSalary();

    void displayDetails(){
        System.out.println("Employee Id: " + this.employeeId);
        System.out.println("Employee Name: " + this.name);
        System.out.println("Base Salary: " + this.baseSalary);
        if(this instanceof PartTimeEmployee){
            System.out.println("Fixed working hours");
        }
        else{
            System.out.println("Variable working hours");
        }
    }
}

interface Department{
    void assignDepartment(String department);
    String getDepartment();
}

class FullTimeEmployee extends Employee implements Department{
    private final int workHours;
    private String department;

    FullTimeEmployee(String id, String name, int salary, int hrs){
        super(id,name,salary);
        this.workHours = hrs;
    }

    int getWorkHours(){
        return this.workHours;
    }

    @Override
    int calculateSalary() {
        if(workHours >= 9){
            return 10000*workHours;
        }
        else{
            return 7000*workHours;
        }
    }

    @Override
    public void assignDepartment(String department){
        this.department = department;
    }

    @Override
    public String getDepartment(){
        return this.department;
    }
}

class PartTimeEmployee extends Employee implements Department{
    private int workHours;
    private String department;

    PartTimeEmployee(String id, String name, int salary, int hrs){
        super(id,name,salary);
        this.workHours = hrs;
    }

    @Override
    int calculateSalary() {
        if(workHours >= 9){
            return 1000*workHours;
        }
        else{
            return 700*workHours;
        }
    }

    int getWorkHours(){
        return this.workHours;
    }

    @Override
    public void assignDepartment(String department){
        this.department = department;
    }

    @Override
    public String getDepartment(){
        return this.department;
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Employee emp1 = new FullTimeEmployee("101", "Alice", 50000,7);
        Employee emp2 = new PartTimeEmployee("102", "Bob", 20, 10);

        ((Department) emp1).assignDepartment("HR");
        ((Department) emp2).assignDepartment("Finance");

        // Processing employees using polymorphism
        Employee[] employees = {emp1, emp2};
        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println("Department: " + ((Department) emp).getDepartment());
            System.out.println("--------------------------");
        }


//        Employee Id: 101
//        Employee Name: Alice
//        Base Salary: 50000
//        Variable working hours
//        Department: HR
//                --------------------------
//        Employee Id: 102
//        Employee Name: Bob
//        Base Salary: 20
//        Fixed working hours
//        Department: Finance
//                --------------------------
    }
}
