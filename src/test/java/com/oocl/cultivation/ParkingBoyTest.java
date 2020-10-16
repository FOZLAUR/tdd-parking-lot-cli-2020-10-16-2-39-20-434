package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_car_given_car() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_right_car_when_fetch_car_given_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);

        assertSame(car, fetchedCar);
    }
}
