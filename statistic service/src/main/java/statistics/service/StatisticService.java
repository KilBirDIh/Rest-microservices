package statistics.service;

import statistics.domain.Statistic;

import java.util.List;

public interface StatisticService
{
    void save(Statistic statistic);

    Statistic findById(Long id);

    List<Statistic> findAll();
}
