package com.oocl.cultivation;

import com.oocl.exceptions.CarIsAlreadyParkedException;
import com.oocl.exceptions.CarIsNullException;
import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import com.oocl.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmartParkingBoyTest {

    //Story 4 - Test Case 1:
    // Given Two Parking Lots with Capacity 1 & 2
    // When Parking Boy Parks Two Car
    // Then Cars are Parked in Parking Lot with more Positions
    @Test
    public void should_return_park_in_lot_with_more_capacity_when_parking_2_cars_given_smart_parking_boy_two_parking_lots_with_capacity_1_and_capacity_2() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(car2);

        //then
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket1));
        assertTrue(parkingLotList.get(0).containsTicket(parkingTicket2));
    }

    //Story 4 - Test Case 2:
    // Given Two Parking Lots with Capacity 1 & 2
    // When Parking Boy Parks Four Car
    // Then 3 Cars are Parked in Parking Lot but 4th is not
    @Test
    public void should_return_no_ticket_when_parking_4_cars_given_two_parking_lots_with_capacity_1_and_capacity_2() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        ParkingTicket parkingTicket1 = smartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(car2);
        ParkingTicket parkingTicket3 = smartParkingBoy.park(car3);

        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket1));
        assertTrue(parkingLotList.get(0).containsTicket(parkingTicket2));
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket3));

        //when-then
        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
            smartParkingBoy.park(car4);
        });
    }

    //Story 4 - Test Case 3:
    // Given Two Parking Lots with Capacity 1 & 2
    // When Parking Boy Parks Null Car
    // Then no ticket returned
    @Test
    public void should_return_no_ticket_when_parking_null_car_given_two_parking_lots_with_capacity_1_and_capacity_2() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when-then
        assertThrows(CarIsNullException.class, () -> {
            smartParkingBoy.park(null);
        });
    }

    //Story 4 - Test Case 4:
    // Given Two Parking Lots with Capacity 1 & 2
    // When Parking Boy Parks Parked Car
    // Then no ticket returned
    @Test
    public void should_return_no_ticket_when_parking_parked_car_given_two_parking_lots_with_capacity_1_and_capacity_2() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car car = new Car();
        smartParkingBoy.park(car);

        //when-then
        assertThrows(CarIsAlreadyParkedException.class, () -> {
            smartParkingBoy.park(car);
        });
    }
}
