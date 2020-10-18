package com.oocl.cultivation;

import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import com.oocl.exceptions.TicketNotProvidedException;
import com.oocl.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    //Story 1 - Test Case 1: Given Car When Parking Boy Parks the Car Then Parking Ticket Returned
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

    //Story 1 - Test Case 2: Given Parking Ticket When Parking Boy Fetches Car Then Car is Returned
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

    //Story 1 - Test Case 3: Given Parking Two Cars When Parking Boy Fetches Cars with Tickets Then Cars are Returned
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

    //Story 1/2 - Test Case 4: Given Wrong Ticket When Parking Boy Fetches Car Then No Car Returned (UnrecognizedTicketException)
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

    //Story 1/2 - Test Case 5: Given No Ticket When Parking Boy Fetches Car Then No Car Returned (TicketNotProvidedException)
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

    //Story 1/2 - Test Case 6: Given Used Ticket When Parking Boy Fetches Car Then No Car Returned (UnrecognizedTicketException)
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

    //Story 1/2 - Test Case 7: Given Used Ticket When Parking Boy Fetches Car Then No Car Returned (ParkingLotOutOfPositionsException)
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

    //Story 3 - Test Case 1: Given Two Parking Lots When Parking Boy Parks Two Car Then Cars are Parked sequentially
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

    //Story 4 - Test Case 1: Given Two Parking Lots with Capacity 1 & 2 When Parking Boy Parks Two Car Then Cars are Parked in Parking Lot with more Positions
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

    //Story 6 (AC1) - Test Case 1: Given Parking Lot Service Manager When Manager Adds Parking Boy to Management List Then Parking Boy is Successfully Added
    @Test
    public void should_be_added_to_management_list_when_add_parking_boy_to_management_list_given_parking_lot_service_manager_and_parking_boy() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        parkingLotServiceManager.addToManagementList(parkingBoy);

        //then
        assertTrue(parkingLotServiceManager.isInManagementList(parkingBoy));
    }

    //Story 6 (AC1) - Test Case 2: Given Parking Lot Service Manager When Manager Assigns Parking Boy to Park Car Then Car is Parked and Ticket is Returned
    @Test
    public void should_return_ticket_when_parking_boy_park_car_given_parking_lot_service_manager_asks_parking_boy_to_park_car() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingLotServiceManager.addToManagementList(parkingBoy);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLotServiceManager.assignParkingBoyToParkCar(parkingBoy, car);

        //then
        assertTrue(parkingLot.containsTicket(parkingTicket));
    }

    //Story 6 (AC1) - Test Case 3: Given Parking Lot Service Manager and Ticket When Manager Assigns Parking Boy to fetch Car Then Car is Fetched and Ticket is Returned
    @Test
    public void should_return_fetched_car_when_parking_boy_fetch_car_given_parking_lot_service_manager_asks_parking_boy_to_fetch_car() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingLotServiceManager.addToManagementList(parkingBoy);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.assignParkingBoyToParkCar(parkingBoy, car);

        //when
        Car fetchedCar = parkingLotServiceManager.assignParkingBoyToFetchCar(parkingBoy, parkingTicket);

        //then
        assertSame(car, fetchedCar);
    }

    //Story 6 (AC2) - Test Case 1: Given Parking Lot Service Manager and Parking Lot When Manager is Assigned a Parking Lot Then Parking Lot is Successfully Added
    @Test
    public void should_return_parking_lot_assigned_to_parking_manager_when_parking_lot_is_assigned_to_parking_lot_service_manager_given_parking_lot_service_manager_and_parking_lot() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        ParkingLot parkingLot = new ParkingLot();

        //when
        parkingLotServiceManager.addParkingLotToParkingLotList(parkingLot);

        // then
        assertTrue(parkingLotServiceManager.isInParkingLotList(parkingLot));
    }

    //Story 6 (AC3) - Test Case 1: Given Parking Lot Service Manager and Wrong Ticket When Manager Assigns Parking Boy to Fetch Car Then No Car is Returned (UnrecognizedTicketException)
    @Test
    public void should_return_unrecognized_ticket_exception_when_parking_manager_assigns_parking_boy_fetch_car_given_parking_lot_service_manager_wrong_ticket() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingLotServiceManager.addToManagementList(parkingBoy);
        Car car = new Car();

        //when
        parkingLotServiceManager.assignParkingBoyToParkCar(parkingBoy, car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLotServiceManager.assignParkingBoyToFetchCar(parkingBoy, wrongTicket);
        });
    }

    //Story 6 (AC3) - Test Case 2: Given Parking Lot Service Manager and Used Ticket When Manager Assigns Parking Boy to Fetch Car Then No Car is Returned (UnrecognizedTicketException)
    @Test
    public void should_return_unrecognized_ticket_exception_when_parking_boy_fetch_car_given_parking_lot_service_manager_asks_parking_boy_to_park_car_with_used_ticket() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingLotServiceManager.addToManagementList(parkingBoy);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLotServiceManager.assignParkingBoyToParkCar(parkingBoy, car);

        //when
        parkingLotServiceManager.assignParkingBoyToFetchCar(parkingBoy, parkingTicket);

        //then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLotServiceManager.assignParkingBoyToFetchCar(parkingBoy, parkingTicket);
        });
    }

    //Story 6 (AC3) - Test Case 3: Given Parking Lot Service Manager and No Ticket When Manager Assigns Parking Boy to Fetch Car Then No Car is Returned (TicketNotProvidedException)
    @Test
    public void should_return_ticket_not_provided_exception_when_parking_boy_fetch_car_given_parking_lot_service_manager_asks_parking_boy_to_park_car_with_no_ticket() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingLotServiceManager.addToManagementList(parkingBoy);

        //when-then
        assertThrows(TicketNotProvidedException.class, () -> {
            parkingLotServiceManager.assignParkingBoyToFetchCar(parkingBoy, null);
        });
    }

    //Story 6 (AC3) - Test Case 4: Given Parking Lot Service Manager and Car Parked in Lot with Capacity 1 When Manager Assigns Parking Boy to Fetch Another Car in Full Parking Lot Then No Ticket is Returned (ParkingLotOutOfPositionsException)
    @Test
    public void should_return_parking_lot_out_of_positions_exception_when_parking_boy_park_car_given_parking_lot_service_manager_asks_parking_boy_to_park_car_when_parking_lot_is_full() {
        //given
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingLotServiceManager.addToManagementList(parkingBoy);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLotServiceManager.assignParkingBoyToParkCar(parkingBoy,car1);

        //when-then
        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
            parkingLotServiceManager.assignParkingBoyToParkCar(parkingBoy, car2);
        });
    }

    //Story 6 - Extra Test Case: Given Parking Manager and Parking Lot When Parking Manager assigns Parking Lot to Parking Boy Then Parking Lot is successfully assigned to Parking Boy
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
        assertTrue(parkingBoy.isAssignedParkingLot(parkingLot));
    }
}
