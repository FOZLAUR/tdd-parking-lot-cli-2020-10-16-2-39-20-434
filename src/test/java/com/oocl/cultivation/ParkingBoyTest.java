package com.oocl.cultivation;

import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import com.oocl.exceptions.TicketNotProvidedException;
import com.oocl.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    //Story 1 - Test Case 1:
    // Given Car
    // When Parking Boy Parks the Car
    // Then Parking Ticket Returned
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

    //Story 1 - Test Case 2:
    // Given Parking Ticket
    // When Parking Boy Fetches Car
    // Then Car is Returned
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

    //Story 1 - Test Case 3:
    // Given Parking Two Cars
    // When Parking Boy Fetches Cars with Tickets
    // Then Cars are Returned
    @Test
    public void should_return_two_cars_when_fetch_car_given_two_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        Car fetchedCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);

        //then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    //Story 1/2 - Test Case 4:
    // Given Wrong Ticket
    // When Parking Boy Fetches Car
    // Then No Car Returned (UnrecognizedTicketException)
    @Test
    public void should_return_no_car_when_fetch_car_given_wrong_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket correctTicket = parkingBoy.park(car);

        //when
        ParkingTicket wrongTicket = new ParkingTicket();
        Car fetchedCorrectCar = parkingBoy.fetchCar(correctTicket);
        assertSame(car, fetchedCorrectCar);

        //then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(wrongTicket);
        });
    }

    //Story 1/2 - Test Case 5:
    // Given No Ticket
    // When Parking Boy Fetches Car
    // Then No Car Returned (TicketNotProvidedException)
    @Test
    public void should_return_no_car_when_fetch_car_given_no_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when-then
        assertThrows(TicketNotProvidedException.class, () -> {
            parkingBoy.fetchCar(null);
        });
    }

    //Story 1/2 - Test Case 6:
    // Given Used Ticket
    // When Parking Boy Fetches Car
    // Then No Car Returned (UnrecognizedTicketException)
    @Test
    public void should_return_no_car_when_fetch_car_given_used_ticket() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        assertSame(car, fetchedCar);

        //when-then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(parkingTicket);
        });
    }

    //Story 1/2 - Test Case 7:
    // Given Used Ticket
    // When Parking Boy Fetches Car
    // Then No Car Returned (ParkingLotOutOfPositionsException)
    @Test
    public void should_return_no_ticket_when_fetch_ticket_given_parking_lot_capacity_is_1_and_parked_1_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);

        //when-then
        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
            parkingBoy.park(car2);
        });
    }

    //Story 1/2 - Test Case 8:
    // Given null Car
    // When Parking Boy Parks Car
    // Then Car is not Parked
    @Test
    public void should_return_car_is_not_parked_when_park_car_given_null_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        assertNull(parkingBoy.park(null));
    }

    //Story 1/2 - Test Case 9:
    // Given parked Car
    // When Parking Boy Parks Car
    // Then Car is not Parked
    @Test
    public void should_return_car_is_not_parked_when_park_car_given_parked_car() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        ParkingTicket correctParkingTicket = parkingBoy.park(car);
        ParkingTicket wrongParkingTicket = parkingBoy.park(car);

        assertNotNull(correctParkingTicket);
        assertNull(wrongParkingTicket);
    }

    //Story 3 - Test Case 1:
    // Given Two Parking Lots
    // When Parking Boy Parks Two Car
    // Then Cars are Parked sequentially
    @Test
    public void should_return_two_tickets_from_parking_lots_when_parking_2_cars_given_parking_lots_with_capacity_1() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //then
        assertTrue(parkingLotList.get(0).containsTicket(parkingTicket1));
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket2));
    }

    //Story 3 - Test Case 2:
    // Given Two Parking Lots
    // When Parking Boy Parks Three Cars
    // Then Third Car is not Parked (ParkingLotOutOfPositionsException)
    @Test
    public void should_not_return_third_ticket_when_park_car_given_two_parking_lot_capacity_1_each_and_three_cars() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        parkingBoy.park(car1);
        parkingBoy.park(car2);

        //when-then
        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
            parkingBoy.park(car3);
        });
    }

    //Story 3 - Test Case 3:
    // Given Two Parking Lots and Two Used Tickets
    // When Parking Boy Fetches Two Car
    // Then No Cars are Returned (UnrecognizedTicketException)
    @Test
    public void should_not_return_cars_when_fetch_car_given_used_tickets() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        parkingBoy.fetchCar(parkingTicket1);
        parkingBoy.fetchCar(parkingTicket2);

        //when-then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(parkingTicket1);
        });
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(parkingTicket2);
        });
    }

    //Story 3 - Test Case 4:
    // Given Two Parking Lots and Two Wrong Tickets
    // When Parking Boy Fetches Two Car
    // Then No Cars are Returned (UnrecognizedTicketException)
    @Test
    public void should_not_return_cars_when_fetch_car_given_wrong_tickets() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);
        parkingBoy.park(car2);
        ParkingTicket wrongParkingTicket1 = new ParkingTicket();
        ParkingTicket wrongParkingTicket2 = new ParkingTicket();

        //when-then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(wrongParkingTicket1);
        });
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(wrongParkingTicket2);
        });
    }

    //Story 3 - Test Case 5:
    // Given Two Parking Lots and No Tickets
    // When Parking Boy Fetches Two Car
    // Then No Cars are Returned (UnrecognizedTicketException)
    @Test
    public void should_not_return_car_when_fetch_car_given_no_tickets() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingBoy.park(car1);
        parkingBoy.park(car2);

        //when-then
        assertThrows(TicketNotProvidedException.class, () -> {
            parkingBoy.fetchCar(null);
        });
    }
}
