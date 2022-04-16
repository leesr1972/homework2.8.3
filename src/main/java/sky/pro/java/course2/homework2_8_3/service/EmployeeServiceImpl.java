package sky.pro.java.course2.homework2_8_3.service;

import org.springframework.stereotype.Service;
import sky.pro.java.course2.homework2_8_3.data.Employee;
import sky.pro.java.course2.homework2_8_3.exceptions.BadRequest;
import sky.pro.java.course2.homework2_8_3.exceptions.NotFound;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> staffOfEmployee = new HashMap<>(Map.of(
            "ПетровЮрий", new Employee("Петров", "Юрий", 200_000f, 0),
            "ЯкобсонИосиф", new Employee("Якобсон", "Иосиф", 180_000f, 0),
            "СтивенДжексон", new Employee("Стивен", "Джексон", 180_000f, 1),
            "КимМария", new Employee("Ким", "Мария", 150_000f, 1),
            "МансуроваАмира", new Employee("Мансурова", "Амира", 150_000f, 2),
            "ШевченкоИрина", new Employee("Шевченко", "Ирина", 120_000f, 2),
            "ЯцехироАнимото", new Employee("Яцехиро", "Анимото", 180_000f, 3),
            "ДорошенкоМатвей", new Employee("Дорошенко", "Матвей", 150_000f, 3),
            "АбдуллаевБахром", new Employee("Абдуллаев", "Бахром", 100_000f, 3)
    ));

    private List<String> departments = new ArrayList<>(List.of("Руководство", "Бухгалтерия",
            "Отдел кадров", "Технический отдел"));

    @Override
    public Employee findEmloyee(String lastName, String firstName) {
        Employee employee = staffOfEmployee.get(lastName + firstName);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            return employee;
        } else {
            throw new BadRequest();
        }
    }

    @Override
    public Employee addEmployee(String lastName, String firstName, Float salary, Integer departmentId) {
        Employee newEmployee = new Employee(lastName, firstName, salary, departmentId);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            throw new BadRequest();
        } else {
            staffOfEmployee.put(lastName + firstName, newEmployee);
            return newEmployee;
        }
    }

    @Override
    public Employee dismissEmployee(String lastName, String firstName) {
        Employee dismissedEmployee = staffOfEmployee.get(lastName + firstName);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            staffOfEmployee.remove(lastName + firstName, dismissedEmployee);
            return dismissedEmployee;
        } else {
            throw new NotFound();
        }
    }

    @Override
    public String getAllStaff() {
        if (staffOfEmployee.size() == 0) {
            throw new NotFound();
        }
        List<String> allStaff = staffOfEmployee.values().stream().
                map(employee -> employee.getLastName() + " " + employee.getFirstName()).
                collect(Collectors.toList());
        return "Список сотрудников:" + allStaff;
    }

    @Override
    public String calculateAllSalaries() {
        List<Float> listOfSalaries = staffOfEmployee.values().stream()
                .map(e -> e.getSalary())
                .collect(Collectors.toList());
        double wageFund = listOfSalaries.stream().mapToDouble(e->e).sum();
        return "Фонд оплаты труда составляет " + wageFund + " руб.";
    }

    @Override
    public String maxSalaryofDepartment(Integer departmentId) {
        if (departmentId < 0 || departmentId > departments.size() - 1) {
            throw new BadRequest();
        }
        Optional<Employee> employeesWithMaxSalary = staffOfEmployee.values().stream().
                filter(e -> e.getDepartmentId() == departmentId).
                max(Comparator.comparingDouble(employee -> employee.getSalary()));
        return "В отделе " + departments.get(departmentId) + " самую высокую зарплату получает "
                + employeesWithMaxSalary.get().getLastName() + " "
                + employeesWithMaxSalary.get().getFirstName() + " - "
                + employeesWithMaxSalary.get().getSalary() + " руб.";
    }

    @Override
    public String minSalaryofDepartment(Integer departmentId) {
        if (departmentId < 0 || departmentId > departments.size() - 1) {
            throw new BadRequest();
        }
        Optional<Employee> employeesWithMinSalary = staffOfEmployee.values().stream().
                filter(e -> e.getDepartmentId() == departmentId).
                min(Comparator.comparingDouble(employee -> employee.getSalary()));
        return "В отделе " + departments.get(departmentId) + " самую низкую зарплату получает "
                + employeesWithMinSalary.get().getLastName() + " "
                + employeesWithMinSalary.get().getFirstName() + " - "
                + employeesWithMinSalary.get().getSalary() + " руб.";
    }

    @Override
    public String printStaffOfDepartment(Integer departmentId) {
        if (departmentId < 0 || departmentId > departments.size() - 1) {
            throw new BadRequest();
        }
        List<String> staffOfDepartment = staffOfEmployee.values().stream().
                filter(e -> e.getDepartmentId() == departmentId).
                map(employee -> employee.getLastName() + " " + employee.getFirstName()).
                collect(Collectors.toList());
        return "В отделе " + departments.get(departmentId) + " работают следующие сотрудники: "
                + staffOfDepartment + ". ";
    }

    @Override
    public String printAllStaff() {
        String allStaff = "Список всех сотрудников: ";
        for (int i = 0; i < departments.size(); i++) {
            allStaff = allStaff + printStaffOfDepartment(i);
        }
        return allStaff;
    }
}
