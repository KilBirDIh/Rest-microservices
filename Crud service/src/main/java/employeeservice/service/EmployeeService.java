package employeeservice.service;

import employeeservice.model.Employee;

import java.util.List;

public interface EmployeeService
{
    Employee findById(Long id);

    void saveEmployee(Employee Employee);

    void updateEmployee(Employee Employee);

    void deleteEmployeeById(Long id);

    void deleteAllEmployees();

    List<Employee> findAllEmployees();

    boolean isEmployeeExist(Employee employee);
}
