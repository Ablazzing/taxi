package org.javaacademy.taxi.taxi;

import lombok.Getter;
import org.javaacademy.taxi.client.Client;
import org.javaacademy.taxi.config.TaxiPropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.CEILING;

public class Car {
    //свойства ставка за км ночная (из ресурсов),
    //ставка за км дневная (из ресурсов), номер машины, сумма заработанных денег.
    private final BigDecimal nightRate;
    private final BigDecimal dayRate;
    @Getter
    private final String number;
    @Getter
    private BigDecimal totalIncome = ZERO;
    @Lazy
    @Autowired
    private TaxiPark taxiPark;

    public Car(String number, TaxiPropertyConfig propertyConfig) {
        this.number = number;
        this.nightRate = valueOf(propertyConfig.getNight());
        this.dayRate = valueOf(propertyConfig.getDay());
    }

    public void takeOrder(Client client, boolean isDay) {
        DistanceAddress address = DistanceAddress.getAddress(client.getAddress());
        BigDecimal rate = isDay ? dayRate : nightRate;
        BigDecimal totalResult = rate.multiply(valueOf(address.getDistance()));
        BigDecimal halfIncome = totalResult.divide(valueOf(2), 2, CEILING);
        totalIncome = totalIncome.add(halfIncome);
        taxiPark.addIncome(halfIncome);
    }

//3.2 Умеет принимать заказ: на вход клиент, день или ночь.
// Из клиента берем адрес и сопоставляем по тарифной сетке:
//Березовая роща - 10км
//Кандикюля - 4км
//Строитель - 12км
//Остальное - ошибка
//На основании адреса, узнаем сколько ехать, и считаем сумму заработанных денег (ставка * км).
// 50% оставляем себе, 50% идет в таксопарк.
}
