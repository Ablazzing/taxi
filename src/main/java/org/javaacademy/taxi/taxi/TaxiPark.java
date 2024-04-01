package org.javaacademy.taxi.taxi;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.taxi.client.Client;
import org.javaacademy.taxi.taxi.exception.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.math.BigDecimal.ZERO;

@Slf4j
@Component
public class TaxiPark {
    @Value("${taxi.name}")
    private String name;
    private final Queue<Car> cars;
    private BigDecimal totalIncome = ZERO;

    public TaxiPark(List<Car> cars) {
        if (cars.size() == 0) {
            throw new RuntimeException("Машин в таксопарке нет!");
        }
        this.cars = new LinkedList<>(cars);
    }

    public void takeOrder(Client client, boolean isDay) {
        Car car = cars.poll();
        try {
            car.takeOrder(client, isDay);
        } catch (AddressNotFoundException e) {
            log.warn(e.getMessage());
        }
        cars.add(car);
    }

    @PreDestroy
    public void printResult() {
        String result = """
        %s
        Заработано: %s
        %s
        """.formatted(name, totalIncome, getTotalResultForCars());
        log.info(result);
    }

    private String getTotalResultForCars() {
        StringBuilder builder = new StringBuilder();
        cars.forEach(car -> builder.append(getTotalResultForCar(car)));
        return builder.toString();
    }

    private String getTotalResultForCar(Car car) {
        return "Водитель машины %s заработал: %s\n".formatted(car.getNumber(), car.getTotalIncome());
    }

    public void addIncome(BigDecimal halfIncome) {
        totalIncome = totalIncome.add(halfIncome);
    }
}
