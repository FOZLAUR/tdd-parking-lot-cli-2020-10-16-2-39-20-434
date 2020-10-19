package com.oocl.cultivation;

import com.oocl.exceptions.CarIsAlreadyParkedException;
import com.oocl.exceptions.CarIsNullException;
import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuperSmartParkingBoyTest {

    //Story 5 - Test Case 1:
    // Given Two Parking Lots with Capacity 2 & 3
    // When Parking Boy Parks Two Car
    // Then Cars are Parked in Parking Lot with higher Availability Ratio
    @Test
    public void should_park_in_lot_with_higher_position_rate_when_parking_3_cars_given_two_parking_lots_with_capacity_2_and_capacity_3() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        //when
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(car2);
        ParkingTicket parkingTicket3 = superSmartParkingBoy.park(car3);

        //then
        assertTrue(parkingLotList.get(0).containsTicket(parkingTicket1));
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket2));
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket3));
    }

    //Story 4 - Test Case 2:
    // Given Two Parking Lots with Capacity 1 & 2
    // When Parking Boy Parks Four Car
    // Then 3 Cars are Parked in Parking Lot but 4th is not
    @Test
    public void should_return_no_ticket_when_parking_4_cars_given_two_parking_lots_with_capacity_2_and_capacity_3() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();

        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(car2);
        ParkingTicket parkingTicket3 = superSmartParkingBoy.park(car3);
        ParkingTicket parkingTicket4 = superSmartParkingBoy.park(car4);
        ParkingTicket parkingTicket5 = superSmartParkingBoy.park(car5);

        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket1));
        assertTrue(parkingLotList.get(0).containsTicket(parkingTicket2));
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket3));
        assertTrue(parkingLotList.get(0).containsTicket(parkingTicket4));
        assertTrue(parkingLotList.get(1).containsTicket(parkingTicket5));

        //when-then
        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
            superSmartParkingBoy.park(car6);
        });
    }

    //Story 4 - Test Case 3:
    // Given Two Parking Lots with Capacity 1 & 2
    // When Parking Boy Parks Null Car
    // Then no ticket returned
    @Test
    public void should_return_no_ticket_when_parking_null_car_given_two_parking_lots_with_capacity_2_and_capacity_3() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when-then
        assertThrows(CarIsNullException.class, () -> {
            superSmartParkingBoy.park(null);
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Car car = new Car();
        superSmartParkingBoy.park(car);

        //when-then
        assertThrows(CarIsAlreadyParkedException.class, () -> {
            superSmartParkingBoy.park(car);
        });
    }
}
