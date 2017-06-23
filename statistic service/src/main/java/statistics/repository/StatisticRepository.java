package statistics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import statistics.domain.Statistic;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Long>
{

}
