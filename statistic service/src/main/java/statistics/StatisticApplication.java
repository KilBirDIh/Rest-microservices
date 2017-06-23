package statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import statistics.configuration.JpaConfiguration;

@SpringBootApplication
@EnableFeignClients
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Import(JpaConfiguration.class)
public class StatisticApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(StatisticApplication.class, args);
    }
}
