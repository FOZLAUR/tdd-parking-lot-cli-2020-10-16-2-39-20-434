package com.oocl.cultivation;

import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import com.oocl.exceptions.TicketNotProvidedException;
import com.oocl.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_car_given_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_right_car_when_fetch_car_given_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);

        //then
        assertSame(car, fetchedCar);
    }

//    @Test
//    public void should_return_two_cars_when_fetch_car_given_two_ticket() {
//        //given
//        ParkingLot parkinglot = new ParkingLot();
//        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
//        Car car1 = new Car();
//        Car car2 = new Car();
//
//        //when
//        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
//        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
//        Car fetchedCar1 = parkingBoy.fetchCar(parkingTicket1);
//        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);
//
//        //then
//        assertSame(car1, fetchedCar1);
//        assertSame(car2, fetchedCar2);
//    }
//
//    @Test
//    public void should_return_no_car_when_fetch_car_given_wrong_ticket() {
//        //given
//        ParkingLot parkinglot = new ParkingLot();
//        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
//        Car car = new Car();
//        ParkingTicket correctTicket = parkingBoy.park(car);
//
//        //when
//        ParkingTicket wrongTicket = new ParkingTicket();
//        Car fetchedCorrectCar = parkingBoy.fetchCar(correctTicket);
//        assertSame(car, fetchedCorrectCar);
//
//        //then
//        assertThrows(UnrecognizedTicketException.class, () -> {
//            parkingBoy.fetchCar(wrongTicket);
//        });
//    }
//
//    @Test
//    public void should_return_no_car_when_fetch_car_given_no_ticket() {
//        //given
//        ParkingLot parkinglot = new ParkingLot();
//        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
//
//        //when-then
//        assertThrows(TicketNotProvidedException.class, () -> {
//            parkingBoy.fetchCar(null);
//        });
//    }
//
//    @Test
//    public void should_return_no_car_when_fetch_car_given_used_ticket() {
//        //given
//        ParkingLot parkinglot = new ParkingLot();
//        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
//        Car car = new Car();
//        ParkingTicket parkingTicket = parkingBoy.park(car);
//        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
//        assertSame(car, fetchedCar);
//
//        //when-then
//        assertThrows(UnrecognizedTicketException.class, () -> {
//            parkingBoy.fetchCar(parkingTicket);
//        });
//    }
//
//    @Test
//    public void should_return_no_ticket_when_fetch_ticket_given_parking_lot_capacity_is_1_and_parked_1_car() {
//        //given
//        ParkingLot parkinglot = new ParkingLot(1);
//        ParkingBoy parkingBoy = new ParkingBoy(parkinglot);
//        Car car1 = new Car();
//        Car car2 = new Car();
//        parkingBoy.park(car1);
//
//        //when-then
//        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
//            parkingBoy.park(car2);
//        });
//    }
}
