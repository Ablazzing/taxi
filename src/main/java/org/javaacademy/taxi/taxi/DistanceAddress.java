package org.javaacademy.taxi.taxi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.javaacademy.taxi.taxi.exception.AddressNotFoundException;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
@ToString
public enum DistanceAddress {
    BEREZOVAYA_ROSCHA("Березовая роща", 10),
    KANDIKYLYA("Кандикюля", 4),
    STROITEL("Строитель", 12);
    private final String name;
    private final double distance;

    public static DistanceAddress getAddress(String name) {
        return Arrays.stream(DistanceAddress.values())
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException("Нет такого адреса!"));
    }
}
