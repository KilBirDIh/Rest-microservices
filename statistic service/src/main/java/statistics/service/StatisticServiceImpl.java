package statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import statistics.client.EmployeeServiceClient;
import statistics.domain.Employee;
import statistics.domain.Statistic;
import statistics.repository.StatisticRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService
{
    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private EmployeeServiceClient employeeServiceClient;

    @Override
    public void save(Statistic statistic)
    {
        Resources<Employee> employees = employeeServiceClient.getEmployees();
        statistic.setAverageAge(employees.getContent().stream().filter(employee -> employee.getAge() != null).mapToDouble(Employee::getAge).average().orElse(0));
        statistic.setAverageDurationOfWork(employees.getContent().stream().mapToLong(employee -> ChronoUnit.MONTHS.between(employee.getHireDate(), employee.getDismissalDate())).average().orElse(0));
        statistic.setDateOfCollection(LocalDate.now());
        statisticRepository.save(statistic);
    }

    @Override
    public Statistic findById(Long id)
    {
        return statisticRepository.findOne(id);
    }

    @Override
    public List<Statistic> findAll()
    {
        return (List<Statistic>) statisticRepository.findAll();
    }
}
