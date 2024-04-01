package org.javaacademy.taxi.config;

import org.javaacademy.taxi.taxi.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("luna")
@Configuration
public class TaxiLunaConfig {

    @Bean
    public Car car1(TaxiPropertyConfig taxiPropertyConfig) {
        return new Car("r001xs799", taxiPropertyConfig);
    }

    @Bean
    public Car car2(TaxiPropertyConfig taxiPropertyConfig) {
        return new Car("r876xs799", taxiPropertyConfig);
    }

    @Bean
    public Car car3(TaxiPropertyConfig taxiPropertyConfig) {
        return new Car("r323xs799", taxiPropertyConfig);
    }
}
