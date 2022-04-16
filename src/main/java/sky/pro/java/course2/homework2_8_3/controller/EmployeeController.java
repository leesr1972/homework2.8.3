package sky.pro.java.course2.homework2_8_3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.java.course2.homework2_8_3.data.Employee;
import sky.pro.java.course2.homework2_8_3.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addNewEmployee(@RequestParam String lastName,
                                 @RequestParam String firstName,
                                 @RequestParam Float salary,
                                 @RequestParam Integer departmentId) {
        Employee newEmployee = employeeService.addEmployee(lastName, firstName, salary, departmentId);
        return newEmployee.getLastName() + " " + newEmployee.getFirstName() + " принят на работу.";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String lastName, @RequestParam String firstName) {
        Employee disMissedEmployee = employeeService.dismissEmployee(lastName, firstName);
        return disMissedEmployee.getLastName() + " " + disMissedEmployee.getFirstName() + " уволен.";
    }

    @GetMapping("/find")
    public String findStaff(@RequestParam String lastName, @RequestParam String firstName) {
        Employee employee = employeeService.findEmloyee(lastName, firstName);
        return employee.getLastName() + " " + employee.getFirstName() + " найден.";
    }

    @GetMapping("/print")
    public String printAllStaff() {
        return employeeService.getAllStaff();
    }

    @GetMapping("/sumOfSalaries")
    public String sumOfSalaries() {
        return employeeService.calculateAllSalaries();
    }

    @GetMapping("/departments/max-salary")
    public String employeeWitnMaxSalary(@RequestParam Integer departmentId) {
        return employeeService.maxSalaryofDepartment(departmentId);
    }

    @GetMapping("/departments/min-salary")
    public String employeeWitnMinSalary(@RequestParam Integer departmentId) {
        return employeeService.minSalaryofDepartment(departmentId);
    }

    @GetMapping("/departments/all")
    public String printStaffOfDepartment(@RequestParam Integer departmentId) {
        return employeeService.printStaffOfDepartment(departmentId);
    }

    @GetMapping("/departments/allPrint")
    public String printAllStaffbyDepartments() {
        return employeeService.printAllStaff();
    }
}
