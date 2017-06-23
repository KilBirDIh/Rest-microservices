package statistics.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import statistics.domain.Employee;

@FeignClient(value = "statistic-service", url = "localhost:8090")
public interface EmployeeServiceClient
{
    @RequestMapping(method = RequestMethod.GET, value = "/employees/employees",produces = "application/json")
    Resources<Employee> getEmployees();

}
