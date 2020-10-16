package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_car_given_car() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }
}
