package com.oocl.cultivation;

import com.oocl.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_car_given_car() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_right_car_when_fetch_car_given_ticket() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);

        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_two_cars_when_fetch_car_given_two_ticket() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        Car fetchedCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);

        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test//
    public void should_return_no_car_when_fetch_car_given_wrong_ticket() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car = new Car();
        ParkingTicket correctTicket = parkingBoy.park(car);

        ParkingTicket wrongTicket = new ParkingTicket();
        Car fetchedCorrectCar = parkingBoy.fetchCar(correctTicket);
        assertSame(car, fetchedCorrectCar);

        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(wrongTicket);
        });
    }

    @Test
    public void should_return_no_car_when_fetch_car_given_no_ticket() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);

        Car fetchedCar = parkingBoy.fetchCar(null);

        assertNull(fetchedCar);
    }

    @Test//
    public void should_return_no_car_when_fetch_car_given_used_ticket() {
        ParkingLot parkinglot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetchCar(parkingTicket);

        Car fetchedCarAgain = parkingBoy.fetchCar(parkingTicket);

        assertNull(fetchedCarAgain);
    }

    @Test
    public void should_return_no_ticket_when_fetch_ticket_given_parking_lot_capacity_is_1_and_parked_1_car() {
        ParkingLot parkinglot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        assertNull(parkingTicket2);
    }
}
