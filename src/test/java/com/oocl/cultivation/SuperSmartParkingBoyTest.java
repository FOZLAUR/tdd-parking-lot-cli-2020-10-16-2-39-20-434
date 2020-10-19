package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuperSmartParkingBoyTest {

    //Story 5 - Test Case 1: Given Two Parking Lots with Capacity 2 & 3 When Parking Boy Parks Two Car Then Cars are Parked in Parking Lot with higher Availability Ratio
    @Test
    public void should_park_in_lot_with_higher_position_rate_when_parking_3_cars_given_super_smart_parking_boy_two_parking_lots_with_capacity_2_and_capacity_3() {
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
}
