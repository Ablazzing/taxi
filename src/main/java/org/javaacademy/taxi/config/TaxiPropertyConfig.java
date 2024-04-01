package org.javaacademy.taxi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "taxi.rate")
@Getter
@Setter
public class TaxiPropertyConfig {
    private double night;
    private double day;
}