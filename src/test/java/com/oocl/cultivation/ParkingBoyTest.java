package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void should_return_two_cars_when_fetch_car_given_two_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        Car fetchedCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);

        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    public void should_return_no_car_when_fetch_car_given_wrong_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetchCar(new ParkingTicket());

        assertNull(fetchedCar);
    }
}
