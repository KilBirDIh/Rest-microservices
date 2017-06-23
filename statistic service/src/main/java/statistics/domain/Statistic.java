package statistics.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Statistic
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double averageAge;
    private Double averageDurationOfWork;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCollection;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Double getAverageAge()
    {
        return averageAge;
    }

    public void setAverageAge(Double averageAge)
    {
        this.averageAge = averageAge;
    }

    public Double getAverageDurationOfWork()
    {
        return averageDurationOfWork;
    }

    public void setAverageDurationOfWork(Double averageDurationOfWork)
    {
        this.averageDurationOfWork = averageDurationOfWork;
    }

    public LocalDate getDateOfCollection()
    {
        return dateOfCollection;
    }

    public void setDateOfCollection(LocalDate dateOfCollection)
    {
        this.dateOfCollection = dateOfCollection;
    }
}
