package sky.pro.java.course2.homework2_8_3.service;

import sky.pro.java.course2.homework2_8_3.data.Employee;

import java.util.stream.Stream;

public interface EmployeeService {
    public Employee addEmployee (String lastName, String firstName, Float salary, Integer departmentId);
    public Employee dismissEmployee (String firstName, String lastName);
    public Employee findEmloyee (String firstName, String lastName);
    public String getAllStaff();
    String calculateAllSalaries();
    String maxSalaryofDepartment(Integer departmentId);
    String minSalaryofDepartment(Integer departmentId);
    String printStaffOfDepartment(Integer departmentId);
    String printAllStaff();
}
