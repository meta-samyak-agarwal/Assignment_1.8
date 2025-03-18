import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class Question1Test {
    
    Question1.Metacube metacube;
    Question1.Department salesDepartment;
    Question1.Department devDepartment;
    Question1.Payroll payroll;

    Question1.Employee employee1;
    Question1.Employee employee2;
    Question1.Employee employee3;
    Question1.Employee employee4;

    @BeforeEach
    void setUp() {
        metacube = new Question1.Metacube();
        
        salesDepartment = new Question1.Department("Sales Department");
        devDepartment = new Question1.Department("Dev Department");
        
        metacube.addDepartment(salesDepartment);
        metacube.addDepartment(devDepartment);

        employee1 = new Question1.nonTechEmployee(1, 20000, "Samyak1");
        employee2 = new Question1.nonTechEmployee(2, 16000, "Samyak2");
        employee3 = new Question1.techEmployee(3, 18000, "Samyak3");
        employee4 = new Question1.techEmployee(4, 10000, "Samyak4");

        salesDepartment.joinEmployee(employee1);
        salesDepartment.joinEmployee(employee2);
        devDepartment.joinEmployee(employee3);
        devDepartment.joinEmployee(employee4);

        payroll = new Question1.Payroll(metacube);
    }

    @Test
    void testDepartmentNames() {
        List<Question1.Department> departments = metacube.getAllDepartments();
        assertEquals(2, departments.size());
        assertEquals("Sales Department", departments.get(0).getDeptName());
        assertEquals("Dev Department", departments.get(1).getDeptName());
    }

    @Test
    void testEmployeeAdditionToDepartment() {
        assertEquals(2, salesDepartment.getAllEmployees().size());
        assertEquals(2, devDepartment.getAllEmployees().size());
    }

    @Test
    void testEmployeeRemovalFromDepartment() {
        assertTrue(salesDepartment.removeEmployee(employee1));
        assertEquals(1, salesDepartment.getAllEmployees().size());
        
        assertTrue(devDepartment.removeEmployee(employee3));
        assertEquals(1, devDepartment.getAllEmployees().size());
    }

    @Test
    void testEmployeeSalaries() {
        assertEquals(20000, employee1.getBasicSalary());
        assertEquals(16000, employee2.getBasicSalary());
        assertEquals(18000, employee3.getBasicSalary());
        assertEquals(10000, employee4.getBasicSalary());
    }

    @Test
    void testTotalSalaryCalculation() {
        assertEquals(20000 + (5 * 20000) + (9 * 20000), employee1.getSalary());
        assertEquals(16000 + (5 * 16000) + (9 * 16000), employee2.getSalary());
        assertEquals(18000 + (11 * 18000) + (12 * 18000), employee3.getSalary());
        assertEquals(10000 + (11 * 10000) + (12 * 10000), employee4.getSalary());
    }

    @Test
    void testAllEmployeesInMetacube() {
        List<Question1.Employee> allEmployees = metacube.getAllEmployees();
        assertEquals(4, allEmployees.size());
    }
}