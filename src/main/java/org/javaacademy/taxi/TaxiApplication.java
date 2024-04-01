package org.javaacademy.taxi;

import org.javaacademy.taxi.client.Client;
import org.javaacademy.taxi.taxi.DistanceAddress;
import org.javaacademy.taxi.taxi.TaxiPark;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TaxiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TaxiApplication.class, args);
		TaxiPark taxiPark = context.getBean(TaxiPark.class);

		Client client1 = new Client(DistanceAddress.KANDIKYLYA.getName());
		Client client3 = new Client(DistanceAddress.STROITEL.getName());
		Client client2 = new Client(DistanceAddress.BEREZOVAYA_ROSCHA.getName());
		Client client4 = new Client("Ломоносов");

		taxiPark.takeOrder(client1, true);
		taxiPark.takeOrder(client2, true);
		taxiPark.takeOrder(client3, false);
		taxiPark.takeOrder(client4, false);
		context.close();
	}

}
