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

    @Test
    public void should_return_two_ticket_with_1_car_in_each_parking_lot_when_parking_2_cars_given_two_parking_lot_with_capacity_1() {
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

    @Test
    public void should_be_assigned_properly_when_assign_parking_lot_to_parking_boy_given_parking_lot_service_manager() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ParkingLot parkingLot = new ParkingLot();

        //when
        parkingLotServiceManager.assignParkingLot(parkingBoy, parkingLot);

        //then
        assertTrue(parkingBoy.isAssignedAParkingLot());
    }
}
