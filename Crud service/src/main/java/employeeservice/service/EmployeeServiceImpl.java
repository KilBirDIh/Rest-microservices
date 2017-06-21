package employeeservice.service;

import employeeservice.model.Employee;
import employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Long id)
    {
        return employeeRepository.findOne(id);
    }

    @Override
    public void saveEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee)
    {
        saveEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Long id)
    {
        employeeRepository.delete(id);
    }

    @Override
    public void deleteAllEmployees()
    {
        employeeRepository.deleteAll();
    }

    @Override
    public List<Employee> findAllEmployees()
    {
        return (List<Employee>) employeeRepository.findAll();
    }

    public boolean isEmployeeExist(Employee employee) {
        return findById(employee.getId()) != null;
    }
}
