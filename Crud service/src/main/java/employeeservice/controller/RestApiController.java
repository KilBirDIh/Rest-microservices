package employeeservice.controller;

import employeeservice.model.Employee;
import employeeservice.service.EmployeeService;
import employeeservice.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees()
    {
        List<Employee> employees = employeeService.findAllEmployees();
        if(employees.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable("id") final Long id)
    {
        Employee employee = employeeService.findById(id);
        if(employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {

        employeeService.saveEmployee(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {

        Employee currentEmployee = employeeService.findById(id);

        if (currentEmployee == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentEmployee.setFullName(employee.getFullName());
        currentEmployee.setDepartment(employee.getDepartment());
        currentEmployee.setPosition(employee.getPosition());
        currentEmployee.setDismissalDate(employee.getDismissalDate());
        currentEmployee.setHireDate(employee.getHireDate());
        employeeService.updateEmployee(currentEmployee);
        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {

        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
