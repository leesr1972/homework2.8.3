package sky.pro.java.course2.homework2_8_3.service;

public interface DepartmentService {
    String maxSalaryofDepartment(Integer departmentId);
    String minSalaryofDepartment(Integer departmentId);
    String printStaffOfDepartment(Integer departmentId);
    String printAllStaff();
}
