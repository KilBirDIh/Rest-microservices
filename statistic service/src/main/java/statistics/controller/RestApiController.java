package statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import statistics.domain.Statistic;
import statistics.service.StatisticService;

import java.util.Comparator;

@RestController
public class RestApiController
{
    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getLatestStatistic()
    {
        Statistic statistic = statisticService.findAll().stream().max(Comparator.comparing(Statistic::getDateOfCollection)).get();
        return new ResponseEntity<Object>(statistic, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createStatistic()
    {
        statisticService.save(new Statistic());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
