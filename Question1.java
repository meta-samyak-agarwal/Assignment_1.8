
import java.util.*;

class Question1 {

    // abstract class for the employee
    abstract static class Employee {

        int emp_id;
        int salary;
        String emp_name;

        abstract public int getBasicSalary();

        abstract public int getBonus();

        abstract public int getCompensation();

        public int getSalary() {
            return getBasicSalary() + getBonus() + getCompensation();
        }

    }

    // employee type 1
    static class techEmployee extends Employee {

        public techEmployee(int emp_id, int salary, String emp_name) {
            this.emp_id = emp_id;
            this.salary = salary;
            this.emp_name = emp_name;
        }

        public int getBasicSalary() {
            return salary;
        }

        public int getBonus() {
            return 11 * salary;
        }

        public int getCompensation() {
            return 12 * salary;
        }
    }

    // employee type 2
    static class nonTechEmployee extends Employee {

        public nonTechEmployee(int emp_id, int salary, String emp_name) {
            this.emp_id = emp_id;
            this.salary = salary;
            this.emp_name = emp_name;
        }

        public int getBasicSalary() {
            return salary;
        }

        public int getBonus() {
            return 5 * salary;
        }

        public int getCompensation() {
            return 9 * salary;
        }
    }
    // department class which stores the list of employees

    static class Department {

        // list of all employees
        List<Employee> listOfEmployees;
        String name;

        public Department(String name) {
            listOfEmployees = new ArrayList<>();
            this.name = name;
        }

        // adding the employee into the department
        boolean joinEmployee(Employee emp) {
            listOfEmployees.add(emp);
            return true;
        }

        // removing the empployee into the department
        boolean removeEmployee(Employee emp) {
            listOfEmployees.remove(emp);
            return true;
        }

        // returning the list of all empoyees in a department
        List<Employee> getAllEmployees() {
            return listOfEmployees;
        }

        public String getDeptName() {
            return this.name;
        }

    }

    // organisation class where all department is there
    static class Metacube {

        List<Department> departmentList;

        List<Employee> finalList = new ArrayList<>();

        public Metacube() {
            departmentList = new ArrayList<>();
        }

        public boolean addDepartment(Department dep) {
            departmentList.add(dep);
            return true;
        }

        public List<Department> getAllDepartments() {
            return departmentList;
        }

        public List<Employee> getAllEmployees() {
            for (Department dp : departmentList) {
                finalList.addAll(dp.listOfEmployees);
            }
            return finalList;
        }
    }

    // Payroll class for the salary slip
    static class Payroll {

        Metacube meta;
    
        public Payroll(Metacube meta) {
            this.meta = meta;
        }
     
        void getSalarySlip(Employee e) {

            System.out.println("Here is the salary slip of: " + e.emp_name);

            System.out.println("Your basic salary is: " + e.getBasicSalary());
            System.out.println("Your bonus is: " + e.getBonus());
            System.out.println("Your compesation is: " + e.getCompensation());

            System.out.println("Your total salary is: " + e.getSalary());

        }
            
    }

    public static void main(String[] args) {

        Department salesDepartment = new Department("salesDepartment");
        Department devDepartment = new Department("devDepartment");

        // added the department into the organization
        Metacube metacube = new Metacube();
        metacube.addDepartment(salesDepartment);
        metacube.addDepartment(devDepartment);

        System.out.println("*************************************");
        // print the department list
        System.out.println("Department list are: ");
        List<Department> departments = metacube.getAllDepartments();

        for (Department dp : departments) {
            System.out.println(dp.getDeptName());
        }

        // added the several employees
        Employee employee1 = new nonTechEmployee(1, 20000, "samyak1");
        Employee employee2 = new nonTechEmployee(2, 16000, "samyak2");

        Employee employee3 = new techEmployee(3, 18000, "samyak3");
        Employee employee4 = new techEmployee(4, 10000, "samyak4");

        // added the employees into their respective departments
        salesDepartment.joinEmployee(employee1);
        salesDepartment.joinEmployee(employee2);

        devDepartment.joinEmployee(employee3);
        devDepartment.joinEmployee(employee4);


       
        System.out.println("**********************************************");
        
        System.out.println("The employess names of devDepartment are: ");
        List<Employee> devEmployeeList = devDepartment.getAllEmployees();
        for (Employee e : devEmployeeList) {
            System.out.println("name -> " + e.emp_name + "and  id -> " + e.emp_id);
        }
        
        System.out.println("The employess names fo salesDepartment are: ");
        List<Employee> salesEmployeeList = salesDepartment.getAllEmployees();
        for (Employee e : salesEmployeeList) {
            System.out.println("name -> " + e.emp_name + "and  id -> " + e.emp_id);
        }

        System.out.println("**********************************************");

        System.out.println("Enter the salary slips of the all the employess: ");
        Payroll pay = new Payroll(metacube);

        List<Employee> salarySlip2 = metacube.getAllEmployees();

        for (Employee e : salarySlip2) {
            pay.getSalarySlip(e);
        }


        System.out.println("************************************************");
        System.out.println("List of all employes in metacube are: ");
        List<Employee> allEmployees = metacube.getAllEmployees();
        for (Employee e : allEmployees) {
            System.out.println("name -> " + e.emp_name + "and  id -> " + e.emp_id);

        }
    }
}
